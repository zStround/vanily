package com.github.vanily.token;

import com.github.vanily.token.request.Request;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.TransactionResult;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.async.RedisAsyncCommands;
import io.lettuce.core.codec.StringCodec;
import io.lettuce.core.support.AsyncConnectionPoolSupport;
import io.lettuce.core.support.AsyncPool;
import io.lettuce.core.support.BoundedAsyncPool;
import io.lettuce.core.support.BoundedPoolConfig;

import java.io.Closeable;
import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

public class TokenManager implements Closeable {

    private final RedisClient client = RedisClient.create();
    private final AsyncPool<StatefulRedisConnection<String, String>> pool;
    private final Gson gson = new Gson();

    private final static String CLIENT_ID = "minecraft-client";
    private final static String CLIENT_SECRET = "eOH9NhRI4BaUxLso62hIw7Jr9rSa2NdT";

    public TokenManager() {

        CompletionStage<BoundedAsyncPool<StatefulRedisConnection<String, String>>> poolFuture
                = AsyncConnectionPoolSupport.createBoundedObjectPoolAsync(
                () -> client.connectAsync(StringCodec.UTF8, RedisURI.create("redis", 6379)),
                BoundedPoolConfig.create());

         this.pool = poolFuture.toCompletableFuture()
                .join();

    }

    public CompletableFuture<TransactionResult> saveToken(String username, String accessToken, String refreshToken, long accessTokenExpiresAt, long refreshTokenExpiresAt) {

        Map<String, Object> tokenInfo = new HashMap<>();
        tokenInfo.put("access_token", accessToken);
        tokenInfo.put("access_token_expires_at", accessTokenExpiresAt);
        tokenInfo.put("refresh_token", refreshToken);
        tokenInfo.put("refresh_token_expires_at", refreshTokenExpiresAt);

        return this.pool.acquire()
                .thenCompose(connection -> {

                    RedisAsyncCommands<String, String> async = connection.async();

                    async.multi();
                    async.set(username, gson.toJson(tokenInfo));

                    return async.exec().whenComplete((s, throwable) -> pool.release(connection));
                });

    }

    public CompletableFuture<Boolean> isLoggedIn(String username) {

        return this.pool.acquire()
                .thenCompose(connection -> {

                    RedisAsyncCommands<String, String> async = connection.async();

                    return async.get(username)
                            .thenCompose(s -> pool.release(connection).thenApply(i -> s != null));
                });

    }

    public CompletableFuture<String> getToken(String username) {

        return this.pool.acquire()
                .thenCompose(connection -> {

                    RedisAsyncCommands<String, String> async = connection.async();

                    return async.get(username)
                            .thenApply(s -> JsonParser.parseString(s).getAsJsonObject())
                            .thenApply(json -> {

                                pool.release(connection);

                                long accessTokenExpiresAt = json.get("access_token_expires_at").getAsLong();

                                if (accessTokenExpiresAt < System.currentTimeMillis()) {

                                    long refreshTokenExpiresAt = json.get("refresh_token_expires_at").getAsLong();

                                    if (refreshTokenExpiresAt < System.currentTimeMillis()) {
                                        removeToken(username).join();
                                        throw new RuntimeException("Refresh token expired");
                                    }

                                    String refreshToken = json.get("refresh_token").getAsString();
                                    return renovateToken(username, refreshToken).join();
                                }

                                return json.get("access_token").getAsString();
                            });

                });

    }

    public CompletableFuture<String> renovateToken(String username, String refreshToken) {

        System.out.println("renovate");

        String body = String.format(
                "client_id=%s&client_secret=%s&grant_type=refresh_token&refresh_token=%s",
                CLIENT_ID, CLIENT_SECRET, refreshToken
        );

        final Request<String> request = new Request<>("https://keycloak:8080/realms/authorization-server/protocol/openid-connect/token", "POST", "application/x-www-form-urlencoded", body);

        return request.send()
                .thenCompose(response -> {

                    final HttpResponse<String> httpResponse = response.get();

                    if (httpResponse.statusCode() != 200)
                        throw new RuntimeException("HTTP status code: " + httpResponse.statusCode());

                    final JsonObject json = response.getBody();
                    final String accessToken = json.get("access_token").getAsString();
                    final String newRefreshToken = json.get("refresh_token").getAsString();
                    final long expiresIn = System.currentTimeMillis() + json.get("expires_in").getAsLong();
                    final long refreshExpiresIn = System.currentTimeMillis() + json.get("refresh_expires_in").getAsLong();

                    return saveToken(username.toLowerCase(), accessToken, newRefreshToken, expiresIn, refreshExpiresIn)
                            .thenApply(i -> accessToken);
                });

    }

    public CompletableFuture<String> getRefreshToken(String username) {

        return this.pool.acquire()
                .thenCompose(connection -> {

                    RedisAsyncCommands<String, String> async = connection.async();

                    return async.get(username)
                            .thenApply(s -> JsonParser.parseString(s).getAsJsonObject())
                            .thenCompose(json -> pool.release(connection).thenApply(i -> json.get("refresh_token").getAsString()));

                });

    }

    public CompletableFuture<TransactionResult> removeToken(String username) {

        return this.pool.acquire()
                .thenCompose(connection -> {

                    RedisAsyncCommands<String, String> async = connection.async();

                    async.multi();
                    async.del(username);
                    return async.exec().whenComplete((s, throwable) -> pool.release(connection));
                });

    }

    @Override
    public void close() throws IOException {
        this.pool.closeAsync();
        this.client.shutdownAsync();
    }

}

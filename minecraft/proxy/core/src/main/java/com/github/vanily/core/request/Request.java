package com.github.vanily.core.request;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

@RequiredArgsConstructor
@AllArgsConstructor
public class Request {

    private final String uri;
    private final String method;
    private JsonObject body;

    public CompletableFuture<JsonObject> send() {

        final HttpRequest.Builder httpRequestBuilder = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .method(method, HttpRequest.BodyPublishers.noBody());

        if (body != null) {
            httpRequestBuilder
                    .header("Content-Type", "application/json")
                    .method(method, HttpRequest.BodyPublishers.ofString(body.toString()));
        }

        final HttpRequest httpRequest = httpRequestBuilder.build();

        try(HttpClient httpClient = HttpClient.newHttpClient()) {

            return httpClient.sendAsync(httpRequest, HttpResponse.BodyHandlers.ofString())
                    .thenApply(response -> response.body() == null || response.body().isEmpty() ? new JsonObject() : JsonParser.parseString(response.body()).getAsJsonObject());

        }

    }

}

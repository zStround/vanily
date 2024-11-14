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

    public CompletableFuture<Response> send() {

        final HttpRequest.Builder httpRequestBuilder = HttpRequest.newBuilder()
                .uri(URI.create(uri));

        if (body != null) {
            httpRequestBuilder
                    .header("Content-Type", "application/json")
                    .method(method, HttpRequest.BodyPublishers.ofString(body.toString()));
        }

        final HttpRequest httpRequest = httpRequestBuilder.build();

        try(HttpClient httpClient = HttpClient.newHttpClient()) {

            return httpClient.sendAsync(httpRequest, HttpResponse.BodyHandlers.ofString())
                    .thenApply(Response::new);

        }

    }

    @RequiredArgsConstructor
    public static class Response {

        private final HttpResponse<String> response;

        public JsonObject getBody() {
            return JsonParser.parseString(response.body()).getAsJsonObject();
        }

        public HttpResponse<String> get() {
            return this.response;
        }

    }

}

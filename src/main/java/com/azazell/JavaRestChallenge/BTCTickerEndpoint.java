package com.azazell.JavaRestChallenge;

import com.azazell.JavaRestChallenge.Parsing.BTCResponseParser;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * Represents endpoint address of BTC ticker REST API link
 * @author Azazell
 */
public class BTCTickerEndpoint {
    /**
     * Builder for this class, obtainable via BTCTickerEndpoint#newBuilder
     * @author Azazell
     */
    public static class Builder {
        private URI uri;
        private String name;
        private BTCResponseParser parser;

        // only obtainable via BTCTickerEndpoint#newBuilder
        private Builder() {
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder uri(URI uri) {
            this.uri = uri;
            return this;
        }

        public Builder parser(BTCResponseParser parser) {
            this.parser = parser;
            return this;
        }

        /**
         * Builds new BTCTickerEndpoint. Name, uri and parser are mandatory for building
         */
        public BTCTickerEndpoint build() {
            if (parser == null || name == null || uri == null) {
                throw new IllegalStateException("Not all required fields were provided");
            }
            return new BTCTickerEndpoint(name, uri, parser);
        }
    }

    private final URI uri;
    private final BTCResponseParser parser;
    private final String name;

    /**
     * @return New builder for this object
     */
    public static Builder newBuilder() {
        return new Builder();
    }

    /**
     * Constructs BTC ticker endpoint. Must not be called directly, instead builder must be used
     * @param uri Endpoint of API link. example: https://blockchain.info/ticker
     * @param parser The parser to parse HTTP responses from API
     */
    private BTCTickerEndpoint(String name, URI uri, BTCResponseParser parser) {
        this.name = name;
        this.uri = uri;
        this.parser = parser;
    }

    /**
     * @return The name of the ticker
     */
    public String getName() {
        return name;
    }

    /**
     * Requests BTC price from ticker endpoint
     */
    public double requestPrice() throws IOException, InterruptedException {
        // Building and sending the GET request
        final HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .GET()
                .build();
        final HttpClient client = HttpClient.newBuilder().build();
        final HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return parser.parse(response.body());
    }
}

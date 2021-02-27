package com.azazell.JavaRestChallenge.Parsing;

import org.json.JSONObject;

import java.net.http.HttpResponse;

/**
 * BTC parser half-implementation that provides JSON object in it's parse abstraction
 * @deprecated Because we can't use JSON objects just anywhere, for example bitcoin ticker sends JSONArray instead of JSONObject
 * @author Azazell
 */
@Deprecated
public abstract class BTCJsonResponseParser implements BTCResponseParser {
    /**
     * Parses BTC currency price from JSON object
     * @param jsonObject The JSON object
     * @return BTC price in USD
     * @throws IllegalStateException If cannot find needed fields in response
     */
    public abstract double parse(JSONObject jsonObject);

    @Override
    public double parse(HttpResponse<String> response) {
        // Convert response into JSON object
        final JSONObject object = new JSONObject(response.body());
        return parse(object);
    }
}
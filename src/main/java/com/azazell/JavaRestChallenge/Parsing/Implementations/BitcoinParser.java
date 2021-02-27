package com.azazell.JavaRestChallenge.Parsing.Implementations;

import com.azazell.JavaRestChallenge.Parsing.BTCResponseParser;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Response parser for https://api.exchange.bitcoin.com/api/2/public/ticker endpoint
 * @author Azazell
 */
public class BitcoinParser implements BTCResponseParser {
    @Override
    public double parse(String body) {
        // For this one this is trickier, all objects are stored in one array, so
        // we have to find object with symbol field equal to BTCUSD
        final JSONArray array = new JSONArray(body);
        for (Object o : array) {
            // Cast object to json object
            final JSONObject jsonElement = (JSONObject) o;
            if (jsonElement.getString("symbol").equals("BTCUSD")) {
                // gotcha
                return jsonElement.getDouble("ask");
            }
        }

        // No price found
        throw new IllegalStateException("Cannot parse price from response: No BTCUSD array element in response");
    }
}

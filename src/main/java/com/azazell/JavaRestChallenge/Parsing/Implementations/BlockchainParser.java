package com.azazell.JavaRestChallenge.Parsing.Implementations;

import com.azazell.JavaRestChallenge.Parsing.BTCResponseParser;
import org.json.JSONObject;

/**
 * Response parser for https://blockchain.info/ticker
 * @author Azazell
 */
public class BlockchainParser implements BTCResponseParser {
    @Override
    public double parse(String body) {
        // This one is simple, just get USD object from root object
        final JSONObject rootObject = new JSONObject(body);
        final JSONObject usd = rootObject.getJSONObject("USD");

        // Now we can get the sell price
        return usd.getDouble("sell");
    }
}

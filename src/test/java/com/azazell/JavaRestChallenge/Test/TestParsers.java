package com.azazell.JavaRestChallenge.Test;

import com.azazell.JavaRestChallenge.Parsing.BTCResponseParser;
import com.azazell.JavaRestChallenge.Parsing.Implementations.BitcoinParser;
import com.azazell.JavaRestChallenge.Parsing.Implementations.BlockchainParser;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Test suit for parsers
 * @author Azazell
 */
public class TestParsers {
    @Test
    public void testBitcoinParser() {
        // forge synthetic response for parsing
        final double price = 42d;
        final JSONArray array = new JSONArray();
        final JSONObject notUsd = new JSONObject();
        notUsd.put("symbol", "NOT USD"); // must be ignored
        notUsd.put("ask", 392523); // some other price
        final JSONObject usd = new JSONObject();
        usd.put("symbol", "BTCUSD");
        usd.put("ask", price);
        array.put(notUsd);
        array.put(usd);

        // parse the response
        final BTCResponseParser parser = new BitcoinParser();
        final double parse = parser.parse(array.toString());
        Assertions.assertEquals(parse, price);
    }

    @Test
    public void testBlockchainParser() {
        // forge synthetic response for parsing
        final double price = 42d;
        final JSONObject root = new JSONObject();
        final JSONObject usd = new JSONObject();
        usd.put("sell", price);
        root.put("USD", usd);

        // parse the response
        final BTCResponseParser parser = new BlockchainParser();
        final double parse = parser.parse(root.toString());
        Assertions.assertEquals(parse, price);
    }
}

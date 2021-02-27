package com.azazell.JavaRestChallenge.Parsing;

/**
 * This interface parses BTC currency price from REST response
 * @author Azazell
 */
public interface BTCResponseParser {
    /**
     * Parses BTC currency price from REST response body
     * @param body The REST response body
     * @return BTC price in USD
     * @throws IllegalStateException If cannot find needed fields in response
     */
    double parse(String body);
}

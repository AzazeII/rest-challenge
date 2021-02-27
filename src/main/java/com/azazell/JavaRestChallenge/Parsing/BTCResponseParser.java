package com.azazell.JavaRestChallenge.Parsing;

import java.net.http.HttpResponse;

/**
 * This interface parses BTC currency price from REST response
 * @author Azazell
 */
public interface BTCResponseParser {
    /**
     * Parses BTC currency price from REST response
     * @param response The REST response
     * @return BTC price in USD
     * @throws IllegalStateException If cannot find needed fields in response
     */
    double parse(HttpResponse<String> response);
}

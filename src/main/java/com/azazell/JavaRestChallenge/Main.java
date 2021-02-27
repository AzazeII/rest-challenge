package com.azazell.JavaRestChallenge;

import com.azazell.JavaRestChallenge.Parsing.Implementations.BitcoinParser;
import com.azazell.JavaRestChallenge.Parsing.Implementations.BlockchainParser;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

/**
 * Main entrypoint for our code. We will use java 11 HTTP client for doing REST
 * @author Azazell
 */
public class Main {
    /**
     * Represents price and ticker
     * @author Azazell
     */
    private static class Price {
        private final BTCTickerEndpoint ticker;
        private final double price;

        /**
         * @return The ticker from which price was fetched
         */
        public BTCTickerEndpoint getTicker() {
            return ticker;
        }

        /**
         * @return The price fetched from ticker
         */
        public double getPrice() {
            return price;
        }

        /**
         * Constructs new price
         * @param ticker The ticker from which price was fetched
         * @param price The price fetched from ticker
         */
        public Price(BTCTickerEndpoint ticker, double price) {
            this.ticker = ticker;
            this.price = price;
        }
    }

    public static void main(String[] args) {
        try {
            // Create list of some endpoints
            final List<BTCTickerEndpoint> endpointList =
                    List.of(BTCTickerEndpoint.newBuilder()
                                .name("Bitcoin")
                                .uri(new URI("https://api.exchange.bitcoin.com/api/2/public/ticker"))
                                .parser(new BitcoinParser())
                                .build(),
                            BTCTickerEndpoint.newBuilder()
                                .name("Blockchain")
                                .uri(new URI("https://blockchain.info/ticker"))
                                .parser(new BlockchainParser())
                                .build());

            final Price lowestPrice = findLowestPrice(endpointList);

            // Log the lowest price
            System.out.printf("Lowest price is on \"%s\" ticker: %f USD%n",
                    lowestPrice.getTicker().getName(), lowestPrice.getPrice());
            // ...profit!
        } catch (URISyntaxException e) {
            System.out.println("Unable to construct URI: " + e.getMessage());
        } catch (IOException | InterruptedException e) {
            System.out.println("Unable to request price: " + e.getMessage());
        }
    }

    public static Price findLowestPrice(List<BTCTickerEndpoint> endpointList) throws IOException, InterruptedException {
        // Map endpoint to price
        final Map<BTCTickerEndpoint, Double> priceMap = new HashMap<>();
        for (BTCTickerEndpoint endpoint : endpointList) {
            priceMap.put(endpoint, endpoint.requestPrice());
        }

        // Find entrypoint with lowest price and convert it into price object
        return priceMap.entrySet()
                .stream()
                .min(Comparator.comparingDouble(Map.Entry::getValue)) // compare entries by values
                .map((entry) -> new Price(entry.getKey(), entry.getValue())) // convert to Price object
                .get();
    }
}

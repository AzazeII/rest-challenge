package com.azazell.JavaRestChallenge.Test;
import com.azazell.JavaRestChallenge.BTCTickerEndpoint;
import com.azazell.JavaRestChallenge.Parsing.Implementations.BlockchainParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Test suite for BTCTickerEndpoint class
 * @author Azazell
 */
public class TestBTCEndpoint {
    @Test
    public void testBTCEndpointBuilder() throws URISyntaxException {
        // Check if builder throws exception if we build object improperly
        Assertions.assertThrows(IllegalStateException.class, () -> {
            final BTCTickerEndpoint endpoint = BTCTickerEndpoint.newBuilder()
                    .uri(new URI("https://example.com"))
                    .parser(new BlockchainParser())
                    //.name("test") provide no name to test if it throws exception
                    .build();
        });
    }
}

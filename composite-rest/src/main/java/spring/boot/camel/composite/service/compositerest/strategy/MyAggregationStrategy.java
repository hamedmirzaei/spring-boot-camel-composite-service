package spring.boot.camel.composite.service.compositerest.strategy;

import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;

public class MyAggregationStrategy implements AggregationStrategy {
    @Override
    public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
        //oldExchange.getFromRouteId()
        newExchange.getOut().setBody("***"
                + oldExchange.getIn().getBody(String.class)
                + newExchange.getIn().getBody(String.class)
                + "***");
        return newExchange;
    }
}

package spring.boot.camel.composite.service.compositerest.strategy;

import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;

public class AllAggregationStrategy implements AggregationStrategy {
    @Override
    public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
        String oldString = "";
        String newString = "";
        if (oldExchange != null)
            oldString = oldExchange.getIn().getBody(String.class);
        if (newExchange != null)
            newString = newExchange.getIn().getBody(String.class);
        newExchange.getOut().setBody(oldString + newString);
        return newExchange;
    }
}

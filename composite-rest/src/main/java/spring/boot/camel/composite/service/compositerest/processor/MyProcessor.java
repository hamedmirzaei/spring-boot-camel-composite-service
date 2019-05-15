package spring.boot.camel.composite.service.compositerest.processor;

import spring.boot.camel.composite.service.compositerest.model.Customer;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

@Slf4j
public class MyProcessor implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        Customer customer = exchange.getIn().getBody(Customer.class);
        log.info("Body after unmarshal: " + customer.toString());
        exchange.getIn().setHeader("biid", customer.getBaseInfoId());
    }
}

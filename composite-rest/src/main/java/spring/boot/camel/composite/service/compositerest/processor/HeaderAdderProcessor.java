package spring.boot.camel.composite.service.compositerest.processor;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import spring.boot.camel.composite.service.commonmodel.model.Customer;

@Slf4j
public class HeaderAdderProcessor implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        Gson gson = new Gson();
        Customer customer = gson.fromJson(exchange.getIn().getBody(String.class), Customer.class);
        exchange.getIn().setHeader("biid", customer.getBaseInfoId());
        exchange.getIn().setHeader("type", customer.getCustomerType());
    }
}

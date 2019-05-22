package spring.boot.camel.composite.service.compositerest.strategy;

import com.google.gson.Gson;
import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;
import spring.boot.camel.composite.service.commonmodel.model.Customer;
import spring.boot.camel.composite.service.commonmodel.model.FullLegalCustomer;
import spring.boot.camel.composite.service.commonmodel.model.LegalBaseInfo;

public class LegalCustomerAggregationStrategy implements AggregationStrategy {
    @Override
    public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
        Gson gson = new Gson();
        Customer customer = gson.fromJson(oldExchange.getIn().getBody(String.class), Customer.class);
        LegalBaseInfo legalBaseInfo = gson.fromJson(newExchange.getIn().getBody(String.class), LegalBaseInfo.class);

        FullLegalCustomer legalCustomer = new FullLegalCustomer();
        legalCustomer.setId(customer.getId());
        legalCustomer.setBaseInfoId(customer.getBaseInfoId());
        legalCustomer.setCifNumber(customer.getCifNumber());
        legalCustomer.setCustomerType(customer.getCustomerType());
        legalCustomer.setLegalBaseInfo(legalBaseInfo);

        newExchange.getOut().setBody(gson.toJson(legalCustomer));
        return newExchange;
    }
}

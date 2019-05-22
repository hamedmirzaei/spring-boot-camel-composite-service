package spring.boot.camel.composite.service.compositerest.strategy;

import com.google.gson.Gson;
import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;
import spring.boot.camel.composite.service.commonmodel.model.Customer;
import spring.boot.camel.composite.service.commonmodel.model.FullRealCustomer;
import spring.boot.camel.composite.service.commonmodel.model.RealBaseInfo;

public class RealCustomerAggregationStrategy implements AggregationStrategy {
    @Override
    public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
        Gson gson = new Gson();
        Customer customer = gson.fromJson(oldExchange.getIn().getBody(String.class), Customer.class);
        RealBaseInfo realBaseInfo = gson.fromJson(newExchange.getIn().getBody(String.class), RealBaseInfo.class);

        FullRealCustomer realCustomer = new FullRealCustomer();
        realCustomer.setId(customer.getId());
        realCustomer.setBaseInfoId(customer.getBaseInfoId());
        realCustomer.setCifNumber(customer.getCifNumber());
        realCustomer.setCustomerType(customer.getCustomerType());
        realCustomer.setRealBaseInfo(realBaseInfo);

        newExchange.getOut().setBody(gson.toJson(realCustomer));
        return newExchange;
    }
}

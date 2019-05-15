package spring.boot.camel.composite.service.compositerest.strategy;

import com.google.gson.Gson;
import org.apache.camel.Exchange;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.apache.camel.model.dataformat.JsonDataFormat;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.camel.processor.aggregate.AggregationStrategy;
import spring.boot.camel.composite.service.compositerest.model.BaseInfo;
import spring.boot.camel.composite.service.compositerest.model.Customer;
import spring.boot.camel.composite.service.compositerest.model.FullCustomer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class MyAggregationStrategy implements AggregationStrategy {
    @Override
    public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
        Gson gson = new Gson();
        Customer customer = gson.fromJson(oldExchange.getIn().getBody(String.class), Customer.class);
        BaseInfo baseInfo = gson.fromJson(newExchange.getIn().getBody(String.class), BaseInfo.class);

        FullCustomer fullCustomer = new FullCustomer();
        fullCustomer.setId(customer.getId());
        fullCustomer.setCifNumber(customer.getCifNumber());
        fullCustomer.setBaseInfo(baseInfo);

        newExchange.getOut().setBody(gson.toJson(fullCustomer));
        return newExchange;
    }
}

package spring.boot.camel.composite.service.compositerest.strategy;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;
import spring.boot.camel.composite.service.commonmodel.model.CompositeEntity;
import spring.boot.camel.composite.service.commonmodel.model.Customer;
import spring.boot.camel.composite.service.commonmodel.model.LegalBaseInfo;
import spring.boot.camel.composite.service.commonmodel.model.RealBaseInfo;
import spring.boot.camel.composite.service.compositerest.ResourceType;

import java.lang.reflect.Type;
import java.util.List;

public class AllAggregationStrategy implements AggregationStrategy {

    @Override
    public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
        CompositeEntity compositeEntity;
        Gson gson = new Gson();
        if (oldExchange == null) {
            compositeEntity = new CompositeEntity();
        } else {
            compositeEntity = gson.fromJson(oldExchange.getIn().getBody(String.class), CompositeEntity.class);
        }
        switch ((String)newExchange.getIn().getHeader("RESOURCE_TYPE")) {
            case ResourceType.CUSTOMERS:
                Type listCustomerType = new TypeToken<List<Customer>>() {}.getType();
                List<Customer> customers = gson.fromJson(newExchange.getIn().getBody(String.class), listCustomerType);
                compositeEntity.setCustomers(customers);
                break;
            case ResourceType.REAL_BASE_INFOS:
                Type listRealBaseInfoType = new TypeToken<List<RealBaseInfo>>() {}.getType();
                List<RealBaseInfo> realBaseInfos = gson.fromJson(newExchange.getIn().getBody(String.class), listRealBaseInfoType);
                compositeEntity.setRealBaseInfos(realBaseInfos);
                break;
            case ResourceType.LEGAL_BASE_INFOS:
                Type listLegalBaseInfoType = new TypeToken<List<LegalBaseInfo>>() {}.getType();
                List<LegalBaseInfo> legalBaseInfos = gson.fromJson(newExchange.getIn().getBody(String.class), listLegalBaseInfoType);
                compositeEntity.setLegalBaseInfos(legalBaseInfos);
                break;
            default:
                break;
        }
        newExchange.getOut().setBody(gson.toJson(compositeEntity));
        return newExchange;
    }

}

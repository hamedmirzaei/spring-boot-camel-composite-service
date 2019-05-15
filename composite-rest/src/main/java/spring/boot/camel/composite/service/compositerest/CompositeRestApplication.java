package spring.boot.camel.composite.service.compositerest;

import spring.boot.camel.composite.service.compositerest.model.Customer;
import spring.boot.camel.composite.service.compositerest.processor.MyProcessor;
import spring.boot.camel.composite.service.compositerest.strategy.MyAggregationStrategy;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class CompositeRestApplication extends RouteBuilder {

    public static void main(String[] args) {
        SpringApplication.run(CompositeRestApplication.class, args);
    }

    @Override
    public void configure() throws Exception {

        from("jetty:http://localhost:8080/customerBaseInfos")
            .removeHeaders("CamelHttp*")
            .setHeader(Exchange.HTTP_METHOD, constant("GET"))
            .toD("jetty:http://localhost:8085/customers/id/${header[cid]}")
            .log("cid from url: ${header[cid]}")
            .log("Body before unmarshal: ${body}")
            .unmarshal().json(JsonLibrary.Jackson, Customer.class)
            .process(new MyProcessor())
            .log("Body after unmarshal.baseInfoId: ${body.baseInfoId}")
            .log("Header after unmarshal.baseInfoId: ${header[biid]}")
            .marshal().json(JsonLibrary.Jackson, Customer.class)
            .log("Body after marshal: ${body}")
            .removeHeaders("CamelHttp*")
            .setHeader(Exchange.HTTP_METHOD, constant("GET"))
            .enrich().simple("jetty:http://localhost:8086/baseinfos/id/${header[biid]}").aggregationStrategy(new MyAggregationStrategy())
            .log("Body after enrich: ${body}")
            .setHeader(Exchange.CONTENT_TYPE, constant("text/xml"));
    }
}

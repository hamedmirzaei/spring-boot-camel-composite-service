package spring.boot.camel.composite.service.compositerest;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import spring.boot.camel.composite.service.compositerest.processor.HeaderAdderProcessor;
import spring.boot.camel.composite.service.compositerest.strategy.LegalCustomerAggregationStrategy;
import spring.boot.camel.composite.service.compositerest.strategy.RealCustomerAggregationStrategy;

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
                /*.log("cid from url: ${header[cid]}")
                .log("Body before unmarshal: ${body}")*/
                //.unmarshal().json(JsonLibrary.Jackson, Customer.class)
                .process(new HeaderAdderProcessor())
                /*.log("Body after unmarshal.baseInfoId: ${body.baseInfoId}")
                .log("Header after unmarshal.baseInfoId: ${header[biid]}")*/
                //.marshal().json(JsonLibrary.Jackson, Customer.class)
                .choice()
                .when(header("type").isEqualTo("REAL"))
                .to("jetty:http://localhost:8080/realcustomer")
                .when(header("type").isEqualTo("LEGAL"))
                .to("jetty:http://localhost:8080/legalcustomer")
                .otherwise().end();

        from("jetty:http://localhost:8080/realcustomer")
                .removeHeaders("CamelHttp*")
                .setHeader(Exchange.HTTP_METHOD, constant("GET"))
                .enrich().simple("jetty:http://localhost:8086/realbaseinfos/id/${header[biid]}")
                .aggregationStrategy(new RealCustomerAggregationStrategy())
                .setHeader(Exchange.CONTENT_TYPE, simple("application/json"))
                .setHeader(Exchange.CHARSET_NAME, simple("utf-8"));

        from("jetty:http://localhost:8080/legalcustomer")
                .removeHeaders("CamelHttp*")
                .setHeader(Exchange.HTTP_METHOD, constant("GET"))
                .enrich().simple("jetty:http://localhost:8087/legalbaseinfos/id/${header[biid]}")
                .aggregationStrategy(new LegalCustomerAggregationStrategy())
                .setHeader(Exchange.CONTENT_TYPE, simple("application/json"))
                .setHeader(Exchange.CHARSET_NAME, simple("utf-8"));
    }
}

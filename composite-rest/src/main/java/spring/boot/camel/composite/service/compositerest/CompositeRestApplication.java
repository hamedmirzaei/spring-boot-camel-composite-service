package spring.boot.camel.composite.service.compositerest;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import spring.boot.camel.composite.service.compositerest.model.ResourceType;
import spring.boot.camel.composite.service.compositerest.processor.HeaderAdderProcessor;
import spring.boot.camel.composite.service.compositerest.strategy.AllAggregationStrategy;
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
                .process(new HeaderAdderProcessor())
                .choice()
                .when(header("type").isEqualTo("REAL"))
                .to("direct:realcustomer")
                .when(header("type").isEqualTo("LEGAL"))
                .to("direct:legalcustomer")
                .otherwise().end();

        from("direct:realcustomer")
                .removeHeaders("CamelHttp*")
                .setHeader(Exchange.HTTP_METHOD, constant("GET"))
                .enrich().simple("jetty:http://localhost:8086/realbaseinfos/id/${header[biid]}")
                .aggregationStrategy(new RealCustomerAggregationStrategy())
                .setHeader(Exchange.CONTENT_TYPE, simple("application/json"))
                .setHeader(Exchange.CHARSET_NAME, simple("utf-8"));

        from("direct:legalcustomer")
                .removeHeaders("CamelHttp*")
                .setHeader(Exchange.HTTP_METHOD, constant("GET"))
                .enrich().simple("jetty:http://localhost:8087/legalbaseinfos/id/${header[biid]}")
                .aggregationStrategy(new LegalCustomerAggregationStrategy())
                .setHeader(Exchange.CONTENT_TYPE, simple("application/json"))
                .setHeader(Exchange.CHARSET_NAME, simple("utf-8"));



        from("jetty:http://localhost:8080/all")
                .setHeader(Exchange.CONTENT_TYPE, simple("application/json"))
                .setHeader(Exchange.CHARSET_NAME, simple("utf-8"))
                .multicast(new AllAggregationStrategy())
                .parallelProcessing().timeout(500).to("direct:a", "direct:b", "direct:c");
        from("direct:a")
                .setHeader(Exchange.HTTP_URI, simple("http://localhost:8085/customers"))
                .setHeader("RESOURCE_TYPE", constant(ResourceType.CUSTOMERS))
                .to("jetty:http://localhost:8085/customers");
        from("direct:b")
                .setHeader(Exchange.HTTP_URI, simple("http://localhost:8086/realbaseinfos"))
                .setHeader("RESOURCE_TYPE", constant(ResourceType.REAL_BASE_INFOS))
                .to("jetty:http://localhost:8086/realbaseinfos");
        from("direct:c")
                .setHeader(Exchange.HTTP_URI, simple("http://localhost:8087/legalbaseinfos"))
                .setHeader("RESOURCE_TYPE", constant(ResourceType.LEGAL_BASE_INFOS))
                .to("jetty:http://localhost:8087/legalbaseinfos");

    }
}

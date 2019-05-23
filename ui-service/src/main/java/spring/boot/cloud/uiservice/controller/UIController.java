package spring.boot.cloud.uiservice.controller;

import com.google.gson.Gson;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;
import spring.boot.camel.composite.service.commonmodel.model.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class UIController {

    final String CUSTOMERS_URI = "http://localhost:8085/customers";
    final String REAL_BASE_INFO_URI = "http://localhost:8086/realbaseinfos";
    final String LEGAL_BASE_INFO_URI = "http://localhost:8087/legalbaseinfos";
    final String COMPOSITE_SERVICE_URI = "http://localhost:8080/customerBaseInfos?cid=";
    final String ALL_IN_PARALLEL_URI = "http://localhost:8080/all";

    @GetMapping("/")
    private String handleRealRequest(Model model) {

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<List<Customer>> customerResponse = restTemplate.exchange(
                CUSTOMERS_URI, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Customer>>() {
                });
        List<Customer> customers = customerResponse.getBody().stream().filter(e -> "REAL".equals(e.getCustomerType())).collect(Collectors.toList());


        ResponseEntity<List<RealBaseInfo>> realBaseInfoResponse = restTemplate.exchange(
                REAL_BASE_INFO_URI, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<RealBaseInfo>>() {
                });
        List<RealBaseInfo> realBaseInfos = realBaseInfoResponse.getBody();

        List<FullRealCustomer> fullRealCustomers = customers.parallelStream()
                .filter(e -> "REAL".equals(e.getCustomerType()))
                .map(e -> restTemplate.getForObject(COMPOSITE_SERVICE_URI + e.getId(), FullRealCustomer.class))
                .collect(Collectors.toList());

        model.addAttribute("realBaseInfos", realBaseInfos);
        model.addAttribute("customers", customers);
        model.addAttribute("fullRealCustomers", fullRealCustomers);

        return "index";
    }

    @GetMapping("/legal")
    private String handleLegalRequest(Model model) {

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<List<Customer>> customerResponse = restTemplate.exchange(
                CUSTOMERS_URI, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Customer>>() {
                });
        List<Customer> customers = customerResponse.getBody().stream().filter(e -> "LEGAL".equals(e.getCustomerType())).collect(Collectors.toList());

        ResponseEntity<List<LegalBaseInfo>> legalBaseInfoResponse = restTemplate.exchange(
                LEGAL_BASE_INFO_URI, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<LegalBaseInfo>>() {
                });
        List<LegalBaseInfo> legalBaseInfos = legalBaseInfoResponse.getBody();

        List<FullLegalCustomer> fullLegalCustomers = customers.parallelStream()
                .filter(e -> "LEGAL".equals(e.getCustomerType()))
                .map(e -> restTemplate.getForObject(COMPOSITE_SERVICE_URI + e.getId(), FullLegalCustomer.class))
                .collect(Collectors.toList());

        model.addAttribute("legalBaseInfos", legalBaseInfos);
        model.addAttribute("customers", customers);
        model.addAttribute("fullLegalCustomers", fullLegalCustomers);

        return "legal";
    }


    @GetMapping("/parallel")
    private String handleParallelRequest(Model model) {

        RestTemplate restTemplate = new RestTemplate();
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
        //Add the Jackson Message converter
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        // Note: here we are making this converter to process any kind of response,
        // not only application/*json, which is the default behaviour
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
        messageConverters.add(converter);
        restTemplate.setMessageConverters(messageConverters);
        CompositeEntity compositeEntity = restTemplate.getForObject(ALL_IN_PARALLEL_URI, CompositeEntity.class);
        model.addAttribute("compositeEntity", compositeEntity);

        return "parallel";
    }

}

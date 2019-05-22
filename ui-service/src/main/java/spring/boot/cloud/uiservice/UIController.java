package spring.boot.cloud.uiservice;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;
import spring.boot.camel.composite.service.commonmodel.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class UIController {

    final String CUSTOMERS_URI = "http://localhost:8085/customers";
    final String REAL_BASE_INFO_URI = "http://localhost:8086/realbaseinfos";
    final String LEGAL_BASE_INFO_URI = "http://localhost:8087/legalbaseinfos";
    final String COMPOSITE_SERVICE_URI = "http://localhost:8080/customerBaseInfos?cid=";

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

}

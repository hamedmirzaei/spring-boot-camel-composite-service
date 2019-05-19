package spring.boot.cloud.uiservice;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class UIController {

    final String CUSTOMERS_URI = "http://localhost:8085/customers";
    final String BASE_INFOS_URI = "http://localhost:8086/baseinfos";
    final String COMPOSITE_URI = "http://localhost:8080/customerBaseInfos?cid=";

    @GetMapping("/")
    private String handleRequest(Model model) {

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<List<Customer>> customerResponse = restTemplate.exchange(
                CUSTOMERS_URI,HttpMethod.GET,null,
                new ParameterizedTypeReference<List<Customer>>(){});
        List<Customer> customers = customerResponse.getBody();



        ResponseEntity<List<BaseInfo>> baseInfoResponse = restTemplate.exchange(
                BASE_INFOS_URI,HttpMethod.GET,null,
                new ParameterizedTypeReference<List<BaseInfo>>(){});
        List<BaseInfo> baseInfos = baseInfoResponse.getBody();


        List<FullCustomer> fullCustomers = customers.parallelStream().map(
                e -> restTemplate.getForObject(COMPOSITE_URI + e.getBaseInfoId(), FullCustomer.class)
        ).collect(Collectors.toList());


        model.addAttribute("baseinfos", baseInfos);
        model.addAttribute("customers", customers);
        model.addAttribute("fullcustomers", fullCustomers);

        return "index";
    }

}

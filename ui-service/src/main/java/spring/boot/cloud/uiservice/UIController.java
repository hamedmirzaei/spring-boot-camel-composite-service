package spring.boot.cloud.uiservice;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class UIController {

    final String CUSTOMERS_URI = "http://localhost:8085/customers";
    final String BASE_INFOS_URI = "http://localhost:8086/baseinfos";
    final String COMPOSITE_URI = "http://localhost:8080/customerBaseInfos?cid=1";

    @GetMapping("/")
    private String handleRequest(Model model) {

        RestTemplate restTemplate = new RestTemplate();
        FullCustomer fullCustomer = restTemplate.getForObject(COMPOSITE_URI, FullCustomer.class);


        ResponseEntity<List<BaseInfo>> baseInfoResponse = restTemplate.exchange(
                BASE_INFOS_URI,HttpMethod.GET,null,
                new ParameterizedTypeReference<List<BaseInfo>>(){});
        List<BaseInfo> baseInfos = baseInfoResponse.getBody();

        ResponseEntity<List<Customer>> customerResponse = restTemplate.exchange(
                CUSTOMERS_URI,HttpMethod.GET,null,
                new ParameterizedTypeReference<List<Customer>>(){});
        List<Customer> customers = customerResponse.getBody();

        model.addAttribute("baseinfos", baseInfos);
        model.addAttribute("customers", customers);
        model.addAttribute("fullcustomer", fullCustomer);

        return "index";
    }

}

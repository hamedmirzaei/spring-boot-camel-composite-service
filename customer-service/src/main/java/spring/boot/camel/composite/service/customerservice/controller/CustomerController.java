package spring.boot.camel.composite.service.customerservice.controller;

import spring.boot.camel.composite.service.customerservice.model.Customer;
import spring.boot.camel.composite.service.customerservice.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping("")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    @GetMapping("/id/{cid}")
    public ResponseEntity<Customer> getCustomer(@PathVariable("cid") Long customerId) {
        return ResponseEntity.ok(customerService.getCustomer(customerId));
    }

}

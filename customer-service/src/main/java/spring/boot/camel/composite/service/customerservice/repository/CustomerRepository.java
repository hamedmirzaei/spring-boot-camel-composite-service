package spring.boot.camel.composite.service.customerservice.repository;

import spring.boot.camel.composite.service.customerservice.model.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
}

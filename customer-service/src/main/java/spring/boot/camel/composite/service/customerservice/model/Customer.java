package spring.boot.camel.composite.service.customerservice.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "CUSTOMER")
public class Customer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "base_info_id")
    private Long baseInfoId;

    @Column(name = "cif_number")
    private Long cifNumber;

    @Column(name = "customer_type")
    private String customerType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBaseInfoId() {
        return baseInfoId;
    }

    public void setBaseInfoId(Long baseInfoId) {
        this.baseInfoId = baseInfoId;
    }

    public Long getCifNumber() {
        return cifNumber;
    }

    public void setCifNumber(Long cifNumber) {
        this.cifNumber = cifNumber;
    }

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    public Customer(Long id, Long baseInfoId, Long cifNumber, String customerType) {
        this.id = id;
        this.baseInfoId = baseInfoId;
        this.cifNumber = cifNumber;
        this.customerType = customerType;
    }

    public Customer() {
    }
}

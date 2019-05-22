package spring.boot.camel.composite.service.commonmodel.model;

import java.io.Serializable;

public class Customer implements Serializable {

    private Long id;

    private Long baseInfoId;

    private Long cifNumber;

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

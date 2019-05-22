package spring.boot.camel.composite.service.commonmodel.model;

import java.io.Serializable;

public class LegalBaseInfo implements Serializable {

    private Long id;

    private String organizationName;

    private String address;

    private String organizationCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOrganizationCode() {
        return organizationCode;
    }

    public void setOrganizationCode(String organizationCode) {
        this.organizationCode = organizationCode;
    }

    @Override
    public String toString() {
        return "LegalBaseInfo{" +
                "id=" + id +
                ", organizationName='" + organizationName + '\'' +
                ", address='" + address + '\'' +
                ", organizationCode='" + organizationCode + '\'' +
                '}';
    }

    public LegalBaseInfo(Long id, String organizationName, String address, String organizationCode) {
        this.id = id;
        this.organizationName = organizationName;
        this.address = address;
        this.organizationCode = organizationCode;
    }

    public LegalBaseInfo() {
    }
}

package spring.boot.cloud.uiservice;

import java.io.Serializable;

public class FullCustomer implements Serializable {
    private Long id;

    private Long cifNumber;

    private BaseInfo baseInfo;

    public FullCustomer() {
    }

    public FullCustomer(Long id, Long cifNumber, BaseInfo baseInfo) {
        this.id = id;
        this.cifNumber = cifNumber;
        this.baseInfo = baseInfo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCifNumber() {
        return cifNumber;
    }

    public void setCifNumber(Long cifNumber) {
        this.cifNumber = cifNumber;
    }

    public BaseInfo getBaseInfo() {
        return baseInfo;
    }

    public void setBaseInfo(BaseInfo baseInfo) {
        this.baseInfo = baseInfo;
    }

    @Override
    public String toString() {
        return "FullCustomer{" +
                "id=" + id +
                ", cifNumber=" + cifNumber +
                ", baseInfo=" + baseInfo +
                '}';
    }
}

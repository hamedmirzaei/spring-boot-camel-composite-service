package spring.boot.cloud.uiservice;


public class Customer {

    private Long id;

    private Long baseInfoId;

    private Long cifNumber;

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

    public Customer(Long id, Long baseInfoId, Long cifNumber) {
        this.id = id;
        this.baseInfoId = baseInfoId;
        this.cifNumber = cifNumber;
    }

    public Customer() {
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", baseInfoId=" + baseInfoId +
                ", cifNumber=" + cifNumber +
                '}';
    }
}

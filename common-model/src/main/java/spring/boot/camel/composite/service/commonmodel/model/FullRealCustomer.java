package spring.boot.camel.composite.service.commonmodel.model;

import java.io.Serializable;

public class FullRealCustomer extends Customer implements Serializable {

    private RealBaseInfo realBaseInfo;

    public RealBaseInfo getRealBaseInfo() {
        return realBaseInfo;
    }

    public void setRealBaseInfo(RealBaseInfo realBaseInfo) {
        this.realBaseInfo = realBaseInfo;
    }

    public FullRealCustomer(Long id, Long baseInfoId, Long cifNumber, String customerType, RealBaseInfo realBaseInfo) {
        super(id, baseInfoId, cifNumber, customerType);
        this.realBaseInfo = realBaseInfo;
    }

    public FullRealCustomer() {
    }

    @Override
    public String toString() {
        return "FullRealCustomer{" +
                "realBaseInfo=" + realBaseInfo +
                '}';
    }
}

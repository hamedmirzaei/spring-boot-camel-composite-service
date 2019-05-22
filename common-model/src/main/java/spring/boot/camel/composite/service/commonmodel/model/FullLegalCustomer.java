package spring.boot.camel.composite.service.commonmodel.model;

import java.io.Serializable;

public class FullLegalCustomer extends Customer implements Serializable {

    private LegalBaseInfo legalBaseInfo;

    public LegalBaseInfo getLegalBaseInfo() {
        return legalBaseInfo;
    }

    public void setLegalBaseInfo(LegalBaseInfo legalBaseInfo) {
        this.legalBaseInfo = legalBaseInfo;
    }

    public FullLegalCustomer(Long id, Long baseInfoId, Long cifNumber, String customerType, LegalBaseInfo legalBaseInfo) {
        super(id, baseInfoId, cifNumber, customerType);
        this.legalBaseInfo = legalBaseInfo;
    }

    public FullLegalCustomer() {
    }

    @Override
    public String toString() {
        return "FullLegalCustomer{" +
                "legalBaseInfo=" + legalBaseInfo +
                '}';
    }
}

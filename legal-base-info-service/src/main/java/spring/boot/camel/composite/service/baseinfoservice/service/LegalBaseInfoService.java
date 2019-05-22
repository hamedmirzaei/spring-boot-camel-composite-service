package spring.boot.camel.composite.service.baseinfoservice.service;

import spring.boot.camel.composite.service.baseinfoservice.model.LegalBaseInfo;
import spring.boot.camel.composite.service.baseinfoservice.repository.LegalBaseInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LegalBaseInfoService {

    @Autowired
    LegalBaseInfoRepository legalBaseInfoRepository;

    public List<LegalBaseInfo> getAllLegalBaseInfos() {
        List<LegalBaseInfo> legalBaseInfos = new ArrayList<LegalBaseInfo>();
        legalBaseInfoRepository.findAll().forEach(e -> legalBaseInfos.add(e));
        return legalBaseInfos;
    }


    public LegalBaseInfo getLegalBaseInfo(Long legalBaseInfoId) {
        Optional<LegalBaseInfo> legalBaseInfo = legalBaseInfoRepository.findById(legalBaseInfoId);
        if(legalBaseInfo.isPresent()) {
            return legalBaseInfo.get();
        } else {
            return new LegalBaseInfo(0L, "Unknown", "Unknown", "Unknown");
        }
    }

    public LegalBaseInfo saveLegalBaseInfo(LegalBaseInfo legalBaseInfo) {
        return legalBaseInfoRepository.save(legalBaseInfo);
    }

}

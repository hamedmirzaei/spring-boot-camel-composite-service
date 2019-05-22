package spring.boot.camel.composite.service.baseinfoservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.boot.camel.composite.service.baseinfoservice.model.RealBaseInfo;
import spring.boot.camel.composite.service.baseinfoservice.repository.RealBaseInfoRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RealBaseInfoService {

    @Autowired
    RealBaseInfoRepository realBaseInfoRepository;

    public List<RealBaseInfo> getAllRealBaseInfos() {
        List<RealBaseInfo> realBaseInfos = new ArrayList<RealBaseInfo>();
        realBaseInfoRepository.findAll().forEach(e -> realBaseInfos.add(e));
        return realBaseInfos;
    }


    public RealBaseInfo getRealBaseInfo(Long realBaseInfoId) {
        Optional<RealBaseInfo> realBaseInfo = realBaseInfoRepository.findById(realBaseInfoId);
        if(realBaseInfo.isPresent()) {
            return realBaseInfo.get();
        } else {
            return new RealBaseInfo(0L, "Unknown", "Unknown");
        }
    }

    public RealBaseInfo saveRealBaseInfo(RealBaseInfo realBaseInfo) {
        return realBaseInfoRepository.save(realBaseInfo);
    }

}

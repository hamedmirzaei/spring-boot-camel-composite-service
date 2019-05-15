package spring.boot.camel.composite.service.baseinfoservice.service;

import spring.boot.camel.composite.service.baseinfoservice.model.BaseInfo;
import spring.boot.camel.composite.service.baseinfoservice.repository.BaseInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BaseInfoService {

    @Autowired
    BaseInfoRepository baseInfoRepository;

    public List<BaseInfo> getAllBaseInfos() {
        List<BaseInfo> baseInfos = new ArrayList<BaseInfo>();
        baseInfoRepository.findAll().forEach(e -> baseInfos.add(e));
        return baseInfos;
    }


    public BaseInfo getBaseInfo(Long baseInfoId) {
        Optional<BaseInfo> baseInfo = baseInfoRepository.findById(baseInfoId);
        if(baseInfo.isPresent()) {
            return baseInfo.get();
        } else {
            return new BaseInfo(0L, "Unknown", "Unknown");
        }
    }

    public BaseInfo saveBaseInfo(BaseInfo baseInfo) {
        return baseInfoRepository.save(baseInfo);
    }

}

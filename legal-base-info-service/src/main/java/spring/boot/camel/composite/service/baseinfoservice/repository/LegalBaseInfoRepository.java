package spring.boot.camel.composite.service.baseinfoservice.repository;

import spring.boot.camel.composite.service.baseinfoservice.model.LegalBaseInfo;
import org.springframework.data.repository.CrudRepository;

public interface LegalBaseInfoRepository extends CrudRepository<LegalBaseInfo, Long> {
}

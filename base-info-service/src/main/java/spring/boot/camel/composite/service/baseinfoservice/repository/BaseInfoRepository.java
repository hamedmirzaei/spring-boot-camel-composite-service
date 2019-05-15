package spring.boot.camel.composite.service.baseinfoservice.repository;

import spring.boot.camel.composite.service.baseinfoservice.model.BaseInfo;
import org.springframework.data.repository.CrudRepository;

public interface BaseInfoRepository extends CrudRepository<BaseInfo, Long> {
}

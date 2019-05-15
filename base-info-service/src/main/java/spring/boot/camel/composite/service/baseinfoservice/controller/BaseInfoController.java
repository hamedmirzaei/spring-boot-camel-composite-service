package spring.boot.camel.composite.service.baseinfoservice.controller;

import spring.boot.camel.composite.service.baseinfoservice.model.BaseInfo;
import spring.boot.camel.composite.service.baseinfoservice.service.BaseInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/baseinfos")
public class BaseInfoController {

    @Autowired
    BaseInfoService baseInfoService;

    @GetMapping("")
    public ResponseEntity<List<BaseInfo>> getAllBaseInfos() {
        return ResponseEntity.ok(baseInfoService.getAllBaseInfos());
    }

    @GetMapping("/id/{biid}")
    public ResponseEntity<BaseInfo> getBaseInfo(@PathVariable("biid") Long baseInfoId) {
        return ResponseEntity.ok(baseInfoService.getBaseInfo(baseInfoId));
    }

    @GetMapping("/id")
    public ResponseEntity<BaseInfo> getBaseInfoByRequestParam(@RequestParam("biid") Long baseInfoId) {
        return ResponseEntity.ok(baseInfoService.getBaseInfo(baseInfoId));
    }

}

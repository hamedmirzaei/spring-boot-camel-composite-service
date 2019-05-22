package spring.boot.camel.composite.service.baseinfoservice.controller;

import spring.boot.camel.composite.service.baseinfoservice.model.LegalBaseInfo;
import spring.boot.camel.composite.service.baseinfoservice.service.LegalBaseInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/legalbaseinfos")
public class LegalBaseInfoController {

    @Autowired
    LegalBaseInfoService legalBaseInfoService;

    @GetMapping("")
    public ResponseEntity<List<LegalBaseInfo>> getAllLegalBaseInfos() {
        return ResponseEntity.ok(legalBaseInfoService.getAllLegalBaseInfos());
    }

    @GetMapping("/id/{lbiid}")
    public ResponseEntity<LegalBaseInfo> getLegalBaseInfo(@PathVariable("lbiid") Long legalBaseInfoId) {
        return ResponseEntity.ok(legalBaseInfoService.getLegalBaseInfo(legalBaseInfoId));
    }

    @GetMapping("/id")
    public ResponseEntity<LegalBaseInfo> getLegalBaseInfoByRequestParam(@RequestParam("lbiid") Long legalBaseInfoId) {
        return ResponseEntity.ok(legalBaseInfoService.getLegalBaseInfo(legalBaseInfoId));
    }

}

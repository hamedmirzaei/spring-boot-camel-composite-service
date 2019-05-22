package spring.boot.camel.composite.service.baseinfoservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.boot.camel.composite.service.baseinfoservice.model.RealBaseInfo;
import spring.boot.camel.composite.service.baseinfoservice.service.RealBaseInfoService;

import java.util.List;

@RestController
@RequestMapping("/realbaseinfos")
public class RealBaseInfoController {

    @Autowired
    RealBaseInfoService realBaseInfoService;

    @GetMapping("")
    public ResponseEntity<List<RealBaseInfo>> getAllRealBaseInfos() {
        return ResponseEntity.ok(realBaseInfoService.getAllRealBaseInfos());
    }

    @GetMapping("/id/{rbiid}")
    public ResponseEntity<RealBaseInfo> getRealBaseInfo(@PathVariable("rbiid") Long realBaseInfoId) {
        return ResponseEntity.ok(realBaseInfoService.getRealBaseInfo(realBaseInfoId));
    }

    @GetMapping("/id")
    public ResponseEntity<RealBaseInfo> getRealBaseInfoByRequestParam(@RequestParam("rbiid") Long realBbaseInfoId) {
        return ResponseEntity.ok(realBaseInfoService.getRealBaseInfo(realBbaseInfoId));
    }

}

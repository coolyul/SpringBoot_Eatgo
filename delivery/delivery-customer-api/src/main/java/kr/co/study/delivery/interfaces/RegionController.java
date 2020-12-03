package kr.co.study.delivery.interfaces;


import kr.co.study.delivery.application.RegionService;
import kr.co.study.delivery.domain.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RegionController {

    @Autowired
    private RegionService regionService;

    @GetMapping("/regions")
    public List<Region> list(){
        List<Region> regions = regionService.getRegions();

        return regions;
    }




}
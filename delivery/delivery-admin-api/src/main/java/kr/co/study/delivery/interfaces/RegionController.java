package kr.co.study.delivery.interfaces;

import com.sun.media.sound.DLSInstrument;
import kr.co.study.delivery.application.RegionService;
import kr.co.study.delivery.domain.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
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


    @PostMapping("/regions")
    public ResponseEntity<?> create(@RequestBody Region resource) throws URISyntaxException {


        String name = resource.getName();       //@RequestBody 해서 받아오기
        Region region = regionService.addRegion(name);  // region에 add 해서 region 생성

        String url = "/regions/" + region.getId();      // region의 id로 url 설정
        return ResponseEntity.created(new URI(url)).body("{}");
    }


}

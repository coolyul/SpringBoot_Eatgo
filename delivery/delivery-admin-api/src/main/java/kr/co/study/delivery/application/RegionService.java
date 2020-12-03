package kr.co.study.delivery.application;

import kr.co.study.delivery.domain.Region;
import kr.co.study.delivery.domain.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegionService {

    private RegionRepository regionRepository;

    @Autowired
    public RegionService(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }



    // region list 받아오기
    public List<Region> getRegions() {

        List<Region> regions = regionRepository.findAll();

        return regions;
    }




    // region 추가하기
    public Region addRegion(String name) {

        Region region = Region.builder().name(name).build();

        regionRepository.save(region);

        return region;
    }
}

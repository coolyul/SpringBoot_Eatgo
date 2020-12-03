package kr.co.study.delivery.interfaces;

import kr.co.study.delivery.application.RegionService;
import kr.co.study.delivery.domain.Region;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.StringContains.containsString;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(RegionController.class)
class RegionControllerTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private RegionService regionService;



    @Test       // 지역의 리스트를 받아오는 테스트
    public void list() throws Exception {

        List<Region> regions = new ArrayList<>();
        regions.add(Region.builder().name("Seoul").build());

        given(regionService.getRegions()).willReturn(regions);

        mvc.perform(get("/regions"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Seoul")));
    }



    @Test       // 지역을 생성하는 테스트
    public void created() throws Exception {

        Region region = Region.builder().name("Seoul").build();

        given(regionService.addRegion("Seoul")).willReturn(region);

        mvc.perform(post("/regions")
                .contentType(MediaType.APPLICATION_JSON)        // 아래처럼 JSON형태로 넘겨줄 땐 꼭 작성!
                .content("{\"name\":\"Seoul\"}"))
                .andExpect(status().isCreated())
                .andExpect(content().string("{}"));

        verify(regionService).addRegion("Seoul");

    }



}
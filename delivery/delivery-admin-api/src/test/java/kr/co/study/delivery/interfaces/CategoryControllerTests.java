package kr.co.study.delivery.interfaces;

import kr.co.study.delivery.application.CategoryService;
import kr.co.study.delivery.domain.Category;
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
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(CategoryController.class)
class CategoryControllerTests {


    @Autowired
    private MockMvc mvc;

    @MockBean
    private CategoryService categoryService;



    @Test       // 지역의 리스트를 받아오는 테스트
    public void list() throws Exception {

        List<Category> categories = new ArrayList<>();
        categories.add(Category.builder().name("Korean Food").build());

        given(categoryService.getCategory()).willReturn(categories);

        mvc.perform(get("/categories"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Korean Food")));
    }



    @Test       // 지역을 생성하는 테스트
    public void created() throws Exception {

        Category category = Category.builder().name("Korean Food").build();

        given(categoryService.addCategory("Korean Food")).willReturn(category);

        mvc.perform(post("/categories")
                .contentType(MediaType.APPLICATION_JSON)        // 아래처럼 JSON형태로 넘겨줄 땐 꼭 작성!
                .content("{\"name\":\"Korean Food\"}"))
                .andExpect(status().isCreated())
                .andExpect(content().string("{}"));

        verify(categoryService).addCategory("Korean Food");

    }


}
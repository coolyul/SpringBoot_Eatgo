package kr.co.study.delivery.interfaces;

import kr.co.study.delivery.application.CategoryService;
import kr.co.study.delivery.domain.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/categories")
    public List<Category> list(){
        List<Category> regions = categoryService.getCategory();

        return regions;
    }


    @PostMapping("/categories")
    public ResponseEntity<?> create(@RequestBody Category resource) throws URISyntaxException {


        String name = resource.getName();       //@RequestBody 해서 받아오기
        Category category = categoryService.addCategory(name);  // region에 add 해서 region 생성

        String url = "/category/" + category.getId();      // region의 id로 url 설정
        return ResponseEntity.created(new URI(url)).body("{}");
    }


}

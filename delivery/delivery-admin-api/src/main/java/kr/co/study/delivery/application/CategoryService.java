package kr.co.study.delivery.application;

import kr.co.study.delivery.domain.Category;
import kr.co.study.delivery.domain.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }



    // region list 받아오기
    public List<Category> getCategory() {

        List<Category> categories = categoryRepository.findAll();

        return categories;
    }




    // region 추가하기
    public Category addCategory(String name) {

        Category category = Category.builder().name(name).build();

        categoryRepository.save(category);

        return category;
    }
}

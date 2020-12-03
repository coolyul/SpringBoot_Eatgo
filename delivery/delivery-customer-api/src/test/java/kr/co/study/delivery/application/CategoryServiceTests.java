package kr.co.study.delivery.application;

import kr.co.study.delivery.domain.Category;
import kr.co.study.delivery.domain.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

class CategoryServiceTests {

    @InjectMocks
    private CategoryService categoryService;

    @Mock
    private CategoryRepository categoryRepository;

    @BeforeEach
    public void setUp(){

        MockitoAnnotations.openMocks(this);

        categoryService = new CategoryService(categoryRepository);
    }

    @Test
    public void getRegions() {
        List<Category> mockCategory = new ArrayList<>();
        mockCategory.add(Category.builder().name("Korean Food").build());

        given(categoryRepository.findAll()).willReturn(mockCategory);

        List<Category> categories = categoryService.getCategory();

        Category category = categories.get(0);
        assertThat(category.getName()).isEqualTo("Korean Food");
    }


}
package ra.hn_jv231229_projectmd5_nhathuoc.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ra.hn_jv231229_projectmd5_nhathuoc.dto.request.CategoryRequest;
import ra.hn_jv231229_projectmd5_nhathuoc.model.Category;
import ra.hn_jv231229_projectmd5_nhathuoc.repository.ICategoryRepository;
import ra.hn_jv231229_projectmd5_nhathuoc.service.ICategoryService;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements ICategoryService {
    private final ICategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }

    @Override
    public Category saveCategory(CategoryRequest categoryRequest) {
        Category category = Category.builder()
                .categoryName(categoryRequest.getCategoryName())
                .image(categoryRequest.getImage())
                .createdAt(new Date())
                .description(categoryRequest.getDescription())
                .status(categoryRequest.getStatus())
                .build();
        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(Long categoryId, CategoryRequest categoryRequest) {
        Category updateCategory = categoryRepository.findById(categoryId).orElseThrow(()->new NoSuchElementException("Category not found. Can't update."));
        updateCategory.setCategoryName(categoryRequest.getCategoryName());
        updateCategory.setDescription(categoryRequest.getDescription());
        updateCategory.setStatus(categoryRequest.getStatus());
        updateCategory.setImage(categoryRequest.getImage());
        return categoryRepository.save(updateCategory);
    }

    @Override
    public Category deleteCategory(Long categoryId) {
        Category deletedCategory = categoryRepository.findById(categoryId).orElseThrow(()->new NoSuchElementException("Category not found. Can't delete."));
        categoryRepository.deleteById(categoryId);
        return deletedCategory;
    }

    @Override
    public Category findCategoryById(Long categoryId) {
        return categoryRepository.findById(categoryId).orElseThrow(()->new NoSuchElementException("Category not found. Can't find Category."));
    }
}

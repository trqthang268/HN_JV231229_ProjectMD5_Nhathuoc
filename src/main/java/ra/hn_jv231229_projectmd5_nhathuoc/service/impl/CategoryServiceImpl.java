package ra.hn_jv231229_projectmd5_nhathuoc.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ra.hn_jv231229_projectmd5_nhathuoc.dto.request.CategoryRequest;
import ra.hn_jv231229_projectmd5_nhathuoc.exception.CustomException;
import ra.hn_jv231229_projectmd5_nhathuoc.exception.DataExistException;
import ra.hn_jv231229_projectmd5_nhathuoc.model.Category;
import ra.hn_jv231229_projectmd5_nhathuoc.repository.ICategoryRepository;
import ra.hn_jv231229_projectmd5_nhathuoc.service.ICategoryService;
import ra.hn_jv231229_projectmd5_nhathuoc.service.UploadService;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements ICategoryService {
    private final ICategoryRepository categoryRepository;
    private final UploadService uploadService;

    @Override
    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }

    @Override
    public Page<Category> findAllCategory(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    @Override
    public Category saveCategory(CategoryRequest categoryRequest) throws DataExistException {
        if (categoryRepository.existsByCategoryName(categoryRequest.getCategoryName())){
            throw new DataExistException("Tên danh mục đã tồn tại","Danh mục");
        }
        Category category = Category.builder()
                .categoryName(categoryRequest.getCategoryName())
                .image(uploadService.uploadFileToServer(categoryRequest.getImage()))
                .createdAt(new Date())
                .description(categoryRequest.getDescription())
                .status(categoryRequest.getStatus())
                .build();
        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(Long categoryId, CategoryRequest categoryRequest) {
        Category updateCategory = categoryRepository.findById(categoryId).orElseThrow(() -> new NoSuchElementException("Category not found. Can't update."));
        if (categoryRequest.getCategoryName() != null) {
            updateCategory.setCategoryName(categoryRequest.getCategoryName());
        }
        if (categoryRequest.getImage() != null) {
            updateCategory.setImage(uploadService.uploadFileToServer(categoryRequest.getImage()));
        }
        if (categoryRequest.getDescription() != null) {
            updateCategory.setDescription(categoryRequest.getDescription());
        }
        if (categoryRequest.getStatus() != null) {
            updateCategory.setStatus(categoryRequest.getStatus());
        }

        return categoryRepository.save(updateCategory);
    }

    @Override
    public Category deleteCategory(Long categoryId) {
        Category deletedCategory = categoryRepository.findById(categoryId).orElseThrow(() -> new NoSuchElementException("Category not found. Can't delete."));
        categoryRepository.deleteById(categoryId);
        return deletedCategory;
    }

    @Override
    public Category findCategoryById(Long categoryId) {
        return categoryRepository.findById(categoryId).orElseThrow(() -> new NoSuchElementException("Category not found. Can't find Category."));
    }
}

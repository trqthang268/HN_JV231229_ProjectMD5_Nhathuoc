package ra.hn_jv231229_projectmd5_nhathuoc.service;

import ra.hn_jv231229_projectmd5_nhathuoc.dto.request.CategoryRequest;
import ra.hn_jv231229_projectmd5_nhathuoc.model.Category;

import java.util.List;

public interface ICategoryService {
    List<Category> getAllCategory();
    Category saveCategory(CategoryRequest categoryRequest);
    Category updateCategory(Long categoryId, CategoryRequest categoryRequest);
    Category deleteCategory(Long categoryId);
    Category findCategoryById(Long categoryId);
}

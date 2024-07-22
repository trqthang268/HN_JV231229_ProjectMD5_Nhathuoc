package ra.hn_jv231229_projectmd5_nhathuoc.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ra.hn_jv231229_projectmd5_nhathuoc.dto.request.CategoryRequest;
import ra.hn_jv231229_projectmd5_nhathuoc.exception.DataExistException;
import ra.hn_jv231229_projectmd5_nhathuoc.model.Category;

import java.util.List;

public interface ICategoryService {
    List<Category> getAllCategory();
    Page<Category> findAllCategory(Pageable pageable);
    Category saveCategory(CategoryRequest categoryRequest) throws DataExistException;
    Category updateCategory(Long categoryId, CategoryRequest categoryRequest);
    Category deleteCategory(Long categoryId);
    Category findCategoryById(Long categoryId);
}

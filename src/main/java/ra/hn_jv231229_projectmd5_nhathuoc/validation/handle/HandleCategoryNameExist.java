package ra.hn_jv231229_projectmd5_nhathuoc.validation.handle;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ra.hn_jv231229_projectmd5_nhathuoc.repository.ICategoryRepository;
import ra.hn_jv231229_projectmd5_nhathuoc.validation.annotation.CategoryNameExist;

@Component
public class HandleCategoryNameExist implements ConstraintValidator<CategoryNameExist,String> {
    @Autowired
    private ICategoryRepository categoryRepository;

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return categoryRepository.findCategoryByCategoryName(s).isEmpty();
    }
}

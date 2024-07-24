package ra.hn_jv231229_projectmd5_nhathuoc.validation.handle;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ra.hn_jv231229_projectmd5_nhathuoc.repository.ICategoryRepository;
import ra.hn_jv231229_projectmd5_nhathuoc.repository.IProductRepository;
import ra.hn_jv231229_projectmd5_nhathuoc.validation.annotation.ProductNameExist;

@Component
public class HandleProductNameExist implements ConstraintValidator<ProductNameExist,String> {
    @Autowired
    private IProductRepository productRepository;

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return productRepository.findProductByProductName(s).isEmpty();
    }
}
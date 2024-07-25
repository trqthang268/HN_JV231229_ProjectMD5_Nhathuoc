package ra.hn_jv231229_projectmd5_nhathuoc.validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import ra.hn_jv231229_projectmd5_nhathuoc.validation.handle.HandleCategoryNameExist;

import java.lang.annotation.*;

@Constraint(
        validatedBy = {HandleCategoryNameExist.class}
)
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CategoryNameExist {
    String message() default "Tên danh mục đã tồn tại";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    @Target({ElementType.TYPE,ElementType.FIELD})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    public @interface List {
        CategoryNameExist[] value();
    }
}

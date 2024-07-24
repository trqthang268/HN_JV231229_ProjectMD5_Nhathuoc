package ra.hn_jv231229_projectmd5_nhathuoc.validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import ra.hn_jv231229_projectmd5_nhathuoc.validation.handle.HandleProductNameExist;

import java.lang.annotation.*;

@Constraint(
        validatedBy = {HandleProductNameExist.class}
)
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ProductNameExist {
    String message() default "Tên sản phẩm đã tồn tại";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    @Target({ElementType.TYPE,ElementType.FIELD})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    public @interface List {
        ProductNameExist[] value();
    }
}

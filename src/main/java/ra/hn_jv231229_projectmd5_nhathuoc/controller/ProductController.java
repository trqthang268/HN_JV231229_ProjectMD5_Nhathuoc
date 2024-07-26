package ra.hn_jv231229_projectmd5_nhathuoc.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.hn_jv231229_projectmd5_nhathuoc.dto.response.ResponseWrapper;
import ra.hn_jv231229_projectmd5_nhathuoc.service.ICategoryService;
import ra.hn_jv231229_projectmd5_nhathuoc.service.IProductService;

@RestController
@RequestMapping("api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final IProductService productService;
    private final ICategoryService categoryService;
    /**
     * Phân trang product
     */
    @GetMapping("/list-product")
    public ResponseEntity<?> GetProducts(
            @RequestParam(defaultValue = "") String searchPro,
            @PageableDefault(page = 0, size = 10,sort = "id",direction = Sort.Direction.ASC) Pageable pageable) {
        return new ResponseEntity<>(ResponseWrapper.builder()
                .statusCode(HttpStatus.OK.value())
                .httpStatus(HttpStatus.OK)
                .data(productService.findAllByProductName(pageable,searchPro))
                .build(), HttpStatus.OK);

    }


    /**
     * feature-9043 : sản phẩm theo danh mục
     * @param categoryId id danh mục
     * @param pageable phân trang
     * @return trả về danh sách sản phẩm theo danh mục
     */
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<?> getProductsByCategory(
            @PathVariable Long categoryId,
            @PageableDefault(page = 0,size = 10,sort = "id",direction = Sort.Direction.ASC) Pageable pageable) {
        return new ResponseEntity<>(ResponseWrapper.builder()
                .statusCode(HttpStatus.OK.value())
                .httpStatus(HttpStatus.OK)
                .data(productService.findProductsByCategoryId(pageable,categoryId))
                .build(),HttpStatus.OK);
    }
    @GetMapping("/categories")
    public ResponseEntity<?> getCategories(){
        return new ResponseEntity<>(ResponseWrapper.builder()
                .statusCode(HttpStatus.OK.value())
                .httpStatus(HttpStatus.OK)
                .data(categoryService.getAllCategory())
                .build(),HttpStatus.OK);
    }

}

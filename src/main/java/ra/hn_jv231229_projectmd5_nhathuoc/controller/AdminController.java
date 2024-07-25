package ra.hn_jv231229_projectmd5_nhathuoc.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.hn_jv231229_projectmd5_nhathuoc.dto.request.BannerRequest;
import ra.hn_jv231229_projectmd5_nhathuoc.dto.request.CategoryRequest;
import ra.hn_jv231229_projectmd5_nhathuoc.dto.request.ProductRequest;
import ra.hn_jv231229_projectmd5_nhathuoc.dto.request.ProductUpdateRequest;
import ra.hn_jv231229_projectmd5_nhathuoc.dto.response.ResponseWrapper;
import ra.hn_jv231229_projectmd5_nhathuoc.exception.CustomException;
import ra.hn_jv231229_projectmd5_nhathuoc.exception.DataExistException;
import ra.hn_jv231229_projectmd5_nhathuoc.model.ProductDetail;
import ra.hn_jv231229_projectmd5_nhathuoc.service.IBrandService;
import ra.hn_jv231229_projectmd5_nhathuoc.service.ICategoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import ra.hn_jv231229_projectmd5_nhathuoc.model.User;
import ra.hn_jv231229_projectmd5_nhathuoc.service.IProductDetailService;
import ra.hn_jv231229_projectmd5_nhathuoc.service.impl.BannerServiceImpl;
import ra.hn_jv231229_projectmd5_nhathuoc.service.IProductService;
import ra.hn_jv231229_projectmd5_nhathuoc.service.impl.UserService;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class AdminController {
    private final ICategoryService categoryService;
    private final IProductService productService;
    private final UserService userService;
    private final BannerServiceImpl bannerService;
    private final IBrandService brandService;
    private final IProductDetailService productDetailService;


    /**
     * CATEGORY MANAGEMENT
     */
    /**
     * feature-8978: hiển thị danh sách danh mục
     * @apiNote lấy toàn bộ danh mục
     */
    @GetMapping("/list-category")
    public ResponseEntity<?> getAllCategory(@RequestParam(defaultValue = "0") int page,
                                            @RequestParam(defaultValue = "2") int size,
                                            @RequestParam(defaultValue = "id") String sortBy,
                                            @RequestParam(defaultValue = "asc") String sortDir) {
        Sort.Direction direction = sortDir.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
        Pageable pageable = PageRequest.of(page,size, Sort.by(direction,sortBy));
            return new ResponseEntity<>(ResponseWrapper
                .builder()
                .httpStatus(HttpStatus.OK)
                .data(categoryService.findAllCategory(pageable))
                .statusCode(HttpStatus.OK.value()).build(),HttpStatus.OK);
    }

    //Hàm lấy toàn bộ category không phân trang
    @GetMapping("/categories")
    public ResponseEntity<?> getAllCategories(){
        return new ResponseEntity<>(ResponseWrapper
                .builder()
                .statusCode(HttpStatus.OK.value())
                .data(categoryService.getAllCategory())
                .httpStatus(HttpStatus.OK)
                .build(),HttpStatus.OK);
    }
    //Hàm lấy catrgory theo id
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<?> getCategory(@PathVariable Long categoryId){
        return new ResponseEntity<>(ResponseWrapper
                .builder()
                .statusCode(HttpStatus.OK.value())
                .data(categoryService.findCategoryById(categoryId))
                .httpStatus(HttpStatus.OK)
                .build(),HttpStatus.OK);
    }
    //hàm lấy toàn bộ brands
    @GetMapping("/brands")
    public ResponseEntity<?> getAllBrands(){
        return new ResponseEntity<>(ResponseWrapper
                .builder()
                .statusCode(HttpStatus.OK.value())
                .data(brandService.getAllBrands())
                .httpStatus(HttpStatus.OK)
                .build(),HttpStatus.OK);
    }

    /**
     * feature-8979: thêm sửa xóa danh mục
     * @param categoryRequest thông tin gửi cho phía sever
     * @return trả về danh mục mới
     */
    @PostMapping("/category")
    public ResponseEntity<?> addCategory(@Valid @ModelAttribute CategoryRequest categoryRequest) throws DataExistException {
        return new ResponseEntity<>(ResponseWrapper
                .builder()
                .statusCode(HttpStatus.CREATED.value())
                .data(categoryService.saveCategory(categoryRequest))
                .httpStatus(HttpStatus.CREATED).build(),HttpStatus.CREATED);
    }

    /**
     *feature-8979: thêm sửa xóa danh mục
     * @param categoryId id cũ  của danh mục cần sửa
     * @param categoryRequest thông tin mới của danh mục cần sửa
     * @return trả về danh mục đã sửa
     */
    @PutMapping("/category/{categoryId}")
    public ResponseEntity<?> updateCategory(@PathVariable Long categoryId, @Valid @ModelAttribute CategoryRequest categoryRequest) {
        return new ResponseEntity<>(ResponseWrapper
                .builder()
                .statusCode(HttpStatus.OK.value())
                .data(categoryService.updateCategory(categoryId,categoryRequest))
                .httpStatus(HttpStatus.OK).build(),HttpStatus.OK);
    }

    /**
     * feature-8979: thêm sửa xóa danh mục
     * @param categoryId id danh mục muốn xóa
     * @return trả về danh mục đã xóa
     */
    @DeleteMapping("/category/{categoryId}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long categoryId) {
        return new ResponseEntity<>(ResponseWrapper
                .builder()
                .statusCode(HttpStatus.NO_CONTENT.value())
                .data(categoryService.deleteCategory(categoryId))
                .httpStatus(HttpStatus.NO_CONTENT)
                .build(),HttpStatus.NO_CONTENT);
    }
    /**
     * PRODUCT MANAGEMENT
     */

    /**
     * feature-8980-9039
     * [ROLE_ADMIN] hiển thị danh sách sản phẩm + phân trang
     * @param page trang hiẹn tại
     * @param size số san pham 1 trang
     * @param sortBy sap xep theo trường
     * @param sortDir hướng sắp xếp
     * @return
     */
    @GetMapping("/list-product")
    public ResponseEntity<?> getAllProduct(@RequestParam(defaultValue = "0") int page,
                                           @RequestParam(defaultValue = "2") int size,
                                           @RequestParam(defaultValue = "id") String sortBy,
                                           @RequestParam(defaultValue = "asc") String sortDir){
        Sort.Direction direction = sortDir.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
        Pageable pageable = PageRequest.of(page,size, Sort.by(direction,sortBy));
        return new ResponseEntity<>(ResponseWrapper
                .builder()
                .httpStatus(HttpStatus.OK)
                .data(productService.findAllProduct(pageable))
                .statusCode(HttpStatus.OK.value()).build(),HttpStatus.OK);
    }

    /**
     * thêm mới sản phẩm feature-8981
     * @param productRequest thông tin product cần gửi để tạo mới
     * @return trả về sản phẩm mới được thêm
     */
    @PostMapping("/addProduct")
    public ResponseEntity<?> addProduct(@Valid @ModelAttribute ProductRequest productRequest) {
        return new ResponseEntity<>(ResponseWrapper.builder()
                .httpStatus(HttpStatus.CREATED)
                .statusCode(HttpStatus.CREATED.value())
                .data(productService.createProduct(productRequest))
                .build(),HttpStatus.CREATED);
    }

    /**
     * chỉnh sửa sản phẩm feature-8982
     * @param productId id của product cũ
     * @param productUpdateRequest thông tin mới được gửi lên để chỉnh sửa
     * @return trả về sản phẩm mới được chỉnh sửa
     */
    @PutMapping("/product/{productId}")
    public ResponseEntity<?> updateProduct(@PathVariable Long productId, @Valid @ModelAttribute ProductUpdateRequest productUpdateRequest) {
        return new ResponseEntity<>(ResponseWrapper.builder()
                .httpStatus(HttpStatus.OK)
                .data(productService.updateProduct(productId,productUpdateRequest))
                .statusCode(HttpStatus.OK.value())
                .build(),HttpStatus.OK);
    }

    /**
     * xoá sản phẩm(block-unblock product) : feature-9037
     * @param productId id của sản phẩm cần block
     * @return trả về sản phẩm được khóa hoặc được mở khóa
     */
    @PutMapping("/productStatus/{productId}")
    public ResponseEntity<?> changeStatusProduct(@PathVariable Long productId) {
        return new ResponseEntity<>(ResponseWrapper.builder()
                .httpStatus(HttpStatus.OK)
                .data(productService.changeStatusProduct(productId))
                .statusCode(HttpStatus.OK.value())
                .build(),HttpStatus.OK);
    }

    /**
     * tìm kiếm sản phẩm : feature-9038
     * @param searchPro Tên sản phẩm hoặc mô tả sản phẩm
     * @param page số trang bắt đầu
     * @param size số sản phẩm trong 1 trang
     * @param sortBy sắp xếp theo trường nào
     * @param sortDir hướng sắp xếp
     * @return trả về danh sách có thông tin cần tìm kiếm
     */
    @GetMapping("/searchProduct")
    public ResponseEntity<?> searchProduct(@RequestParam(defaultValue = "") String searchPro,
                                           @RequestParam(defaultValue = "0") int page,
                                           @RequestParam(defaultValue = "5") int size,
                                           @RequestParam(defaultValue = "id") String sortBy,
                                           @RequestParam(defaultValue = "asc") String sortDir){
        Sort.Direction direction = sortDir.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
        Pageable pageable = PageRequest.of(page,size, Sort.by(direction,sortBy));
        return new ResponseEntity<>(ResponseWrapper.builder()
                .httpStatus(HttpStatus.OK)
                .data(productService.findAllByProductName(pageable,searchPro))
                .statusCode(HttpStatus.OK.value())
                .build(),HttpStatus.OK);
    }

    /**
     * lọc sản phẩm theo Danh mục : feature-9040
     * @param categoryId id danh mục cần lọc
     * @param page số trang bắt đầu
     * @param size số sản phẩm trong 1 trang
     * @param sortBy sắp xếp theo trường nào
     * @param sortDir hướng sắp xếp
     * @return trả về danh sách sản phẩm trong danh mục
     */
    @GetMapping("/list-product-by-category/{categoryId}")
    public ResponseEntity<?> getAllProduct(@PathVariable Long categoryId,
                                           @RequestParam(defaultValue = "0") int page,
                                           @RequestParam(defaultValue = "5") int size,
                                           @RequestParam(defaultValue = "id") String sortBy,
                                           @RequestParam(defaultValue = "asc") String sortDir){
        Sort.Direction direction = sortDir.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
        Pageable pageable = PageRequest.of(page,size, Sort.by(direction,sortBy));
        return new ResponseEntity<>(ResponseWrapper.builder()
                .httpStatus(HttpStatus.OK)
                .data(productService.findProductsByCategoryId(pageable,categoryId))
                .statusCode(HttpStatus.OK.value())
                .build(),HttpStatus.OK);
    }

    /**
     * feature-9041 chính sửa stock sản phẩm
     * @param productId Id của sản phẩm
     * @param newStock stock mới của sản phẩm
     * @return trả về chi tiết sản phẩm mới
     * @throws CustomException trả về lỗi khi stock mới nhỏ hơn 0
     */
    @PutMapping("/stock/{productId}")
    public ResponseEntity<?> changeStockProduct(@PathVariable Long productId,
                                                @RequestParam Integer newStock) throws CustomException {
        ProductDetail productDetail = productDetailService.updateStock(productId,newStock);
        return new ResponseEntity<>(ResponseWrapper.builder()
                .statusCode(HttpStatus.OK.value())
                .data(productDetail)
                .httpStatus(HttpStatus.OK)
                .build(),HttpStatus.OK);
    }

    /**
     * lấy ra prodyct detail theo productId
     * @param productId id sản phẩm
     * @return trả về thông tin chi tiết sản phẩm
     */
    @GetMapping("product-detail/{productId}")
    public ResponseEntity<?> getProductDetail(@PathVariable Long productId) {
        return new ResponseEntity<>(ResponseWrapper.builder()
                .statusCode(HttpStatus.OK.value())
                .data(productDetailService.findProductDetailByProductId(productId))
                .httpStatus(HttpStatus.OK)
                .build(),HttpStatus.OK);
    }

    /**
     * USER MANAGEMENT
     */
    /**
 * tìm kiếm, sắp xếp, phân trang user
 * */
    @GetMapping("/getuser")
    public ResponseEntity<Page<User>> findAll(
            @PageableDefault(page = 0, size = 5, sort = "id", direction = Sort.Direction.ASC) Pageable pageable,
            @RequestParam(defaultValue = "") String search
    ) {
        return ResponseEntity.ok().body(userService.findAll(pageable, search));
    }

    /**
     * tìm kiếm user
     **/
    @GetMapping("/searchUser")
    public ResponseEntity<?> searchUser(@RequestParam String search) {
        if(search == null || search.isEmpty()) {
            return  ResponseEntity.ok().body(userService.findAll(PageRequest.of(0, 10, Sort.Direction.ASC, "id"),""));
        }
        return new ResponseEntity<>( userService.findByName(search), HttpStatus.OK);
    }

    /**
     * khóa, mở khóa user
     * */
    @PutMapping("/lockUser")
    public ResponseEntity<?> lockUser(@RequestParam Long id) {
       Boolean isLock = userService.LockUser(id);
       if (isLock) {
           return new ResponseEntity<>("Success",HttpStatus.OK);
       }
       return new ResponseEntity<>("Fail",HttpStatus.OK);
    }


    /***
     *
     * add banner
     */
    @PostMapping("/banner")
    public ResponseEntity<?> addBanner(@Valid @ModelAttribute BannerRequest bannerRequest) {
        return new ResponseEntity<>(bannerService.addBanner(bannerRequest),HttpStatus.OK);
    }
    /***
     *
     * get banner
     */
    @GetMapping("/banner")
    public ResponseEntity<?> getBanner( ) {
        return new ResponseEntity<>(bannerService.getBanners(),HttpStatus.OK);
    }
    /***
     *
     * change status banner
     */
    @PutMapping("/banner")
    public ResponseEntity<?> setStatusBanner(@RequestParam Integer id ) {
        return new ResponseEntity<>(bannerService.updateBanner(id),HttpStatus.OK);
    }


}

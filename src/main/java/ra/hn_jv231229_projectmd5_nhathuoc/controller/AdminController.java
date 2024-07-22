package ra.hn_jv231229_projectmd5_nhathuoc.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.hn_jv231229_projectmd5_nhathuoc.dto.request.CategoryRequest;
import ra.hn_jv231229_projectmd5_nhathuoc.dto.response.ResponseWrapper;
import ra.hn_jv231229_projectmd5_nhathuoc.service.ICategoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import ra.hn_jv231229_projectmd5_nhathuoc.model.User;
import ra.hn_jv231229_projectmd5_nhathuoc.service.impl.UserService;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class AdminController {
    private final ICategoryService categoryService;
    private final UserService userService;

    /**
     * feature-8978: hiển thị danh sách danh mục
     * @apiNote lấy toàn bộ danh mục
     */
    @GetMapping("/list-category")
    public ResponseEntity<?> getAllCategory() {
            return new ResponseEntity<>(ResponseWrapper
                .builder()
                .httpStatus(HttpStatus.OK)
                .data(categoryService.getAllCategory())
                .statusCode(HttpStatus.OK.value()).build(),HttpStatus.OK);
    }

    /**
     * feature-8979: thêm sửa xóa danh mục
     * @param categoryRequest thông tin gửi cho phía sever
     * @return trả về danh mục mới
     */
    @PostMapping("/category")
    public ResponseEntity<?> addCategory(@Valid @RequestBody CategoryRequest categoryRequest) {
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
    public ResponseEntity<?> updateCategory(@PathVariable Long categoryId, @Valid @RequestBody CategoryRequest categoryRequest) {
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

}

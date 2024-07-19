package ra.hn_jv231229_projectmd5_nhathuoc.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.hn_jv231229_projectmd5_nhathuoc.model.User;
import ra.hn_jv231229_projectmd5_nhathuoc.service.impl.UserService;

@RestController
@RequestMapping("api/v1/admin")
public class AdminController {
    @Autowired
    UserService userService;
    @GetMapping("/getuser")
    public ResponseEntity<Page<User>> findAll(
            @PageableDefault(page = 0, size = 5, sort = "id", direction = Sort.Direction.ASC) Pageable pageable,
            @RequestParam(defaultValue = "") String search
    ) {

        return ResponseEntity.ok().body(userService.findAll(pageable, search));
    }
    @GetMapping("/searchUser")
    public ResponseEntity<?> searchUser(@RequestParam String search) {
        return new ResponseEntity<>( userService.findByName(search), HttpStatus.OK);
    }

    @PutMapping("/lockUser")
    public ResponseEntity<?> lockUser(@RequestParam Long id) {
       Boolean isLock = userService.LockUser(id);
       if (isLock) {
           return new ResponseEntity<>("Success",HttpStatus.OK);
       }
       return new ResponseEntity<>("Fail",HttpStatus.OK);
    }

}

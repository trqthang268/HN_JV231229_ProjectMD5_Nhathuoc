package ra.hn_jv231229_projectmd5_nhathuoc.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.hn_jv231229_projectmd5_nhathuoc.model.User;
import ra.hn_jv231229_projectmd5_nhathuoc.service.impl.UserService;

@RestController
@RequestMapping("api/v1/user")
public class UserController {
    @Autowired
    UserService userService;

    @PutMapping("/editUser")
    public ResponseEntity<?> editUser(@RequestBody User user) {
        return new ResponseEntity<>(userService.updateUser(user), HttpStatus.OK);
    }

    @PutMapping("/changePassword")
    public ResponseEntity<?> changePassword(@RequestParam String phone, @RequestParam String newPassword,@RequestParam String oldPassword ) {
        return new ResponseEntity<>(userService.changePassword(phone ,newPassword,  oldPassword), HttpStatus.OK);
    }

}

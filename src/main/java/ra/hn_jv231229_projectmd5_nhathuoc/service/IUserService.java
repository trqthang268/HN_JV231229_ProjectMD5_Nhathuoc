package ra.hn_jv231229_projectmd5_nhathuoc.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ra.hn_jv231229_projectmd5_nhathuoc.model.Product;
import ra.hn_jv231229_projectmd5_nhathuoc.model.User;
import ra.hn_jv231229_projectmd5_nhathuoc.model.WishList;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    Page<User> findAll(Pageable pageable, String search);
    List<User> findByName(String search);
    Boolean LockUser(Long id);
    Boolean findByPhone(String phone);
    User updateUser(User user);
    User changePassword(String phone,String oldPassword, String newPassword);
    WishList addToWishList(Long id ,String phone);
    Page<WishList> getFromWishList(Pageable pageable);
    void deleteFromWishList(Integer id);
}

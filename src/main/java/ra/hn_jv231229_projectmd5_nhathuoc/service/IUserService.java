package ra.hn_jv231229_projectmd5_nhathuoc.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ra.hn_jv231229_projectmd5_nhathuoc.model.User;

import java.util.List;

public interface IUserService {
    Page<User> findAll(Pageable pageable, String search);
    List<User> findByName(String search);
    Boolean LockUser(Long id);
    Boolean findByPhone(String phone);
}

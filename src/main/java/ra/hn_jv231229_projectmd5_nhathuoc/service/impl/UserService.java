package ra.hn_jv231229_projectmd5_nhathuoc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ra.hn_jv231229_projectmd5_nhathuoc.model.User;
import ra.hn_jv231229_projectmd5_nhathuoc.repository.IUserRepository;
import ra.hn_jv231229_projectmd5_nhathuoc.service.IUserService;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {
    //tìm kiếm, sắp xếp và phân trang Quản lý User
    @Autowired
    IUserRepository userRepository;
    @Override
    public Page<User> findAll(Pageable pageable, String search) {
        Page<User> users;
        if (search.isEmpty()) {
            users = userRepository.findAll(pageable);
        } else {
            users = userRepository.findAllByUsernameContains(search, pageable);
        }
        return users;
    }
//Tìm kiếm User
    @Override
    public List<User> findByName(String search) {
        User user = userRepository.findUserByUsername(search)
                .orElseThrow(() -> new RuntimeException("không tìm thấy user này"));
        return List.of(user);
    }
//Khóa mở
    @Override
    public Boolean LockUser(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            return false;
        }
        else {
            user.setStatus(!user.getStatus());
        }
        userRepository.save(user);
        return true;
    }

    @Override
    public Boolean findByPhone(String phone) {
      User user =   userRepository.findByPhone(phone);
        return user != null;
    }

    @Override
    public User updateUser(User user) {
        // Find the existing user in the repository based on the phone number
        User updateUser = userRepository.findByPhone(user.getPhone());
        Date date = new Date();
        // Update the existing user's properties
        updateUser.setEmail(user.getEmail());
        updateUser.setAvatar(user.getAvatar());
        updateUser.setBirthDate(user.getBirthDate());
        updateUser.setUsername(user.getUsername());
        updateUser.setGender(user.getGender());
        updateUser.setUpdatedAt(date);
        // Save the updated user to the repository and return the saved user
        return userRepository.save(updateUser);
    }
}

package ra.hn_jv231229_projectmd5_nhathuoc.sercurity.principal;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ra.hn_jv231229_projectmd5_nhathuoc.model.User;
import ra.hn_jv231229_projectmd5_nhathuoc.repository.IUserRepository;

import java.util.Optional;

@Service
public class UserDetailCustomService implements UserDetailsService {
    @Autowired
    private IUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String phone) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findUserByPhone(phone);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return UserDetailCustom.builder()
                    .userId(user.getId())
                    .username(user.getUsername())
                    .email(user.getEmail())
                    .phone(user.getPhone())
                    .password(user.getPassword())
                    .createdAt(user.getCreatedAt())
                    .updatedAt(user.getUpdatedAt())
                    .status(user.getStatus())
                    .point(user.getPoint())
                    .avatar(user.getAvatar())
                    .birthDate(user.getBirthDate())
                    .gender(user.getGender())
                    .isDeleted(user.getIsDeleted())
                    .authorities(
                            user.getRoles().stream()
                                    .map(role -> new SimpleGrantedAuthority(role.getRoleName().name()))
                                    .toList()
                    )
                    .build();
        }
        throw new UsernameNotFoundException("Không tim thấy người dùng");
    }
}

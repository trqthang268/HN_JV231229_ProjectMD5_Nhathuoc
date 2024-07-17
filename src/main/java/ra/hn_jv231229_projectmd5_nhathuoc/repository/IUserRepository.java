package ra.hn_jv231229_projectmd5_nhathuoc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ra.hn_jv231229_projectmd5_nhathuoc.model.User;

import java.util.Optional;

@Repository
@Transactional
public interface IUserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByUsername(String username);
    Optional<User> findUserByPhone(String phone);
    Optional<User> findUserByEmail(String email);

    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
    boolean existsByPhone(String phone);
}

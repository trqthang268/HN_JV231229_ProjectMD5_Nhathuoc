package ra.hn_jv231229_projectmd5_nhathuoc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ra.hn_jv231229_projectmd5_nhathuoc.constants.RoleName;
import ra.hn_jv231229_projectmd5_nhathuoc.model.Role;

import java.util.Optional;

@Repository
@Transactional
public interface IRoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRoleName(RoleName roleName);
}

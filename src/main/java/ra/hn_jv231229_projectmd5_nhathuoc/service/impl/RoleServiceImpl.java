package ra.hn_jv231229_projectmd5_nhathuoc.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ra.hn_jv231229_projectmd5_nhathuoc.constants.RoleName;
import ra.hn_jv231229_projectmd5_nhathuoc.exception.CustomException;
import ra.hn_jv231229_projectmd5_nhathuoc.model.Role;
import ra.hn_jv231229_projectmd5_nhathuoc.repository.IRoleRepository;
import ra.hn_jv231229_projectmd5_nhathuoc.service.IRoleService;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements IRoleService {
    private final IRoleRepository roleRepository;

    @Override
    public Role findByRoleName(RoleName roleName){
        return roleRepository.findByRoleName(roleName).orElseThrow(() -> new NoSuchElementException("Role not found"));
    }
}

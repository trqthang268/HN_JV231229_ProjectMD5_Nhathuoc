package ra.hn_jv231229_projectmd5_nhathuoc.service;

import ra.hn_jv231229_projectmd5_nhathuoc.constants.RoleName;
import ra.hn_jv231229_projectmd5_nhathuoc.exception.CustomException;
import ra.hn_jv231229_projectmd5_nhathuoc.model.Role;

public interface IRoleService {
    Role findByRoleName(RoleName roleName) throws CustomException;
}

package ra.hn_jv231229_projectmd5_nhathuoc.service;

import ra.hn_jv231229_projectmd5_nhathuoc.dto.request.LoginRequest;
import ra.hn_jv231229_projectmd5_nhathuoc.dto.request.RegisterRequest;
import ra.hn_jv231229_projectmd5_nhathuoc.dto.response.JwtResponse;
import ra.hn_jv231229_projectmd5_nhathuoc.exception.CustomException;
import ra.hn_jv231229_projectmd5_nhathuoc.model.User;

public interface IAuthService {
    JwtResponse login(LoginRequest loginRequest) throws CustomException;
    User register(RegisterRequest registerRequest);

}

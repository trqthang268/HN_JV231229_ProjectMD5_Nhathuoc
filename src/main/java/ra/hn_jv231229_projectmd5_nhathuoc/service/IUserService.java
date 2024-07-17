package ra.hn_jv231229_projectmd5_nhathuoc.service;

import ra.hn_jv231229_projectmd5_nhathuoc.dto.request.LoginRequest;
import ra.hn_jv231229_projectmd5_nhathuoc.dto.request.RegisterRequest;
import ra.hn_jv231229_projectmd5_nhathuoc.dto.response.JwtResponse;
import ra.hn_jv231229_projectmd5_nhathuoc.exception.CustomException;

public interface IUserService {
    JwtResponse login(LoginRequest loginRequest) throws CustomException;
    void register(RegisterRequest registerRequest) throws CustomException;
}

package ra.hn_jv231229_projectmd5_nhathuoc.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ra.hn_jv231229_projectmd5_nhathuoc.dto.request.LoginRequest;
import ra.hn_jv231229_projectmd5_nhathuoc.dto.request.RegisterRequest;
import ra.hn_jv231229_projectmd5_nhathuoc.dto.response.JwtResponse;
import ra.hn_jv231229_projectmd5_nhathuoc.dto.response.ResponseWrapper;
import ra.hn_jv231229_projectmd5_nhathuoc.service.IAuthService;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final IAuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> handleRegister(@Valid @RequestBody RegisterRequest registerRequest) {
        return new ResponseEntity<>(ResponseWrapper.builder()
                .statusCode(HttpStatus.CREATED.value())
                .data(authService.register(registerRequest))
                .httpStatus(HttpStatus.CREATED)
                .build(),HttpStatus.CREATED);
    }

    @PostMapping("login")
    public ResponseEntity<?> handleLogin(@Valid @RequestBody LoginRequest loginRequest) {
        JwtResponse jwtResponse = authService.login(loginRequest);
        return new ResponseEntity<>(ResponseWrapper.builder()
                .statusCode(HttpStatus.OK.value())
                .data(jwtResponse)
                .httpStatus(HttpStatus.OK)
                .build(),HttpStatus.OK);
    }
}

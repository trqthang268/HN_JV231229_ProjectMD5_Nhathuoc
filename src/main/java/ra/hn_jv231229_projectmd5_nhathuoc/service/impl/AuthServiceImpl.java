package ra.hn_jv231229_projectmd5_nhathuoc.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ra.hn_jv231229_projectmd5_nhathuoc.constants.RoleName;
import ra.hn_jv231229_projectmd5_nhathuoc.dto.request.LoginRequest;
import ra.hn_jv231229_projectmd5_nhathuoc.dto.request.RegisterRequest;
import ra.hn_jv231229_projectmd5_nhathuoc.dto.response.JwtResponse;
import ra.hn_jv231229_projectmd5_nhathuoc.exception.CustomException;
import ra.hn_jv231229_projectmd5_nhathuoc.model.Role;
import ra.hn_jv231229_projectmd5_nhathuoc.model.User;
import ra.hn_jv231229_projectmd5_nhathuoc.repository.IUserRepository;
import ra.hn_jv231229_projectmd5_nhathuoc.sercurity.jwt.JwtProvider;
import ra.hn_jv231229_projectmd5_nhathuoc.sercurity.principal.UserDetailCustom;
import ra.hn_jv231229_projectmd5_nhathuoc.service.IAuthService;
import ra.hn_jv231229_projectmd5_nhathuoc.service.IRoleService;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements IAuthService {
    private final IRoleService roleService;
    private final IUserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;

    @Override
    public JwtResponse login(LoginRequest loginRequest) throws CustomException {
        Authentication authentication;
        try {
            authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(
                            loginRequest.getPhoneNumber(),loginRequest.getPassword()
                    ));
        }catch (AuthenticationException e) {
            throw new BadCredentialsException("Wrong phone number or password");
        }
        UserDetailCustom userDetailCustom = (UserDetailCustom) authentication.getPrincipal();
        String token = jwtProvider.generateToken(userDetailCustom);
        return JwtResponse.builder()
                .token(token)
                .username(userDetailCustom.getUsername())
                .fullName(userDetailCustom.getFullName())
                .email(userDetailCustom.getEmail())
                .phone(userDetailCustom.getPhone())
                .status(userDetailCustom.getStatus())
                .createdAt(userDetailCustom.getCreatedAt())
                .updatedAt(userDetailCustom.getUpdatedAt())
                .isDeleted(userDetailCustom.getIsDeleted())
                .point(userDetailCustom.getPoint())
                .avatar(userDetailCustom.getAvatar())
                .roles(userDetailCustom.getAuthorities()
                        .stream().map(GrantedAuthority::getAuthority).collect(Collectors.toSet()))
                .build();
    }

    @Override
    public User register(RegisterRequest registerRequest){
        Set<Role> roles = new HashSet<>();
        if (registerRequest.getRoles() == null || registerRequest.getRoles().isEmpty()) {
            roles.add(roleService.findByRoleName(RoleName.ROLE_USER));
        } else {
            registerRequest.getRoles().forEach(role -> {
                switch (role) {
                    case "ADMIN":
                            roles.add(roleService.findByRoleName(RoleName.ROLE_ADMIN));
                        break;
                    case "USER":
                            roles.add(roleService.findByRoleName(RoleName.ROLE_USER));
                        break;
                    case "MANAGER" :
                            roles.add(roleService.findByRoleName(RoleName.ROLE_MANAGER));
                        break;
                    default:
                        throw new RuntimeException("role not found");
                }
            });
        }
        User user = User.builder()
                .username(registerRequest.getUsername())
                .phone(registerRequest.getPhoneNumber())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .roles(roles)
                .status(true)
                .createdAt(new Date())
                .isDeleted(false)
                .build();
        return userRepository.save(user);
    }
}

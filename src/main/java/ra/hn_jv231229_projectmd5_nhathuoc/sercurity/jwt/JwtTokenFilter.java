package ra.hn_jv231229_projectmd5_nhathuoc.sercurity.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import ra.hn_jv231229_projectmd5_nhathuoc.sercurity.principal.UserDetailCustom;
import ra.hn_jv231229_projectmd5_nhathuoc.sercurity.principal.UserDetailCustomService;

import java.io.IOException;

@Component
@Slf4j
public class JwtTokenFilter extends OncePerRequestFilter {
    @Autowired
    private UserDetailCustomService userDetailCustomService;
    @Autowired
    private JwtProvider jwtProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try{
            String token = getTokenFromRequest(request);
            if (token != null && jwtProvider.validateToken(token)) {
                String username = jwtProvider.getUsernameFromToken(token);
                UserDetailCustom userDetailCustom = (UserDetailCustom) userDetailCustomService.loadUserByUsername(username);
                Authentication authentication = new UsernamePasswordAuthenticationToken(userDetailCustom, null, userDetailCustom.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }catch(Exception ex){
            log.error("Exception {}",ex.getMessage());
        }
        filterChain.doFilter(request, response);
    }

    private String getTokenFromRequest(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            return header.substring(7);
        }
        return null;
    }
}

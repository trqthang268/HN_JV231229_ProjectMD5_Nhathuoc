package ra.hn_jv231229_projectmd5_nhathuoc.sercurity.jwt;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ra.hn_jv231229_projectmd5_nhathuoc.sercurity.principal.UserDetailCustom;

import java.util.Date;

@Component
@Slf4j
public class JwtProvider {
    @Value("${jwt_secret}")
    private String SECRET_KEY;
    @Value("${jwt_expiration}")
    private int EXPIRATION;

    public String generateToken(UserDetailCustom userDetail) {
        return Jwts.builder()
                .setSubject(userDetail.getPhone())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime()+EXPIRATION))
                .signWith(SignatureAlgorithm.HS384,SECRET_KEY)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return true;
        }catch (UnsupportedJwtException ex){
            log.error("Invalid JWT token");
        }catch (SignatureException ex){
            log.error("Signature exception {}", ex.getMessage());
        }catch (MalformedJwtException ex){
            log.error("Malformed URL exception {}", ex.getMessage());
        }catch (ExpiredJwtException ex){
            log.error("Expired JWT token");
        }catch (IllegalArgumentException ex){
            log.error("JWT claims string is empty");
        }
        return false;
    }

    public String getUsernameFromToken(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody().getSubject();
    }
}

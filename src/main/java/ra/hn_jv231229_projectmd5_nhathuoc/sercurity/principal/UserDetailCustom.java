package ra.hn_jv231229_projectmd5_nhathuoc.sercurity.principal;

import jakarta.persistence.Column;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserDetailCustom implements UserDetails {
    private Long userId;
    private String avatar;
    private Date createdAt;
    private String email;
    private String fullName;
    private Boolean isDeleted;
    private String password;
    private String phone;
    private Double point;
    private Boolean status;
    private Date updatedAt;
    private String username;
    private Collection<? extends GrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }
}

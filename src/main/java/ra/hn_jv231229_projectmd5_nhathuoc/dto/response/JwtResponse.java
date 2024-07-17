package ra.hn_jv231229_projectmd5_nhathuoc.dto.response;

import lombok.*;

import java.util.Date;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class JwtResponse {
    private String token;
    private final String type = "Bearer";
    private String avatar;
    private Date createdAt;
    private String email;
    private String fullName;
    private Boolean isDeleted;
    private String phone;
    private Double point;
    private Boolean status;
    private Date updatedAt;
    private String username;
    private Set<String> roles;
}

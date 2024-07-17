package ra.hn_jv231229_projectmd5_nhathuoc.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class LoginRequest {
    @NotBlank(message = "Không được để trống số điện thoại")
    private String phoneNumber;
    @NotBlank(message = "Mật khẩu không được để trống")
    private String password;
}

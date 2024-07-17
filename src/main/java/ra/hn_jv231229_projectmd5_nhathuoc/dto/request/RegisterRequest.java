package ra.hn_jv231229_projectmd5_nhathuoc.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class RegisterRequest {
    @NotBlank(message = "Không được để trống tên")
    private String fullName;
    @NotBlank(message = "Không được để trống Số điện thoại")
//    @PhoneExist
    private String phoneNumber;
    @NotBlank(message = "Không được để trống mật khẩu")
    private String password;
    @NotBlank(message = "Không được để trống xác nhận mật khẩu")
    private String confirmPassword;


}

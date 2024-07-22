package ra.hn_jv231229_projectmd5_nhathuoc.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class RegisterRequest {
    @NotBlank(message = "Không được để trống tên")
    private String username;
    @NotBlank(message = "Không được để trống Số điện thoại")
//    @PhoneExist
    @Pattern(regexp = "^([+]84|0)[35789][0-9]{8,9}$" ,message = "Invalid phone format!")
    private String phoneNumber;
    @NotBlank(message = "Không được để trống mật khẩu")
    private String password;
    @NotBlank(message = "Không được để trống xác nhận mật khẩu")
    private String confirmPassword;

    private Set<String> roles ;

}

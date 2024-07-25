package ra.hn_jv231229_projectmd5_nhathuoc.dto.request;


import lombok.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class BannerRequest {
    private String bannerName;
    private String description;
    private MultipartFile image;
    private Boolean status;
}

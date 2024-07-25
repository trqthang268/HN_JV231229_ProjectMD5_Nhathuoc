package ra.hn_jv231229_projectmd5_nhathuoc.dto.request;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductUpdateRequest {
    private Date updatedAt = new Date();
    private String productName;
    private MultipartFile image;
    private String description;
    private Boolean status;

    private Long brandId;
    private Long categoryId;
}

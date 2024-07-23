package ra.hn_jv231229_projectmd5_nhathuoc.dto.request;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class BrandRequest {
    private String brandName;
    private Date createAt;
    private String description;
    private String image;
    private Boolean status;
}

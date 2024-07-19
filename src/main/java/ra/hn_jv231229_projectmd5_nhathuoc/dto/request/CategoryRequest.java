package ra.hn_jv231229_projectmd5_nhathuoc.dto.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CategoryRequest {
    @NotBlank(message = "Category name must not be blanked")
    private String categoryName;
    private Date createdAt;
    private String description;
    private String image;
    private Boolean status = true;
}

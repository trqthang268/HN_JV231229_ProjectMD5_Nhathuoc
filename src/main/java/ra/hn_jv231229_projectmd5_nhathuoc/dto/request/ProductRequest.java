package ra.hn_jv231229_projectmd5_nhathuoc.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;
import ra.hn_jv231229_projectmd5_nhathuoc.validation.annotation.ProductNameExist;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductRequest {
    private Date createdAt;
    private Date updatedAt;
    @ProductNameExist
    @NotBlank(message = "Không được để trống tên sản phẩm")
    private String productName;
    private MultipartFile image;
    private String description;
    private String sku = UUID.randomUUID().toString();
    private Boolean status;

    private Long brandId;
    private Long categoryId;

    private MultipartFile productDetailImage;
    private String productDetailName;
    private Boolean productDetailStatus;
    private Integer productDetailStock;
    private Double productDetailUnitPrice;

    private String unitName;
    private Double unitPrice;
    private Boolean unitStatus;

    private List<MultipartFile> imageSrc;
}

package ra.hn_jv231229_projectmd5_nhathuoc.dto.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ProductDetailRequest {
    private String image;
    private String productDetailName;
    private Boolean status;
    private Integer stock;
    private Double unitPrice;
    private UnitRequest unit;
    private Long productId;
}

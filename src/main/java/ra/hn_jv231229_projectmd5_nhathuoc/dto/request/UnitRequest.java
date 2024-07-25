package ra.hn_jv231229_projectmd5_nhathuoc.dto.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UnitRequest {
    private String unitName;
    private Double unitPrice;
    private Boolean status;
}

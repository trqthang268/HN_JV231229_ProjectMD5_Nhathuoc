package ra.hn_jv231229_projectmd5_nhathuoc.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class OrderDetailId implements Serializable {
    @Column(name = "orders_id")
    private Long ordersId;

    @Column(name = "product_detail_id")
    private Long productDetailId;

}

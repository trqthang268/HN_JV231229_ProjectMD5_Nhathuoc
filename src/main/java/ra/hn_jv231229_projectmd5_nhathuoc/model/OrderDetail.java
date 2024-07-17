package ra.hn_jv231229_projectmd5_nhathuoc.model;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "order_detail")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class OrderDetail {
    @EmbeddedId
    private OrderDetailId id;

    @ManyToOne
    @MapsId("ordersId")
    private Orders orders;

    @ManyToOne
    @MapsId("productDetailId")
    private ProductDetail productDetail;

    @Column(name = "order_quantity")
    private Integer orderQuantity;

    @Column(name = "order_detail_name")
    private String orderDetailName;

    @Column(name = "unit_price")
    private Double unitPrice;
}




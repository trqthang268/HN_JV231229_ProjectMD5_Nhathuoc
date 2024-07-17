package ra.hn_jv231229_projectmd5_nhathuoc.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "shopping_cart")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_quantity")
    private Integer orderQuantity;

    @ManyToOne
    @JoinColumn(name = "product_detail_id")
    private ProductDetail productDetail;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}

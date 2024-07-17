package ra.hn_jv231229_projectmd5_nhathuoc.model;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "product_detail")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ProductDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String image;

    @Column(name = "product_detail_name")
    private String productDetailName;

    @Column(name = "status")
    private Boolean status;

    private Integer stock;

    @Column(name = "unit_price")
    private Double unitPrice;

    @ManyToOne
    @JoinColumn(name = "unit_id")
    private Unit unit;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

}


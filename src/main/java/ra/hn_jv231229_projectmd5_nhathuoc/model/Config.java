package ra.hn_jv231229_projectmd5_nhathuoc.model;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "config")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Config {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "config_name")
    private String configName;

    @Column(name = "status")
    private Boolean status;

    @ManyToOne
    @JoinColumn(name = "product_detail_id")
    private ProductDetail productDetail;
}

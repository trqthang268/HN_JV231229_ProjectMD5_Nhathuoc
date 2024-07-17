package ra.hn_jv231229_projectmd5_nhathuoc.model;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "unit")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Unit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "unit_name")
    private String unitName;

    @Column(name = "unit_price")
    private Double unitPrice;

    @Column(name = "status")
    private Boolean status;
}


package ra.hn_jv231229_projectmd5_nhathuoc.model;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "review")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String comments;

    @Column(name = "created_at")
    private Date createdAt;

    private Integer rating;

    @Column(name = "status")
    private Boolean status;

    @ManyToOne
    @JoinColumn(name = "product_detail_id")
    private ProductDetail productDetail;
}

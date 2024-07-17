package ra.hn_jv231229_projectmd5_nhathuoc.model;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "image")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String src;

    @ManyToOne
    @JoinColumn(name = "product")
    private Product product;
}

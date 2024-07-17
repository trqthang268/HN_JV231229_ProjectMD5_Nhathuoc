package ra.hn_jv231229_projectmd5_nhathuoc.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "banner")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Banner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "banner_name")
    private String bannerName;

    @Column(name = "created_at")
    private Date createdAt;

    private String description;
    private String image;

    @Column(name = "status")
    private Boolean status;
}


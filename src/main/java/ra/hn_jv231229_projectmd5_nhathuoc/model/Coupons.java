package ra.hn_jv231229_projectmd5_nhathuoc.model;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "coupons")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Coupons {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;
    private String discount;

    @Column(name = "end_date")
    private Date endDate;

    private Integer quantity;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "status")
    private Boolean status;

    private String title;
}


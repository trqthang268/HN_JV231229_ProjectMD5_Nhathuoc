package ra.hn_jv231229_projectmd5_nhathuoc.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "address")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String district;
    private String phone;

    @Column(name = "priority")
    private Boolean priority;

    private String province;

    @Column(name = "receive_name")
    private String receiveName;

    @Column(name = "street_address")
    private String streetAddress;

    private String ward;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}


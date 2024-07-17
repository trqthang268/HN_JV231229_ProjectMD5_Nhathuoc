package ra.hn_jv231229_projectmd5_nhathuoc.model;

import jakarta.persistence.*;
import lombok.*;
import ra.hn_jv231229_projectmd5_nhathuoc.constants.OrderStatus;

import java.util.Date;

@Entity
@Table(name = "orders")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created_at")
    private Date createdAt;

    private String district;
    private String note;
    private String phone;
    private String province;

    @Column(name = "receive_at")
    private Date receiveAt;

    @Column(name = "receive_name")
    private String receiveName;

    @Column(name = "serial_number")
    private String serialNumber;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Column(name = "street_address")
    private String streetAddress;

    @Column(name = "total_discounted_price")
    private Double totalDiscountedPrice;

    @Column(name = "total_price")
    private Double totalPrice;

    @Column(name = "total_price_after_coupons")
    private Double totalPriceAfterCoupons;

    private String ward;

    @ManyToOne
    @JoinColumn(name = "coupons_id")
    private Coupons coupons;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}


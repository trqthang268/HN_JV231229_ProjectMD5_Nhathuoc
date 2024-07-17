package ra.hn_jv231229_projectmd5_nhathuoc.model;

import jakarta.persistence.*;
import lombok.*;
import ra.hn_jv231229_projectmd5_nhathuoc.constants.RoleName;

@Entity
@Table(name = "role")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "role_name")
    @Enumerated(EnumType.STRING)
    private RoleName roleName;
}


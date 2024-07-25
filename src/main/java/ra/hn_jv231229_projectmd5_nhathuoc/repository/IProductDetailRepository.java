package ra.hn_jv231229_projectmd5_nhathuoc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ra.hn_jv231229_projectmd5_nhathuoc.model.ProductDetail;

import java.util.Optional;

public interface IProductDetailRepository extends JpaRepository<ProductDetail, Long> {
    Optional<ProductDetail> findProductDetailByProductId(Long productId);
}

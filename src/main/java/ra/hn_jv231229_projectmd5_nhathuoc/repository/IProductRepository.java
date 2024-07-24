package ra.hn_jv231229_projectmd5_nhathuoc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ra.hn_jv231229_projectmd5_nhathuoc.model.Product;

import java.util.Optional;

@Repository
public interface IProductRepository extends JpaRepository<Product, Long>, PagingAndSortingRepository<Product, Long> {
    Optional<Product> findProductByProductName(String productName);
}

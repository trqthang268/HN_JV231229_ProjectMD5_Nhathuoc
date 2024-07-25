package ra.hn_jv231229_projectmd5_nhathuoc.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ra.hn_jv231229_projectmd5_nhathuoc.model.Product;

import java.util.Optional;

@Repository
public interface IProductRepository extends JpaRepository<Product, Long>, PagingAndSortingRepository<Product, Long> {
    Optional<Product> findProductByProductName(String productName);

    @Query("SELECT p FROM Product p WHERE p.productName LIKE %:productName% OR p.description LIKE %:productName%")
    Page<Product> findAllByProductName(String productName, Pageable pageable);

    Page<Product> findProductsByCategoryId(Pageable pageable,Long categoryId);
}

package ra.hn_jv231229_projectmd5_nhathuoc.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ra.hn_jv231229_projectmd5_nhathuoc.dto.request.ProductRequest;
import ra.hn_jv231229_projectmd5_nhathuoc.dto.request.ProductUpdateRequest;
import ra.hn_jv231229_projectmd5_nhathuoc.model.Product;

public interface IProductService {
    Page<Product> findAllProduct(Pageable pageable);
    Page<Product> findAllByProductName(Pageable pageable, String productName);
    Page<Product> findProductsByCategoryId(Pageable pageable, Long categoryId);
    Product createProduct(ProductRequest productRequest);
    Product updateProduct(Long productId, ProductUpdateRequest productRequest);
    Product changeStatusProduct(Long productId);
}

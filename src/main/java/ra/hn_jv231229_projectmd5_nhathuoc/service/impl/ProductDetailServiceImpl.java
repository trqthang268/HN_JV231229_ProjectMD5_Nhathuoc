package ra.hn_jv231229_projectmd5_nhathuoc.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ra.hn_jv231229_projectmd5_nhathuoc.model.ProductDetail;
import ra.hn_jv231229_projectmd5_nhathuoc.repository.IProductDetailRepository;
import ra.hn_jv231229_projectmd5_nhathuoc.service.IProductDetailService;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ProductDetailServiceImpl implements IProductDetailService {
    private final IProductDetailRepository productDetailRepository;
    @Override
    public ProductDetail findProductDetailByProductId(Long productId) {
        return productDetailRepository.findProductDetailByProductId(productId)
                .orElseThrow(()->new NoSuchElementException("Không tìm thấy chi tiết sản phẩm"));
    }

    @Override
    public ProductDetail updateStock(Long productDetailId, Integer newStock) {
        ProductDetail productDetail = productDetailRepository.findById(productDetailId)
                .orElseThrow(()->new NoSuchElementException("Chi tiết sản phẩm không tồn tại"));
        productDetail.setStock(newStock);
        return null;
    }
}

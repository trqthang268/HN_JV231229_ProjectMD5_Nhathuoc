package ra.hn_jv231229_projectmd5_nhathuoc.service;

import ra.hn_jv231229_projectmd5_nhathuoc.model.ProductDetail;

public interface IProductDetailService {
    ProductDetail findProductDetailByProductId(Long productId);
    ProductDetail updateStock(Long productDetailId, Integer stock);

}

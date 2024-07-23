package ra.hn_jv231229_projectmd5_nhathuoc.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ra.hn_jv231229_projectmd5_nhathuoc.dto.request.ProductRequest;
import ra.hn_jv231229_projectmd5_nhathuoc.model.*;
import ra.hn_jv231229_projectmd5_nhathuoc.repository.*;
import ra.hn_jv231229_projectmd5_nhathuoc.service.IProductService;
import ra.hn_jv231229_projectmd5_nhathuoc.service.UploadService;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements IProductService {
    private final IProductRepository productRepository;
    private final IBrandRepository brandRepository;
    private final ICategoryRepository categoryRepository;
    private final IImageRepository imageRepository;
    private final IProductDetailRepository productDetailRepository;
    private final IUnitRepository unitRepository;
    private final UploadService uploadService;

    @Override
    public Page<Product> findAllProduct(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public Product createProduct(ProductRequest productRequest) {
        //chuyển đổi DTO sang product
        Product product = new Product();
        product.setCreatedAt(productRequest.getCreatedAt());
        product.setUpdatedAt(productRequest.getUpdatedAt());
        product.setProductName(productRequest.getProductName());
        product.setImage(uploadService.uploadFileToServer(productRequest.getImage()));
        product.setDescription(productRequest.getDescription());
        product.setSku(productRequest.getSku());
        product.setStatus(productRequest.getStatus());

        // thêm hoặc update Brand
        Brand brand = brandRepository.findById(productRequest.getBrandId())
                .orElseThrow(() -> new NoSuchElementException("Brand Not Found"));
        product.setBrand(brand);

        //thêm hoặc update category
        Category category = categoryRepository.findById(productRequest.getCategoryId())
                .orElseThrow(() -> new NoSuchElementException("Category Not Found"));
        product.setCategory(category);

        //thêm sản phẩm
        product = productRepository.save(product);

        //tạo và lưu product detail
        ProductDetail productDetail = new ProductDetail();
        productDetail.setImage(uploadService.uploadFileToServer(productRequest.getProductDetailImage()));
        productDetail.setProductDetailName(productRequest.getProductDetailName());
        productDetail.setStatus(productRequest.getProductDetailStatus());
        productDetail.setStock(productRequest.getProductDetailStock());
        productDetail.setUnitPrice(productRequest.getProductDetailUnitPrice());

        // tạo hoặc tìm unit
        Unit unit = new Unit();
        unit.setUnitName(productRequest.getUnitName());
        unit.setUnitPrice(productRequest.getUnitPrice());
        unit.setStatus(productRequest.getUnitStatus());
        unit = unitRepository.save(unit);
        productDetail.setUnit(unit);

        productDetail.setProduct(product);
        productDetailRepository.save(productDetail);

        // Create and save images
        for (MultipartFile imageSrc : productRequest.getImageSrc()) {
            Image image = new Image();
            image.setSrc(uploadService.uploadFileToServer(imageSrc));
            image.setProduct(product);
            imageRepository.save(image);
        }

        return product;
    }
}
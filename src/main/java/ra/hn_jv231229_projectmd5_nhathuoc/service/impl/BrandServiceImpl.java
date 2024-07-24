package ra.hn_jv231229_projectmd5_nhathuoc.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ra.hn_jv231229_projectmd5_nhathuoc.model.Brand;
import ra.hn_jv231229_projectmd5_nhathuoc.repository.IBrandRepository;
import ra.hn_jv231229_projectmd5_nhathuoc.service.IBrandService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements IBrandService {
    private final IBrandRepository brandRepository;
    @Override
    public List<Brand> getAllBrands() {
        return brandRepository.findAll();
    }
}

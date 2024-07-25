package ra.hn_jv231229_projectmd5_nhathuoc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.hn_jv231229_projectmd5_nhathuoc.dto.request.BannerRequest;
import ra.hn_jv231229_projectmd5_nhathuoc.model.Banner;
import ra.hn_jv231229_projectmd5_nhathuoc.repository.IBannerRepository;
import ra.hn_jv231229_projectmd5_nhathuoc.service.IBannerService;
import ra.hn_jv231229_projectmd5_nhathuoc.service.UploadService;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BannerServiceImpl implements IBannerService {
    @Autowired
    IBannerRepository bannerRepository;
    @Autowired
    UploadService uploadService;
    @Override
    public Banner addBanner(BannerRequest bannerRequest) {
        Banner banner = Banner.builder().bannerName(bannerRequest.getBannerName())
                .image(uploadService.uploadFileToServer(bannerRequest.getImage()))
                .createdAt(new Date())
                .description(bannerRequest.getDescription())
                .status(true)
                .build();
        return bannerRepository.save(banner);
    }

    @Override
    public Banner updateBanner(Integer id) {
        Banner banner =  bannerRepository.findById(id).orElse(null);
        banner.setStatus(banner.getStatus());
        return bannerRepository.save(banner);
    }

    @Override
    public List<Banner> getBanners() {
        return bannerRepository.findAll();
    }

    }

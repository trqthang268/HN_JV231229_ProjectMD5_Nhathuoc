package ra.hn_jv231229_projectmd5_nhathuoc.service;

import ra.hn_jv231229_projectmd5_nhathuoc.dto.request.BannerRequest;
import ra.hn_jv231229_projectmd5_nhathuoc.model.Banner;

import java.util.List;

public interface IBannerService {
    Banner addBanner(BannerRequest bannerRequest);
    Banner updateBanner(Integer id);
    List<Banner> getBanners();
}

package ra.hn_jv231229_projectmd5_nhathuoc.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import ra.hn_jv231229_projectmd5_nhathuoc.model.User;
import ra.hn_jv231229_projectmd5_nhathuoc.model.WishList;

public interface IWishListRepository extends JpaRepository<WishList, Integer> , PagingAndSortingRepository<WishList,Integer> {
}

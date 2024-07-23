package ra.hn_jv231229_projectmd5_nhathuoc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ra.hn_jv231229_projectmd5_nhathuoc.model.Category;

import java.util.List;

@Repository
public interface ICategoryRepository extends JpaRepository<Category, Long>, PagingAndSortingRepository<Category, Long> {
    boolean existsByCategoryName(String categoryName);
}

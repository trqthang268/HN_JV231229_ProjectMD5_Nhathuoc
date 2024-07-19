package ra.hn_jv231229_projectmd5_nhathuoc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ra.hn_jv231229_projectmd5_nhathuoc.model.Category;

import java.util.List;

@Repository
public interface ICategoryRepository extends JpaRepository<Category, Long> {

}

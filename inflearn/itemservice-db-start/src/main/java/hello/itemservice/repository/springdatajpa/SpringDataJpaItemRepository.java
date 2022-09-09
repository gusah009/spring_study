package hello.itemservice.repository.springdatajpa;

import hello.itemservice.domain.Item;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface SpringDataJpaItemRepository extends JpaRepository<Item, Long> {

  List<Item> findByItemNameLike(String itemName);

  List<Item> findByPriceLessThanEqual(Integer price);

  // 쿼리 메서드
  List<Item> findByItemNameLikeAndPriceLessThanEqual(String itemName, Integer price);

  // 쿼리 직접 실행
  @Query("select i from Item i where i.itemName like :itemName and i.price <= :price")
  List<Item> findItems(@Param("itemName") String itemName, @Param("price") Integer price);
}

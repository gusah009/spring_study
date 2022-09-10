package hello.itemservice.repository.v2;

import static hello.itemservice.domain.QItem.item;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import hello.itemservice.domain.Item;
import hello.itemservice.repository.ItemSearchCond;
import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

@Repository
public class ItemQueryRepositoryV2 {

  private final JPAQueryFactory query;

  public ItemQueryRepositoryV2(EntityManager em) {
    this.query = new JPAQueryFactory(em);
  }

  public List<Item> findAll(ItemSearchCond cond) {
    String itemName = cond.getItemName();
    Integer maxPrice = cond.getMaxPrice();

    List<Item> result = query
        .select(item)
        .from(item)
        .where(likeItemName(itemName), maxPrice(maxPrice))
        .fetch();
    return result;
  }

  private BooleanExpression likeItemName(String itemName) {
    if (StringUtils.hasText(itemName)) {
      return item.itemName.like("%" + itemName + "%");
    }
    return null;
  }

  private BooleanExpression maxPrice(Integer maxPrice) {
    if (maxPrice != null) {
      return item.price.loe(maxPrice);
    }
    return null;
  }
}

package jpashop.jpabook.repository;

import java.util.List;
import javax.persistence.EntityManager;
import jpashop.jpabook.domain.Order;
import jpashop.jpabook.domain.OrderSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepository {

  private final EntityManager em;

  public void save(Order order) {
    if (order.getId() == null) {
      em.persist(order);
    } else {
      em.merge(order);
    }
  }

  public Order findOne(Long id) {
    return em.find(Order.class, id);
  }

  public List<Order> findAll() {
    return em.createQuery("select o from Order o", Order.class)
        .getResultList();
  }

//  public List<Order> findAll(OrderSearch orderSearch) {  }
}

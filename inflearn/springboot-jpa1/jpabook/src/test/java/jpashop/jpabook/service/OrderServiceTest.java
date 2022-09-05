package jpashop.jpabook.service;

import static jpashop.jpabook.domain.OrderStatus.CANCEL;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import javax.persistence.EntityManager;
import jpashop.jpabook.domain.Address;
import jpashop.jpabook.domain.Member;
import jpashop.jpabook.domain.Order;
import jpashop.jpabook.domain.OrderStatus;
import jpashop.jpabook.domain.item.Book;
import jpashop.jpabook.domain.item.Item;
import jpashop.jpabook.exception.NotEnoughStockException;
import jpashop.jpabook.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class OrderServiceTest {

  @Autowired
  EntityManager em;
  @Autowired
  OrderService orderService;
  @Autowired
  OrderRepository orderRepository;


  @Test
  public void 상품주문() {
    // given
    Member member = createMember();

    Item book = createBook("시골 JPA", 10000, 10);

    int orderCount = 2;

    // when
    Long orderId = orderService.order(member.getId(), book.getId(), orderCount);

    // then
    Order getOrder = orderRepository.findOne(orderId);

    assertThat(OrderStatus.ORDER).isEqualTo(getOrder.getStatus());
    assertThat(getOrder.getOrderItems().size()).isEqualTo(1);
    assertThat(getOrder.getTotalPrice()).isEqualTo(10000 * orderCount);
    assertThat(book.getStockQuantity()).isEqualTo(8);
  }

  private Item createBook(String bookName, int price, int stockQuantity) {
    Item book = new Book();
    book.setName(bookName);
    book.setPrice(price);
    book.setStockQuantity(stockQuantity);
    em.persist(book);
    return book;
  }

  private Member createMember() {
    Member member = new Member();
    member.setName("회원1");
    member.setAddress(new Address("서울", "강가", "123-123"));
    em.persist(member);
    return member;
  }

  @Test
  public void 주문취소() {
    // given
    Member member = createMember();
    Item book = createBook("시골 JPA", 10000, 10);

    int orderCount = 2;

    Long orderId = orderService.order(member.getId(), book.getId(), orderCount);

    // when
    orderService.cancelOrder(orderId);

    // then
    Order getOrder = orderRepository.findOne(orderId);

    assertThat(CANCEL).isEqualTo(getOrder.getStatus());
    assertThat(10).isEqualTo(book.getStockQuantity());
  }

  @Test
  public void 상품주문_재고수량초과() {
    // given
    Member member = createMember();
    Item book = createBook("시골 JPA", 10000, 10);

    int orderCount = 12;

    // when
    // then
    assertThatThrownBy(() -> orderService.order(member.getId(), book.getId(), orderCount))
        .isInstanceOf(NotEnoughStockException.class);
  }
}
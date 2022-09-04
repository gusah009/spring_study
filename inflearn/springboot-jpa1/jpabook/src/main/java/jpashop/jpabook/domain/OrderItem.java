package jpashop.jpabook.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import jpashop.jpabook.domain.item.Item;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class OrderItem {

  @Id
  @GeneratedValue
  @Column(name = "order_item_id")
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "item_id")
  private Item item;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "order_id")
  private Order order;

  private int orderPrice;
  private int count;

  //==비즈니스 로직==//

  /**
   * 주문 취소
   */
  public void cancel() {
    getItem().addStock(count);
  }

  //==조회 로직==//

  /**
   * 주문상품 전체 가격 조회
   */
  public int getTotalPrice() {
    return getOrderPrice() * getCount();
  }
}

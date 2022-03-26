package inflearn.yeonghan.basic2.order;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Order {

  private final Long memberId;
  private final String itemName;
  private final int itemPrice;
  private final int discountPrice;

  public int calculatePrice() {
    return itemPrice - discountPrice;
  }

  @Override
  public String toString() {
    return "Order{" +
        "memberId=" + memberId +
        ", itemName='" + itemName + '\'' +
        ", itemPrice=" + itemPrice +
        ", discountPrice=" + discountPrice +
        '}';
  }
}

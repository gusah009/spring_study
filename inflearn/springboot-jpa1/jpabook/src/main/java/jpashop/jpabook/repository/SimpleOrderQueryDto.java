package jpashop.jpabook.repository;

import java.time.LocalDateTime;
import jpashop.jpabook.domain.Address;
import jpashop.jpabook.domain.Order;
import jpashop.jpabook.domain.OrderStatus;
import lombok.Data;

@Data
public class SimpleOrderQueryDto {

  private Long orderId;
  private String name;
  private LocalDateTime orderDate;
  private OrderStatus orderStatus;
  private Address address;

  private SimpleOrderQueryDto() {
  }

  public SimpleOrderQueryDto(Long orderId, String name, LocalDateTime orderDate,
      OrderStatus orderStatus, Address address) {
    this.orderId = orderId;
    this.name = name;
    this.orderDate = orderDate;
    this.orderStatus = orderStatus;
    this.address = address;
  }

  public static SimpleOrderQueryDto toDto(Order order) {
    SimpleOrderQueryDto simpleOrderDto = new SimpleOrderQueryDto();
    simpleOrderDto.orderId = order.getId();
    simpleOrderDto.name = order.getMember().getName();
    simpleOrderDto.orderDate = order.getOrderDate();
    simpleOrderDto.orderStatus = order.getStatus();
    simpleOrderDto.address = order.getDelivery().getAddress();
    return simpleOrderDto;
  }
}
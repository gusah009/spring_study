package jpashop.jpabook.api;

import java.util.List;
import java.util.stream.Collectors;
import jpashop.jpabook.domain.Order;
import jpashop.jpabook.domain.OrderSearch;
import jpashop.jpabook.repository.OrderRepository;
import jpashop.jpabook.repository.SimpleOrderQueryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @formatter:off
 * Order
 * Order -> Member N:1 연관관계
 * Order -> Delivery 1:1 연관관계
 * @formatter:on
 */
@RestController
@RequiredArgsConstructor
public class OrderSimpleApiController {

  private final OrderRepository orderRepository;

  @GetMapping("/api/v1/simple-orders")
  public List<Order> ordersV1() {
    List<Order> all = orderRepository.findAllByCriteria(new OrderSearch());
    return all;
  }

  @GetMapping("/api/v2/simple-orders")
  public List<SimpleOrderQueryDto> ordersV2() {
    List<Order> orders = orderRepository.findAllByCriteria(new OrderSearch());
    return orders.stream()
        .map(SimpleOrderQueryDto::toDto)
        .collect(Collectors.toList());
  }

  @GetMapping("/api/v3/simple-orders")
  public List<SimpleOrderQueryDto> ordersV3() {
    List<Order> orders = orderRepository.findAllWithMemberDelivery();
    return orders.stream()
        .map(SimpleOrderQueryDto::toDto)
        .collect(Collectors.toList());
  }

  @GetMapping("/api/v4/simple-orders")
  public List<SimpleOrderQueryDto> ordersV4() {
    return orderRepository.findOrderDtos();
  }
}

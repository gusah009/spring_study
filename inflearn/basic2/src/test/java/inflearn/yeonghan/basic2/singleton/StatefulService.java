package inflearn.yeonghan.basic2.singleton;

import org.springframework.web.bind.annotation.GetMapping;

public class StatefulService {

//  private int price;

  public int order(String name, int price) {
    System.out.println("name = " + name + " price = " + price);
//    this.price = price;
    return price;
  }

//  public int getPrice() {
//    return price;
//  }
}

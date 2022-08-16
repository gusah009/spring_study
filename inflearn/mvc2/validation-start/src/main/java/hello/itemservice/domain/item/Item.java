package hello.itemservice.domain.item;

import lombok.Data;

@Data
//@ScriptAssert(lang = "javascript", script = "_this.price * _this.quantity >= 10000", message = "총합이 10000원 넘게 입력해주세요.")
public class Item {

  //  @NotNull(groups = UpdateCheck.class) //수정시에만 적용
  private Long id;

  //  @NotBlank(groups = {SaveCheck.class, UpdateCheck.class})
  private String itemName;

  //  @NotNull(groups = {SaveCheck.class, UpdateCheck.class})
//  @Range(min = 1000, max = 1000000, groups = {SaveCheck.class, UpdateCheck.class})
  private Integer price;

  //  @NotNull(groups = {SaveCheck.class, UpdateCheck.class})
//  @Max(value = 9999, groups = SaveCheck.class) //등록시에만 적용
  private Integer quantity;


  public Item() {
  }

  public Item(String itemName, Integer price, Integer quantity) {
    this.itemName = itemName;
    this.price = price;
    this.quantity = quantity;
  }
}

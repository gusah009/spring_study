package hello.itemservice.web.validation;

import hello.itemservice.domain.item.Item;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.thymeleaf.util.StringUtils;

public class ItemValidator implements Validator {

  @Override
  public boolean supports(Class<?> clazz) {
    return Item.class.isAssignableFrom(clazz);
    // item == clazz
    //item == subItem
  }

  @Override
  public void validate(Object target, Errors errors) {
    Item item = (Item) target;
    // 검증 로직
    if (StringUtils.isEmpty(item.getItemName())) {
      errors.rejectValue("itemName", "required");
    }
    if (item.getPrice() == null || item.getPrice() < 1000 || item.getPrice() > 1_000_000) {
      errors.rejectValue("price", "range", new Object[]{1000, 1_000_000}, null);
    }
    if (item.getQuantity() == null || item.getQuantity() > 9999) {
      errors.rejectValue("quantity", "max", new Object[]{9999}, null);
    }

    // 특정 필드가 아닌 복합 룰 검증
    if (item.getPrice() != null && item.getQuantity() != null) {
      int resultPrice = item.getPrice() * item.getQuantity();
      if (resultPrice < 10000) {
        errors.reject("totalPriceMin", new Object[]{10000, resultPrice}, null);
      }
    }
  }
}

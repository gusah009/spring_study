package yeonghan.simpleproject.web.basic;

import java.util.List;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import yeonghan.simpleproject.domain.item.Item;
import yeonghan.simpleproject.domain.item.ItemRepository;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor
public class BasicItemController {

  private final ItemRepository itemRepository;

  @GetMapping
  public String items(Model model) {
    List<Item> items = itemRepository.findAll();
    model.addAttribute("items", items);
    return "basic/items";
  }

  @GetMapping("/{itemId}")
  public String item(@PathVariable long itemId, Model model) {
    Item findItem = itemRepository.findById(itemId);
    model.addAttribute("item", findItem);
    return "basic/item";
  }

  @GetMapping("/add")
  public String addForm() {
    return "basic/addForm";
  }

//  @PostMapping("/add")
//  public String addItemV1(
//      @RequestParam String itemName,
//      @RequestParam Integer price,
//      @RequestParam Integer quantity,
//      Model model) {
//    Item item = new Item(itemName, price, quantity);
//
//    itemRepository.sa ve(item);
//
//    model.addAttribute("item", item);
//    return "basic/item";
//  }

//  @PostMapping("/add")
//  public String addItemV2(@ModelAttribute("item") Item item) {
//    itemRepository.save(item);
////    model.addAttribute("item", item); // 자동 추가, 생략 가능
//    return "basic/item";
//  }

//  @PostMapping("/add")
//  public String addItemV3(@ModelAttribute Item reqItem) {
//    // 클래스명의 앞글자를 소문자로 바꾼 name을 Model에 key값으로 넣어줌
//    itemRepository.save(reqItem);
//    return "basic/item";
//  }

//  @PostMapping("/add")
//  public String addItemV4(Item reqItem) {
//    itemRepository.save(reqItem);
//    return "basic/item";
//  }

//  @PostMapping("/add")
//  public String addItemV5(Item reqItem) {
//    itemRepository.save(reqItem);
//    return "redirect:/basic/items/" + reqItem.getId(); // PRG (Post Redirect Get) 적용
//  }

  @PostMapping("/add")
  public String addItemV6(Item reqItem, RedirectAttributes redirectAttributes) {
    Item savedItem = itemRepository.save(reqItem);
    redirectAttributes.addAttribute("itemId", savedItem.getId());
    redirectAttributes.addAttribute("status", true);
    return "redirect:/basic/items/{itemId}";
  }

  @GetMapping("/{itemId}/edit")
  public String editForm(@PathVariable long itemId, Model model) {
    Item findItem = itemRepository.findById(itemId);
    model.addAttribute("item", findItem);
    return "basic/editForm";
  }

  @PostMapping("/{itemId}/edit")
  public String edit(@PathVariable long itemId, @ModelAttribute Item item) {
    itemRepository.update(itemId, item);
    return "redirect:/basic/items/{itemId}";
  }

  @PostConstruct
  public void init() {
    itemRepository.save(new Item("itemA", 10000, 10));
    itemRepository.save(new Item("itemB", 20000, 20));
  }

}

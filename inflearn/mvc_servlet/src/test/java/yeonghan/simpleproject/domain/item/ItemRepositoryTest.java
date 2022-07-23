package yeonghan.simpleproject.domain.item;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

class ItemRepositoryTest {

  ItemRepository itemRepository = new ItemRepository();

  @AfterEach
  void afterEach() {
    itemRepository.clearStore();
  }

  @Test
  void save() {
    // given
    Item item = new Item("itemA", 10000, 10);

    // when
    Item findItem = itemRepository.save(item);

    // then
    assertThat(findItem).isEqualTo(item);
  }

  @Test
  void findAll() {
    // given
    Item item1 = new Item("itemA", 10000, 10);
    Item item2 = new Item("itemB", 20000, 20);

    itemRepository.save(item1);
    itemRepository.save(item2);

    // when
    List<Item> result = itemRepository.findAll();
    // then
    assertThat(result.size()).isEqualTo(2);
    assertThat(result).contains(item1, item2);
  }

  @Test
  void updateItem() {
    // given
    Item item = new Item("itemA", 10000, 10);

    Item savedItem = itemRepository.save(item);
    Long itemId = savedItem.getId();

    // when
    Item updateParam = new Item("item2", 20000, 20);
    itemRepository.update(itemId, updateParam);

    // then
    Item findItem = itemRepository.findById(itemId);

    assertThat(findItem.getItemName()).isEqualTo(item.getItemName());
    assertThat(findItem.getPrice()).isEqualTo(item.getPrice());
    assertThat(findItem.getQuantity()).isEqualTo(item.getQuantity());
  }
}

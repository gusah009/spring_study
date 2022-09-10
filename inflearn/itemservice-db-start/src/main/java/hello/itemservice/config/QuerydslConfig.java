package hello.itemservice.config;


import hello.itemservice.repository.ItemRepository;
import hello.itemservice.repository.jpa.JpaItemRepository;
import hello.itemservice.repository.jpa.JpaItemRepositoryV3;
import hello.itemservice.service.ItemService;
import hello.itemservice.service.ItemServiceV1;
import javax.persistence.EntityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuerydslConfig {

  private final EntityManager em;

  public QuerydslConfig(EntityManager em) {
    this.em = em;
  }

  @Bean
  public ItemService itemService() {
    return new ItemServiceV1(itemRepository());
  }

  @Bean
  public ItemRepository itemRepository() {
    return new JpaItemRepositoryV3(em);
  }
}

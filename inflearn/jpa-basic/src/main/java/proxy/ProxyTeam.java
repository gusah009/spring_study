package proxy;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@ToString
public class ProxyTeam {

  @Id
  @GeneratedValue
  private Long id;
  @Setter
  private String name;
}

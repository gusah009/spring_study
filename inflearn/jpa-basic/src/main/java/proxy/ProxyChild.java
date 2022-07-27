package proxy;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ProxyChild {

  @Id
  @GeneratedValue
  private Long id;
  private String name;

  @ManyToOne
  @JoinColumn
  ProxyParent parent;
}

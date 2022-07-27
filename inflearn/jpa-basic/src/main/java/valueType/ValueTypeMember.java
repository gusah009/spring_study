package valueType;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ValueTypeMember {

  @Id
  @GeneratedValue
  private Long id;

  private String username;

  @Embedded
  private ValueTypePeriod period;

  @Embedded
  private ValueTypeAddress address;

  @ElementCollection
  @CollectionTable(name = "FAVORITE_FOOD",
      joinColumns = @JoinColumn(name = "VALUE_TYPE_MEMEBER_ID")
  )
  @Column(name = "FOOD_NAME")
  private Set<String> favoriteFood = new HashSet<>();

  @ElementCollection
  @CollectionTable(name = "ADDRESS",
      joinColumns = @JoinColumn(name = "VALUE_TYPE_MEMEBER_ID"))
  private List<ValueTypeAddress> addressHistory = new ArrayList<>();
}

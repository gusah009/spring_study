package jpql;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@ToString
@Getter
@Setter
public class MemberJPQL {

  @Id
  @GeneratedValue
  private Long id;
  private String username;
  private int age;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn
  private TeamJPQL team;

  public void changeTeam(TeamJPQL team) {
    this.team = team;
    team.getMemberList().add(this);
  }
}

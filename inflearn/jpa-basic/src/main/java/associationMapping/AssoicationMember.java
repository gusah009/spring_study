package associationMapping;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class AssoicationMember {

  @Id
  @GeneratedValue
  @Column(name = "MEMBER_ID")
  private Long id;

  private String username;

//  private Long teamId;

  @ManyToOne
  @JoinColumn(name = "TEAM_ID")
  private AssoicationTeam team;

  @OneToOne
  @JoinColumn(name = "LOCKER_ID")
  private AssocationLocker locker;
}


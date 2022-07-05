package entityMapping;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class PKMember {

  // @formatter:off
  /**
   * Id를 직접 할당할 거라면 @Id만 사용하면 됨.
   * 자동 생성하려면 @GeneratedValue 사용
   * AUTO: 데이터베이스 방언에 맞춰 아래 3가지 중 하나를 자동으로 선택 (잘 보고 선택해야함!)
   * IDENTITY: 데이터베이스에 Id 생성 전략을 위임.
   *  - MySql의 AUTO_INCREMENT 같은 전략
   * SEQUENCE: Oracle 같은 DB에서 많이 사용
   *  - SEQ_TABLE을 가지고 id를 가져옴. IDENTITY와 다르게 persist 쿼리를 보낼 필요가 없음.
   *  - 하지만 이렇게 되면 SEQ_TABLE에 쿼리 한 번, INSERT 쿼리 한 번, 두 번이나 왔다갔다 하게 된다.
   *  - 여기서 일어나는 성능 이슈를 방지하기 위해 allocationSize를 할당하여, allocationSize만큼 미리 id를 가져와서 사용한다.
   * TABLE: DB의 TABLE 전략을 흉내낸 것
   */
  // @formatter:on
  @Id
  @GeneratedValue
  private Long id;

  @Column(name = "name", nullable = false, unique = true)
  private String username;

  public PKMember() {
  }
}

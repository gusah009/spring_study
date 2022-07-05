package entityMapping;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
public class EntityMappingMember {

  @Id
  private Long id;

  @Column(name = "name")
  private String username; // 컬럼의 컬럼명을 명시하고 싶다면 @Column 어노테이션

  private Integer age; // 생각하는 대로 숫자 타입으로 만들어짐

  // 운영상황에선 반드시 STRING으로 해야한다. ORDINAL로 하면 순서로 들어가는데, 순서가 바뀌면 데이터가 다 엉키게 된다.
  @Enumerated(EnumType.STRING)
  private RoleType roleType; // DB엔 실제로 Enum 타입이 없지만 STRING으로 타입을 결정할 수 있음

  @Temporal(TemporalType.TIMESTAMP)
  private Date createdDate; // JAVA는 그냥 Date 하나로 막 쓰지만, DB들은 DATE, TIME, DATETIME을 구분해서 씁니다.

  @Temporal(TemporalType.TIMESTAMP)
  private Date lastModifiedDate; // LocalDate, LocalDateTime 에선 생략 가능하다.

  @Lob // 지정할 수 있는 속성이 없습니다. 문자열이면 자동으로 CLOB, 나머지면 BLOB으로 매핑됩니다.
  private String description; // VARCHAR를 넘어서는 큰 컨텐츠를 쓰고 싶다면 @Lob

  @Transient
  private int temp; // DB와 관련없는 필드

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  public RoleType getRoleType() {
    return roleType;
  }

  public void setRoleType(RoleType roleType) {
    this.roleType = roleType;
  }

  public Date getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }

  public Date getLastModifiedDate() {
    return lastModifiedDate;
  }

  public void setLastModifiedDate(Date lastModifiedDate) {
    this.lastModifiedDate = lastModifiedDate;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}

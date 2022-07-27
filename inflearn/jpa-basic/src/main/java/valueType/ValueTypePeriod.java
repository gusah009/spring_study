package valueType;

import java.time.LocalDateTime;
import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Embeddable
@Getter
// @Setter // 불변 객체로 만들기
public class ValueTypePeriod {

  private LocalDateTime startDate;
  private LocalDateTime endDate;
}

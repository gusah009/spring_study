package jpashop.jpabook;

import static org.assertj.core.api.Assertions.assertThat;

import javax.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class MemberRepositoryTest {

  @Autowired
  private MemberRepository memberRepository;
  @Autowired
  private EntityManager entityManager;

  @Test
  @Transactional
  void testMember() {
    // given
    Member member = new Member();
    member.setUsername("member1");

    // when
    Long savedId = memberRepository.save(member);
    entityManager.flush();
    entityManager.clear();
    Member findMember = memberRepository.find(savedId);

    // then
    assertThat(findMember.getId()).isEqualTo(member.getId());
    assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
  }

  @Test
  void find() {
  }
}
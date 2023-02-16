package study.querydsl.jpql;

import org.junit.jupiter.api.Test;
import study.querydsl.QuerydslBasicTest;
import study.querydsl.entity.Member;

import static org.assertj.core.api.Assertions.assertThat;

public class JPQLTest extends QuerydslBasicTest {

    @Test
    public void startJPQL() {
        //member1을 찾아라.
        String qlString =
                "select m from Member m " +
                        "where m.username = :username";
        Member findMember = em.createQuery(qlString, Member.class)
                .setParameter("username", "member1")
                .getSingleResult();
        assertThat(findMember.getUsername()).isEqualTo("member1");
    }
}

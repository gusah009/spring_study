package study.querydsl;

import com.querydsl.core.Tuple;
import org.junit.jupiter.api.Test;

import java.util.List;

import static study.querydsl.entity.QMember.member;

public class QuerydslAdvancedTest extends QuerydslBasicTest {

    @Test
    public void simpleProjection() {
        List<String> result = queryFactory.select(member.username)
                .from(member)
                .fetch();

        for (String s : result) {
            System.out.println("s = " + s);
        }
    }

    @Test
    public void tupleProjection() {
        List<Tuple> result = queryFactory.select(member.username, member.age)
                .from(member)
                .fetch();

        for (Tuple tuple : result) {
            System.out.println("username = " + tuple.get(member.username));
            System.out.println("age = " + tuple.get(member.age));
        }
    }
}

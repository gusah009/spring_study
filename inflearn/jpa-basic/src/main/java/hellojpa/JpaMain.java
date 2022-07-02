package hellojpa;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {

  public static void main(String[] args) {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

    EntityManager em = emf.createEntityManager();

    EntityTransaction tx = em.getTransaction();
    tx.begin();

    try {
      // INSERT
//      Member member = new Member();
//      member.setId(1L);
//      member.setName("HelloA");

//      Member member = new Member();
//      member.setId(2L);
//      member.setName("HelloB");

      // SELECT
//      Member findMember = em.find(Member.class, 1L);
//      System.out.println("findMembmer.getId() = " + findMember.getId());
//      System.out.println("findMembmer.getName() = " + findMember.getName());

      // UPDATE
//      Member findMember = em.find(Member.class, 1L);
//      findMember.setName("HelloJPA");

      // JPQL - SQL을 추상화한 객체 지향 쿼리 언어
      List<Member> result = em.createQuery("select m from Member as m", Member.class)
          .getResultList();

      for (Member member : result) {
        System.out.println("member.getName() = " + member.getName());
      }

      tx.commit();
    } catch (Exception e) {
      tx.rollback();
    } finally {
      em.close();
    }

    emf.close();
  }

}

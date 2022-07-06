package associationMapping;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Main {

  public static void main(String[] args) {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

    EntityManager em = emf.createEntityManager();

    EntityTransaction tx = em.getTransaction();
    tx.begin();

    try {

      // 저장
      AssoicationTeam team = new AssoicationTeam();
      team.setName("teamA");
      em.persist(team);

      AssoicationMember member = new AssoicationMember();
      member.setUsername("memberA");
      member.setTeam(team);
      em.persist(member);

      // 조회
      AssoicationMember findMember = em.find(AssoicationMember.class, member.getId());
      System.out.println(findMember.getTeam());

      tx.commit();
    } catch (Exception e) {
      tx.rollback();
    } finally {
      em.close();
    }

    emf.close();
  }
}

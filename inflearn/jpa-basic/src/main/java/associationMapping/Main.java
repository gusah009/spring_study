package associationMapping;

import java.util.List;
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

      AssocationLocker lockerA = new AssocationLocker();
      lockerA.setName("lockerA");
      em.persist(lockerA);

      AssoicationMember memberA = new AssoicationMember();
      memberA.setUsername("memberA");
      memberA.setTeam(team);
      memberA.setLocker(lockerA);
      em.persist(memberA);
      AssoicationMember memberB = new AssoicationMember();
      memberB.setUsername("memberB");
      memberB.setTeam(team);
      em.persist(memberB);

      em.flush();
      em.clear();

      // 조회
      AssoicationMember findMember = em.find(AssoicationMember.class, memberA.getId());
      System.out.println(findMember.getTeam().getName());

      List<AssoicationMember> members = findMember.getTeam().getMembers();
      for (AssoicationMember member : members) {
        System.out.println("teamA's member = " + member.getUsername());
      }

      AssocationLocker findLocker = em.find(AssocationLocker.class, lockerA.getId());
      System.out.println(findLocker.getName() + "'s owner: "
          + findLocker.getMember().getUsername());

      tx.commit();
    } catch (Exception e) {
      tx.rollback();
      e.printStackTrace();
    } finally {
      em.close();
    }

    emf.close();
  }
}

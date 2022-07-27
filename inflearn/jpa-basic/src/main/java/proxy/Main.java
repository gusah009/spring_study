package proxy;

import associationMapping.AssocationLocker;
import associationMapping.AssoicationMember;
import associationMapping.AssoicationTeam;
import java.awt.print.Pageable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import jpashop.domain.Member;
import org.hibernate.HibernateException;
import org.hibernate.LazyInitializationException;

public class Main {

  public static void main(String[] args) {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

    EntityManager em = emf.createEntityManager();

    EntityTransaction tx = em.getTransaction();
    tx.begin();

    try {
      // 한 번 영속성 컨텍스트에 올라간 객체는 굳이 프록시로 반환하지 않는다.
      getSameObjectInSameTransaction(em);
      // 한 번 영속성 컨텍스트에 올라간 객체는 프록시라도 같은 트랜잭션에서 맞춘다.
      getSameObjectInSameTransaction2(em);
      // 참조하고 있는 객체가 영속성 컨텍스트에서 내려가면 LazyInitializationException를 발생시킨다.
      refObjectOutOfTransactionError(em);
      // 지연로딩이라면 team을 프록시로 조회함
      getTeamProxyWhenFetchTypeLazy(em);
      // JPQL에서 N + 1 문제 (Team의 fetchType을 EAGER로 바꾸고 persistence.xml의 show-sql로 확인)
      eagerNplusOneProblem(em);

      // Cascade ALL로 설정하면 parent를 persist할 때 내부 요소도 연쇄적으로 persist 해줌을 의미함.
      cascadeAll(em);
      // 관리하는 객체를 Collection에서 지우면 고아 객체가 되어 삭제됨.
      orphanRemoval(em);

      tx.commit();
    } catch (Exception e) {
      tx.rollback();
      e.printStackTrace();
    } finally {
      em.close();
    }

    emf.close();
  }

  private static void orphanRemoval(EntityManager em) {
    ProxyChild child1 = new ProxyChild();
    ProxyChild child2 = new ProxyChild();
    ProxyParent parent = new ProxyParent();
    parent.addChild(child1);
    parent.addChild(child2);

    em.persist(parent);
    ProxyParent findParent = em.find(ProxyParent.class, parent.getId());
    findParent.getChildList().remove(0);
  }

  private static void cascadeAll(EntityManager em) {
    ProxyChild child1 = new ProxyChild();
    ProxyChild child2 = new ProxyChild();
    ProxyParent parent = new ProxyParent();
    parent.addChild(child1);
    parent.addChild(child2);

    em.persist(parent);
//      em.persist(child1);
//      em.persist(child2);
    em.flush();
    em.clear();
  }

  private static void eagerNplusOneProblem(EntityManager em) {
    List<ProxyMember> members = em.createQuery("select m from ProxyMember m",
        ProxyMember.class).getResultList();
  }

  private static void getTeamProxyWhenFetchTypeLazy(EntityManager em) {
    ProxyTeam team = getProxyTeam(em);
    ProxyMember member = getProxyMember(team, em);
    em.flush();
    em.clear();
    ProxyMember findMember = em.find(ProxyMember.class, member.getId());
    System.out.println("findMember.getTeam().getClass() = " + findMember.getTeam().getClass());
  }

  private static ProxyTeam getProxyTeam(EntityManager em) {
    ProxyTeam team = new ProxyTeam();
    team.setName("helloTeam");
    em.persist(team);
    return team;
  }

  private static ProxyMember getProxyMember(ProxyTeam team, EntityManager em) {
    ProxyMember member = new ProxyMember();
    member.setName("hello");
    member.setTeam(team);
    em.persist(member);
    return member;
  }

  private static void refObjectOutOfTransactionError(EntityManager em) {
    System.out.println("======= refObjectOutOfTransactionError START");
    ProxyMember newMember = getProxyMember(null, em);
    em.flush();
    em.clear();
    ProxyMember findMember = em.getReference(ProxyMember.class, newMember.getId());
    System.out.println("findMember.getClass() = " + findMember.getClass());
    em.detach(findMember);
    try {
      printMember(findMember);
      printMemberAndTeam(findMember);
    } catch (LazyInitializationException e) {
      System.out.println("error: " + e.getMessage());
    }
    System.out.println("======= refObjectOutOfTransactionError END");
    System.out.println();
  }

  private static void getSameObjectInSameTransaction(EntityManager em) {
    System.out.println("======= getSameObjectInSameTransaction START");
    ProxyTeam team = getProxyTeam(em);
    ProxyMember member2 = getProxyMember(team, em);
    em.flush();
    em.clear();

    ProxyMember findMember = em.find(ProxyMember.class, member2.getId());
    ProxyMember refMember = em.getReference(ProxyMember.class, member2.getId());
    System.out.println("findMember.getClass() = " + findMember.getClass());
    System.out.println("refMember.getClass() = " + refMember.getClass());
    System.out.println("(findMember == refMember) = " + (findMember == refMember));
    System.out.println("======= getSameObjectInSameTransaction END");
    System.out.println();
  }

  private static void getSameObjectInSameTransaction2(EntityManager em) {
    System.out.println("======= getSameObjectInSameTransaction2 START");
    ProxyTeam team = getProxyTeam(em);
    ProxyMember member3 = getProxyMember(team, em);
    em.flush();
    em.clear();

    ProxyMember refMember = em.getReference(ProxyMember.class, member3.getId());
    ProxyMember findMember = em.find(ProxyMember.class, member3.getId());
    System.out.println("refMember.getClass() = " + refMember.getClass());
    System.out.println("findMember.getClass() = " + findMember.getClass());
    System.out.println("(refMember == findMember) = " + (refMember == findMember));
    System.out.println("======= getSameObjectInSameTransaction2 END");
    System.out.println();
  }

  private static void printMemberAndTeam(ProxyMember member) {
    String findMemberName = member.getName();
    System.out.println("findMemberName = " + findMemberName);
    String findTeamName = member.getTeam().getName();
    System.out.println("findTeamName = " + findTeamName);
  }

  private static void printMember(ProxyMember member) {
    String findMemberName = member.getName();
    System.out.println("findMemberName = " + findMemberName);
  }
}

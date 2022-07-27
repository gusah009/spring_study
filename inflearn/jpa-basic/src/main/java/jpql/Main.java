package jpql;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class Main {

  public static void main(String[] args) {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
    EntityManager em = emf.createEntityManager();
    EntityTransaction tx = em.getTransaction();
    tx.begin();

    try {

      JPQL(em);
      Criteria(em);
      nativeSQL(em);

      JPQLBasic(em);
      binding(em);
      selectProjection(em);
      paging(em);
      join(em);
      conditionalExpression(em);

      fetchJoinBasic(em);
      bulk(em);
      tx.commit();
    } catch (
        Exception e) {
      tx.rollback();
      e.printStackTrace();
    } finally {
      em.close();
    }

    emf.close();
  }

  private static void bulk(EntityManager em) {
    int resultCount = em.createQuery("update MemberJPQL m set m.age = 20").executeUpdate();
    System.out.println("resultCount = " + resultCount);
  }

  private static void fetchJoinBasic(EntityManager em) {
    TeamJPQL teamA = createTeam("teamA", em);
    TeamJPQL teamB = createTeam("teamB", em);
    createMember("MO1", teamA, em);
    createMember("MO2", teamA, em);
    createMember("MO3", teamB, em);
    createMember("MO4", null, em);
    em.flush();
    em.clear();

//    String slowQuery = "select m From MemberJPQL m";
    String fastQuery = "select m From MemberJPQL m join fetch m.team";
    List<MemberJPQL> result = em.createQuery(fastQuery, MemberJPQL.class)
        .getResultList();
    for (MemberJPQL member : result) {
      if (member.getTeam() == null) {
        System.out.println(
            "member = " + member.getUsername() + ", null");
      } else {
        System.out.println(
            "member = " + member.getUsername() + ", " + member.getTeam().getName());
      }
    }
  }

  private static TeamJPQL createTeam(String teamName, EntityManager em) {
    TeamJPQL team = new TeamJPQL();
    team.setName(teamName);
    em.persist(team);
    return team;
  }

  private static void createMember(String userName, TeamJPQL team, EntityManager em) {
    MemberJPQL member4 = new MemberJPQL();
    member4.setUsername(userName);
    member4.setTeam(team);
    em.persist(member4);
  }

  private static void conditionalExpression(EntityManager em) {
    MemberJPQL member = new MemberJPQL();
    member.setAge(20);
    em.persist(member);

    em.flush();
    em.clear();

    List<String> coalesce = em.createQuery(
        "select coalesce(m.username,'이름 없는 회원') from MemberJPQL m",
        String.class).getResultList();
    System.out.println("coalesce = " + coalesce);

    member = em.find(MemberJPQL.class, member.getId());
    member.setUsername("관리자");
    em.persist(member);
    List<String> nullif = em.createQuery(
        "select nullif(m.username,'관리자') from MemberJPQL m",
        String.class).getResultList();
    System.out.println("nullif = " + nullif);
  }

  private static void join(EntityManager em) {
    TeamJPQL team = createTeam("team1", em);
    MemberJPQL member = new MemberJPQL();
    member.setAge(10);
    member.setUsername("MO");
    member.setTeam(team);
    em.persist(member);
    em.flush();
    em.clear();

    List<TeamJPQL> innerJoin = em.createQuery(
        "select m.team from MemberJPQL m inner join m.team t",
        TeamJPQL.class).getResultList();
    List<TeamJPQL> outerJoin = em.createQuery(
        "select m.team from MemberJPQL m left join m.team t",
        TeamJPQL.class).getResultList();
    List<TeamJPQL> thetaJoin = em.createQuery(
        "select m.team from MemberJPQL m, TeamJPQL t where m.username = t.name",
        TeamJPQL.class).getResultList();

    List<TeamJPQL> onInnerJoin = em.createQuery(
        "select m.team from MemberJPQL m inner join m.team t on t.name = 'team1'",
        TeamJPQL.class).getResultList();
    List<TeamJPQL> onOuterJoin = em.createQuery(
        "select m.team from MemberJPQL m left join TeamJPQL t on m.username = t.name",
        TeamJPQL.class).getResultList();
  }

  private static void paging(EntityManager em) {
    for (int i = 0; i < 100; i++) {
      MemberJPQL member = new MemberJPQL();
      member.setAge(i);
      member.setUsername("MO" + i);
      em.persist(member);
    }

    em.flush();
    em.clear();

    List<MemberJPQL> resultList = em.createQuery("select m from MemberJPQL m order by m.age desc",
            MemberJPQL.class)
        .setFirstResult(0)
        .setMaxResults(10)
        .getResultList();
    for (MemberJPQL result : resultList) {
      System.out.println(result);
    }
  }

  private static void selectProjection(EntityManager em) {
    TeamJPQL team = createTeam("team1", em);
    MemberJPQL member = new MemberJPQL();
    member.setAge(10);
    member.setUsername("MO");
    member.setTeam(team);
    em.persist(member);
    em.flush();
    em.clear();

    List<TeamJPQL> findMemberList = em.createQuery("select m.team from MemberJPQL m",
        TeamJPQL.class).getResultList();
    List<TeamJPQL> joinQueryExplicitly = em.createQuery("select t from MemberJPQL m join m.team t",
        TeamJPQL.class).getResultList();
    List<AddressJPQL> embeddedTypeList = em.createQuery("select o.address from OrderJPQL o",
        AddressJPQL.class).getResultList();

//    List<Object[]> scalarTypeList = em.createQuery("select m.username, m.age from MemberJPQL m")
//        .getResultList();
    List<MemberDto> scalarTypeList = em.createQuery(
            "select new jpql.MemberDto(m.username, m.age) from MemberJPQL m", MemberDto.class)
        .getResultList();
    System.out.println("username = " + scalarTypeList.get(0).getUsername());
    System.out.println("age = " + scalarTypeList.get(0).getAge());
  }

  private static void binding(EntityManager em) {
    List<MemberJPQL> nameBinding = em
        .createQuery("select m from MemberJPQL m where m.username = :username",
            MemberJPQL.class)
        .setParameter("username", "member1")
        .getResultList();

    List<MemberJPQL> locationBinding = em
        .createQuery("select m from MemberJPQL m where m.username = ?1",
            MemberJPQL.class)
        .setParameter(1, "member1")
        .getResultList();
  }

  private static void JPQLBasic(EntityManager em) {
    TypedQuery<MemberJPQL> query1 = em
        .createQuery("select m from MemberJPQL m", MemberJPQL.class);
    List<MemberJPQL> resultList = query1.getResultList();
    Query query2 = em
        .createQuery("select m.username, m.age from MemberJPQL m");
  }

  private static void nativeSQL(EntityManager entityManager) {
    entityManager.createNativeQuery("select * from MemberJPQL").getResultList();
  }

  private static void Criteria(EntityManager entityManager) {
    CriteriaBuilder cb = entityManager.getCriteriaBuilder();
    CriteriaQuery<MemberJPQL> query = cb.createQuery(MemberJPQL.class);

    Root<MemberJPQL> m = query.from(MemberJPQL.class);
    CriteriaQuery<MemberJPQL> cq = query.select(m).where(cb.equal(m.get("username"), "kim"));
    List<MemberJPQL> resultList = entityManager.createQuery(cq).getResultList();
  }

  private static void JPQL(EntityManager entityManager) {
    List resultList = entityManager
        .createQuery("select m From MemberJPQL  m where m.username like '%kim%'")
        .getResultList();
  }
}

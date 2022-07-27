package valueType;

import java.time.LocalDateTime;
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
      basicTest(em);
      valueTypeReferenceErrorTest(em);
      valueTypeCollectionTest(em);
      tx.commit();
    } catch (Exception e) {
      tx.rollback();
      e.printStackTrace();
    } finally {
      em.close();
    }

    emf.close();
  }

  private static void valueTypeCollectionTest(EntityManager em) {
    ValueTypeMember member = new ValueTypeMember();
    member.setUsername("user1");
    member.setAddress(new ValueTypeAddress("city", "street", "100"));
    member.setPeriod(new ValueTypePeriod(LocalDateTime.now(), LocalDateTime.now()));

    member.getFavoriteFood().add("치킨");
    member.getFavoriteFood().add("피자");
    member.getFavoriteFood().add("햄버거");

    member.getAddressHistory().add(new ValueTypeAddress("old1", "street", "100"));
    member.getAddressHistory().add(new ValueTypeAddress("old2", "street", "100"));
    em.persist(member);
  }

  private static void basicTest(EntityManager em) {
    ValueTypeMember member = new ValueTypeMember();
    member.setUsername("user1");
    member.setAddress(new ValueTypeAddress("city", "street", "100"));
    member.setPeriod(new ValueTypePeriod(LocalDateTime.now(), LocalDateTime.now()));
    em.persist(member);
  }

  private static void valueTypeReferenceErrorTest(EntityManager em) {
    ValueTypeAddress address = new ValueTypeAddress("city", "street", "100");

    ValueTypeMember member1 = new ValueTypeMember();
    member1.setUsername("user1");
    member1.setAddress(address);
    member1.setPeriod(new ValueTypePeriod(LocalDateTime.now(), LocalDateTime.now()));
    em.persist(member1);

    ValueTypeAddress copyAddress = new ValueTypeAddress(address.getCity(), address.getStreet(),
        address.getZipcode());

    ValueTypeMember member2 = new ValueTypeMember();
    member2.setUsername("user2");
    member2.setAddress(copyAddress);
    member2.setPeriod(new ValueTypePeriod(LocalDateTime.now(), LocalDateTime.now()));
    em.persist(member2);

//      member1.getAddress().setCity("city2"); // member2의 값도 바뀐다.
  }

}

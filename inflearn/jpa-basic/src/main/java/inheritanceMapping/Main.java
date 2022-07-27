package inheritanceMapping;

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

      InheritanceMovie movie = new InheritanceMovie();
      movie.setDirector("aaa");
      movie.setActor("bbb");
      movie.setName("바람과 함께 사라지다");
      movie.setPrice(10000);
      movie.setCreatedBy("kim");
      movie.setLastModifiedBy("kim");
      movie.setCreateDate(LocalDateTime.now());
      movie.setLastModifiedDate(LocalDateTime.now());
      em.persist(movie);

      em.flush();
      em.clear();

      InheritanceAlbum album = new InheritanceAlbum();
      album.setPrice(10);
      album.setName("album1");
      album.setArtist("arti");
      album.setPage(10);
      em.persist(album);

      InheritanceBook book = new InheritanceBook();
      book.setAuthor("author1");
      book.setIsbn("isbn1");
      book.setPage(20);
      book.setPrice(13000);
      em.persist(book);

      InheritanceMovie findMovie = em.find(InheritanceMovie.class, movie.getId());
      System.out.println("findMovie = " + findMovie);

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

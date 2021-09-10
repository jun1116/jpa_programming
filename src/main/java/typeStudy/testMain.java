package typeStudy;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class testMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            Member2 member = new Member2();
            member.setName("KIM");
            Address address = new Address("부천시", "중동로", 33333);
            member.setHomeAddress(address);
            member.setWorkPeriod(new Period());
            em.persist(member);

//            address.setCity("수원시");
//            address.setStreet("월드컵로");
            Member2 member2 = new Member2();
            member2.setHomeAddress(new Address("수원시", "월드컵로", address.getZipcode()));
            em.persist(member2);


            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }finally {
            System.out.println("\n\n---------------------COMMIT------------------");
            em.close();
            emf.close();
            return;
        }

    }
}

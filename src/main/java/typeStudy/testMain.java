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
            member.setCompanyAddress(new Address("Seoul","광화문로",33333));
            member.setHomeAddress(new Address("부천시","중동로",33333));
            member.setWorkPeriod(new Period());
            em.persist(member);

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

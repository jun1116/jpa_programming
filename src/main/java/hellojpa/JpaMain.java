package hellojpa;

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
            //팀 저장
//            Team team = new Team();
//            team.setName("TeamA");
//            em.persist(team);
//            //회원 저장
//            Member member1 = new Member();
//            Member member2 = new Member();
//            member1.setUsername("member1");
//            member2.setUsername("member2");
//            em.persist(member1);
//            em.persist(member2);
//
//            team.getMembers().add(member1);
//            team.getMembers().add(member2);


//            System.out.println("FLUSH, CLEAR");
//            System.out.println("NEW START");
            //멤버 조회

        } catch (Exception e) {
            tx.rollback();
        }finally {
            System.out.println("---------------------COMMIT------------------");
            tx.commit();
            em.close();
        }
        emf.close();
    }
}
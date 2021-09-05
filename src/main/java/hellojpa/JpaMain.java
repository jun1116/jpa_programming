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
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);
            //회원 저장
            Member member = new Member();
            member.setUsername("member1");
            em.persist(member);

            team.getMembers().add(member);


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
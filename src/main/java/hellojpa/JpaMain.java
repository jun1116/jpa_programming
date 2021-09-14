package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Team team1 = new Team();
            team1.setName("team1");
            Team team2 = new Team();
            team2.setName("team2");
            Team team3 = new Team();
            team3.setName("team3");
            em.persist(team1);
            em.persist(team2);
            em.persist(team3);

            Member member1 = new Member();
            member1.setUsername("KIM");
            member1.setTeam(team1);
            em.persist(member1);
            Member member2 = new Member();
            member2.setUsername("PARK");
            member2.setTeam(team2);
            em.persist(member2);

            em.flush();em.clear();
            System.out.println("\n===================CLEAR===============\n");
            String sql = "SELECT MEMBER_ID,USERNAME,TEAM_ID FROM MEMBER WHERE USERNAME='KIM'";
            List<Member> resultList = em.createNativeQuery(sql, Member.class).getResultList();
            for (Member member : resultList) {
                System.out.println("member = " + member.getUsername());
            }

            tx.commit();
        } catch (Exception e) {
            System.out.println("\n<EXCEPTION>\n");
            System.out.println("e = " + e);
            tx.rollback();
        }finally {
            System.out.println("\n\n---------------------COMMIT------------------");
            em.close();
            emf.close();
            return;
        }
    }

    private static void saveNoCascade(EntityManager em) {
        Team team = new Team();
        em.persist(team);

        Member member1 = new Member();
        Member member2 = new Member();
        member1.setTeam(team);
        member2.setTeam(team);
    }
}
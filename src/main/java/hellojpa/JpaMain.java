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
            member1.setUsername("member1");
            member1.setTeam(team1);
            em.persist(member1);
            Member member2 = new Member();
            member2.setUsername("member2");
            member2.setTeam(team2);
            em.persist(member2);

            em.flush();em.clear();
            List<Member> members = em.createQuery("select m from Member m", Member.class).getResultList();
            //select * from member
            //select * from team where team.id = member11.teamid~~
            System.out.println("members = " + members);

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
    public void printUserNTeam(String memberId,EntityManager em){
        Member member = em.find(Member.class, memberId);
        Team team = member.getTeam();
        System.out.println("team.getName() = " + team.getName());
        System.out.println("member = " + member.getUsername());
    }
    public void printUser(String memberId, EntityManager em){
        Member member = em.find(Member.class, memberId);
        Team team = member.getTeam();
        System.out.println("team.getName() = " + team.getName());
    }
}
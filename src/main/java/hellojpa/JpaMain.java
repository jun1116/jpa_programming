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
            Member member = new Member();
            member.setUsername("member1");

            em.persist(member);

            em.flush();
            em.clear();

            System.out.println("\n------------------------- FLUSH, CLEAR");
            System.out.println("\n------------------------- NEW START");
//            em.close();
            Member refMember = em.getReference(Member.class, member.getId());
            System.out.println("\ngetReference 실행완료");
            System.out.println("\n\nrefMember.getUsername() = " + refMember.getUsername());

            System.out.println(member==refMember);
            System.out.println(member==refMember);
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
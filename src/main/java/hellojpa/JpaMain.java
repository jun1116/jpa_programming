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
            Member member1 = new Member();
            member1.setUsername("member1");
            em.persist(member1);

            Member member2 = new Member();
            member2.setUsername("member2");
            em.persist(member2);

            em.flush();
            em.clear();

            System.out.println("\n------------------------- FLUSH, CLEAR\n");
            System.out.println("\n------------------------- NEW START\n");
            Member m1 = em.find(Member.class, member1.getId());
            Member m2 = em.getReference(Member.class, member2.getId());
            System.out.println("\nm1 InstanceOf : "+(m1 instanceof Member));
            System.out.println("\nm2 InstanceOf : "+(m2 instanceof Member));
            System.out.println("\n m1 == m2 "+(m1 == m2));

//            em.close();
//            Member refMember = em.getReference(Member.class, member1.getId());
//            System.out.println("\ngetReference 실행완료");
//            System.out.println("\n\nrefMember.getUsername() = " + refMember.getUsername());

//            System.out.println(member==refMember);
//            System.out.println(member==refMember);
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
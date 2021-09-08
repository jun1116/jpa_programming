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
            Member member1 = new Member();
            member1.setUsername("member1");
            em.persist(member1);
//
//            Member member2 = new Member();
//            member2.setUsername("member2");
//            em.persist(member2);

            em.flush();
            em.clear();

            System.out.println("\n------------------------- FLUSH, CLEAR\n");
            System.out.println("\n------------------------- NEW START\n");
            Member refMember = em.getReference(Member.class, member1.getId());

            System.out.println("\nemf.getPersistenceUnitUtil().isLoaded(refMember) = " + emf.getPersistenceUnitUtil().isLoaded(refMember));
            System.out.println("\nrefMember.getUsername()를 호출해서 초기화하기! = " + refMember.getUsername());
            System.out.println("\nemf.getPersistenceUnitUtil().isLoaded(refMember) = " + emf.getPersistenceUnitUtil().isLoaded(refMember));

            System.out.println("\n-------------------------END------------------");
//            Member findMember = em.find(Member.class, member1.getId());
//            System.out.println("\nfindMember.getClass() = " + findMember.getClass());//객체가나올거라 예상해
//            System.out.println("(refMember==findMember) = " + (refMember==findMember));

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
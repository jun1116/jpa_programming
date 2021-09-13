package typeStudy;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Set;

public class testMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            Member2 member = new Member2();
            member.setName("KIM");
            member.setHomeAddress(new Address("부천시", "중동로", 33333));
            member.setWorkPeriod(new Period());

            member.getFavoriteFoods().add("치킨");
            member.getFavoriteFoods().add("피자");
            member.getFavoriteFoods().add("떡볶이");

            member.getAddressHistory().add(new AddressEntity("부산", "태종대", 100));
            member.getAddressHistory().add(new AddressEntity("부산씨", "태종ㅁㄴㅇㄹ대", 100));
            em.persist(member);

            em.flush(); em.clear();
            System.out.println("----------------------clear------------");
            //Criteria 사용준비
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Member2> query = cb.createQuery(Member2.class);
            //루트클래스 (조회를 시작할 클래스)
            Root<Member2> m = query.from(Member2.class);

            //쿼리생성
            CriteriaQuery<Member2> cq = query.select(m).where(cb.equal(m.get("name"), "KIM"));
            List<Member2> resultList = em.createQuery(cq).getResultList();
            System.out.println("\nresultList = " + resultList);
            for (Member2 member2 : resultList) {
                System.out.println("member2 = " + member2.getName());
            }


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

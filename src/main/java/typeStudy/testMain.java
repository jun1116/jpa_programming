package typeStudy;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
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

            member.getAddressHistory().add(new Address("부산", "태종대", 100));
            member.getAddressHistory().add(new Address("부산씨", "태종ㅁㄴㅇㄹ대", 100));
            em.persist(member);

            em.flush(); em.clear();
            System.out.println("\n\n\n===================START===============\n");
            Member2 findMember = em.find(Member2.class, member.getId());
            System.out.println("\n AddressHistory 지연로딩이니??");
            List<Address> addressHistory = findMember.getAddressHistory();
            for (Address address : addressHistory) {
                System.out.println("address = " + address);
            }
            System.out.println("\n FavoriteFoods 지연로딩이니??");
            Set<String> favoriteFoods = findMember.getFavoriteFoods();
            for (String favoriteFood : favoriteFoods) {
                System.out.println("favoriteFood = " + favoriteFood);
            }

            findMember.getFavoriteFoods().remove("치킨");
            findMember.getFavoriteFoods().add("한식");


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

package typeStudy;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Member2 {
    @Id
    @GeneratedValue
    @Column(name="MEMBER2_ID")
    private Long id;
    private String name;
    @Embedded
    Address homeAddress;

    @Embedded
    Period workPeriod;

    @ElementCollection
    @CollectionTable(name = "FAVORITE_FOOD", joinColumns = @JoinColumn(name = "MEMBER2_ID"))
    @Column(name = "FOOD_NAME")//String 하나라서 예외적으로 컬럼이름을 만들 수 있어 (String은 값이 하나고, 내가 정의한게 아니라서)
    private Set<String> favoriteFoods = new HashSet<>();

    @ElementCollection
    @CollectionTable(name="ADDRESS", joinColumns = @JoinColumn(name="MEMBER2_ID"))
    private List<Address> addressHistory =new ArrayList<>();
    //Address는 내가 정의한것이라서 ..

    public Member2() {
    }

    public Set<String> getFavoriteFoods() {
        return favoriteFoods;
    }

    public void setFavoriteFoods(Set<String> favoriteFoods) {
        this.favoriteFoods = favoriteFoods;
    }

    public List<Address> getAddressHistory() {
        return addressHistory;
    }

    public void setAddressHistory(List<Address> addressHistory) {
        this.addressHistory = addressHistory;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getHomeAddress() {
        return homeAddress;
    }

    public Period getWorkPeriod() {
        return workPeriod;
    }

    public void setWorkPeriod(Period workPeriod) {
        this.workPeriod = workPeriod;
    }

    public void setHomeAddress(Address homeAddress) {
        this.homeAddress = homeAddress;
    }
}

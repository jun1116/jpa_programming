package typeStudy;

import javax.persistence.*;

@Entity
public class Member2 {
    @Id @GeneratedValue
    private Long id;
    private String name;
    @Embedded
    Address homeAddress;

    @Embedded
    Period workPeriod;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="city", column=@Column(name="COMPANY_CITY")),
            @AttributeOverride(name="street", column=@Column(name="COMPANY_STREET")),
            @AttributeOverride(name="zipcode", column=@Column(name="COMPANY_ZIPCODE")),})
    Address companyAddress;

    public Member2() {
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

    public void setHomeAddress(Address homeAddress) {
        this.homeAddress = homeAddress;
    }

    public Period getWorkPeriod() {
        return workPeriod;
    }

    public void setWorkPeriod(Period workPeriod) {
        this.workPeriod = workPeriod;
    }

    public Address getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(Address companyAddress) {
        this.companyAddress = companyAddress;
    }
}

package typeStudy;

import javax.persistence.Embeddable;

@Embeddable
public class Address {
    private String city;
    private String street;
    private int zipcode;
    public Address() {}

    public Address(String city, String street, int zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}

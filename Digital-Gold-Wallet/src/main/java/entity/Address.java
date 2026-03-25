package entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="addresses")
public class Address {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="address_id")
    private Integer addressId;
    @Column(name="street", length=255)
    private String street;
    @Column(name="city", length=100)
    private String city;
    @Column(name="state", length=100)
    private String state;
    @Column(name="postal_code", length=20)
    private String postalCode;
    @Column(name="country", length=100)
    private String country;

    public Address(String street, String city, String state, String postalCode, String country) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
        this.country = country;
    }
}

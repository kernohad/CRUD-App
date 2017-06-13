package com.kernohad;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * This is the entity class which Hibernate will automatically translate into a table.
 */
@Entity     // This tells Hiberbate to make a table out of this class
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    //@Size is a Java Bean annotation. Source: http://www.baeldung.com/javax-validation
    @Size(max=50, message = "'Name' should be less than 50 characters.")
    private String name;

    @Size(max=50, message = "'Email' should be less than 50 characters.")
    private String email;




    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<PhoneNumber> phoneNumbers;

    public List<PhoneNumber> getPhoneNumbers(){
        return phoneNumbers;
    }

    public void setPhoneNumbers(List<PhoneNumber> phoneNumbers){this.phoneNumbers = phoneNumbers;}

    public void addPhoneNumber(PhoneNumber phoneNumber){
        phoneNumbers.add(phoneNumber);
        phoneNumber.setUser(this);
    }

    public void removePhoneNumber(PhoneNumber phoneNumber){
        phoneNumbers.remove(phoneNumber);
        phoneNumber.setUser(null);
    }





    public Long getId() {
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }


}

package com.kernohad;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Columns;

import javax.persistence.*;

/**
 * Created by user on 6/6/2017.
 */
@Entity     // This tells Hiberbate to make a table out of this class
@Table(name="phonenumbers")
public class PhoneNumber {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="phonenumberid")
    private Long phoneNumberId;

    @Column(name="id", insertable = false, updatable = false)
    private Long id;

    @Column(name="type")
    private String type;

    @Column(name="number")
    private String number;




    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "id")
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getPhoneNumberId(){return phoneNumberId;}
    public void setPhoneNumberId(){this.phoneNumberId = phoneNumberId;}

    public Long getId(){return id;}
    public void setId(){this.id = id;}

    public String getType(){return type;}
    public void setType(){this.type = type;}

    public String getNumber(){return number;}
    public void setNumber(){this.number = number;}


}

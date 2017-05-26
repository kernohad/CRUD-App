package com.kernohad;

import com.sun.xml.internal.bind.v2.model.core.ID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * This is the entity class which Hibernate will automatically translate into a table.
 */
@Entity     // This tells Hiberbate to make a table out of this class
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)

    private Long id;

    private String name;

    private String email;

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

package com.kernohad;

import com.sun.xml.internal.bind.v2.model.core.ID;
import org.hibernate.validator.constraints.Email;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * This is the entity class which Hibernate will automatically translate into a table.
 */
@Entity     // This tells Hiberbate to make a table out of this class
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)

    //@Size is a Java Bean annotation. Source: http://www.baeldung.com/javax-validation
    @Size (max=16, message = "'ID' should be less than 16 characters")
    private Long id;

    @Size(max=50, message = "'Name' should be less than 50 characters")
    private String name;

    @Size(max=50, message = "'Email' should be less than 50 characters")
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

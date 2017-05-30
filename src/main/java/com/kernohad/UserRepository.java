package com.kernohad;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.kernohad.User;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;

/**
 * This is the repository interface, this will be automatically
 *  implemented by Spring in a bean with the same name with changing
 *  case The bean name will be userRepository
 */
public interface UserRepository extends CrudRepository<User, Long> {
    @Query("select u from User u where u.name = :name and u.email = :email")
    User findUser(@Param("name") String name, @Param("email") String email);

    @Query("select u from User u where u.name = :name or u.email = :email or u.id = :id")
     ArrayList<User> search(@Param("name") String name, @Param("email") String email, @Param("id") Long id);


}

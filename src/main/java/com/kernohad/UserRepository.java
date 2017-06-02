package com.kernohad;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.kernohad.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;

/**
 * This is the repository interface, this will be automatically
 *  implemented by Spring in a bean with the same name with changing
 *  case The bean name will be userRepository
 */
public interface UserRepository extends PagingAndSortingRepository<User, Long>  {
    @Query("select u from User u where u.name = :name and u.email = :email")
    User findUser(@Param("name") String name, @Param("email") String email);

    @Query("select u from User u where u.name like :name% or u.email like :email% or u.id like :id")
    Page<User> search(@Param("name") String name, @Param("email") String email, @Param("id") Long id, Pageable pageable);


}

package com.kernohad;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

/**
 * This is the repository interface, this will be automatically
 *  implemented by Spring in a bean with the same name with changing
 *  case The bean name will be userRepository
 */
public interface UserRepository extends PagingAndSortingRepository<User, Long>  {
    @Query("select u from User u where u.name = :name and u.email = :email")
    User findUser(@Param("name") String name, @Param("email") String email);

    @Query("select u from User u where u.name like :name% and u.email like :email% and u.id like :id")
    Page<User> searchNameEmailId(@Param("name") String name, @Param("email") String email, @Param("id") Long id, Pageable pageable);

    @Query("select u from User u where u.id like :id")
    Page<User> searchId(@Param("id") Long id, Pageable pageable);

    @Query("select u from User u where u.name like :name%")
    Page<User> searchName(@Param("name") String name, Pageable pageable);

    @Query("select u from User u where u.email like :email%")
    Page<User> searchEmail(@Param("email") String email, Pageable pageable);

    @Query("select u from User u where u.name like :name% and u.id like :id")
    Page<User> searchIdName(@Param("name") String name, @Param("id") Long id, Pageable pageable);

    @Query("select u from User u where u.email like :email% and u.id like :id")
    Page<User> searchIdEmail(@Param("email") String email, @Param("id") Long id, Pageable pageable);

    @Query("select u from User u where u.name like :name% and u.email like :email%")
    Page<User> searchNameEmail(@Param("name") String name, @Param("email") String email, Pageable pageable);


}

package com.kernohad;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by user on 6/6/2017.
 */
public interface PhoneNumbersRepository extends PagingAndSortingRepository<PhoneNumber, Long> {

    @Query("select p from PhoneNumber p where p.id = :id")
    List<PhoneNumber> getContactInfo(@Param("id") Long id);

}

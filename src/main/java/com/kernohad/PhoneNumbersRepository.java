package com.kernohad;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 6/6/2017.
 */
public interface PhoneNumbersRepository extends PagingAndSortingRepository<PhoneNumbers, Long> {

    @Query("select p from PhoneNumbers p where p.id = :id")
    List<PhoneNumbers> getContactInfo(@Param("id") Long id);

}

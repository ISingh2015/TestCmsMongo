package com.cms.repo;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import com.cms.exception.CmsRecordNotFoundException;
import com.cms.vo.CmsCustomer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

/**
 * @author : Inderjit SS
 * @version: v1.0.0
 * @github: https://github.com/ISingh2015/TestMongo
 * @since  : 06.06.2019
 */

public interface CustomerRepository extends MongoRepository<CmsCustomer, Long> {


    /**
     *
     * @param firstName
     * @return
     */
    @Query("{ 'firstName' : ?0 }")
    public List<CmsCustomer> findByFirstName(String firstName);

    /**
     *
     * @param LastName
     * @return
     */
    @Query("{ 'lastName' : ?0 }")
    public List<CmsCustomer> findByLastName(String LastName);

    /**
     *
     * @param email
     * @param number
     * @return
     * @throws  CmsRecordNotFoundException
     */
    @Query("{ 'email' : ?0 ,'mobileNo':?1}")
    public Optional<CmsCustomer> getCustomerByEmailAndMobileNumber(String email, Long number) throws CmsRecordNotFoundException;

    /**
     *
     * @param userId
     * @return
     * @throws  CmsRecordNotFoundException
     */
    @Query("{ 'userId' : ?0 }, Sort: {'firstName' : 1 }")
    public Collection<CmsCustomer> getAllCustomers(Long userId) throws  CmsRecordNotFoundException;
}

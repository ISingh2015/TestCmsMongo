package com.cms.service;

import com.cms.exception.CmsRecordNotFoundException;
import com.cms.vo.CmsCustomer;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * @author : Inderjit SS
 * @version: v1.0.0
 * @github: https://github.com/ISingh2015/TestMongo
 * @since : 06.06.2019
 */
@Service
public interface CmsCustomerService {

    /**
     * @return
     * @throws CmsRecordNotFoundException
     */
    public List<CmsCustomer> getCustomerByFirstName(String firstName) throws CmsRecordNotFoundException;

    /**
     *
     * @param id
     * @return
     * @throws CmsRecordNotFoundException
     */
    public Optional<CmsCustomer> getCustomerById(long id) throws CmsRecordNotFoundException;

    /**
     * @return
     * @throws CmsRecordNotFoundException
     */
    public List<CmsCustomer> getCustomerByLastName(String LastName) throws CmsRecordNotFoundException;

    /**
     * @param email
     * @param phone
     * @return
     * @throws CmsRecordNotFoundException
     */

    public Optional<CmsCustomer> getCustomerByEmailAndPhone(String email, Long phone) throws CmsRecordNotFoundException;

    /**
     * @param cmsCustomer
     * @return
     * @throws CmsRecordNotFoundException
     */
    public boolean createCustomer(CmsCustomer cmsCustomer) throws CmsRecordNotFoundException;

    /**
     *
     * @param userId
     * @return
     * @throws CmsRecordNotFoundException
     */
    public Collection<CmsCustomer> getAllCustomers(Long userId) throws CmsRecordNotFoundException;

    /**
     * @param cmsCustomerList
     * @return
     * @throws CmsRecordNotFoundException
     */
    public Boolean updateCustomer(List<CmsCustomer> cmsCustomerList) throws CmsRecordNotFoundException;

    /**
     * @param cmsCustomerList
     * @return
     * @throws CmsRecordNotFoundException
     */
    public Boolean deleteCustomer(List<CmsCustomer> cmsCustomerList) throws CmsRecordNotFoundException;
}

package com.cms.service.impl;

import com.cms.repo.CustomerRepository;
import com.cms.service.CmsCustomerService;
import com.cms.service.CmsMongoSequenceGeneratorService;
import com.cms.exception.CmsRecordNotFoundException;
import com.cms.vo.CmsCustomer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.invoke.MethodHandles;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * @author : Inderjit SS
 * @version: v1.0.0
 * @github: https://github.com/ISingh2015/TestMongo
 * @since : 06.06.2019
 */

public class CmsCustomerServiceImpl implements CmsCustomerService {

    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
//    final StaticLoggerBinder staticLoggerBinder = StaticLoggerBinder.getSingleton();

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    CmsMongoSequenceGeneratorService sequenceGenerator;

    @Override
    public List<CmsCustomer> getCustomerByFirstName(String firstName) throws CmsRecordNotFoundException {
        List<CmsCustomer> cmsCustomerList;
        try {
            cmsCustomerList = customerRepository.findByFirstName(firstName);
        } catch (Exception e) {
            logger.error(getClass().getName() + " : getByFirstName Failed : " + e.getMessage());
            throw new  CmsRecordNotFoundException(false, firstName);
        }
        return cmsCustomerList;
    }

    @Override
    public Optional<CmsCustomer> getCustomerById(long id) throws  CmsRecordNotFoundException {
        Optional<CmsCustomer> customer = null;
        try {
            customer = customerRepository.findById(id);
        } catch (Exception e) {
            logger.error(getClass().getName() + " : GetAll Failed : " + e.getMessage());
            throw new  CmsRecordNotFoundException(false,String.valueOf(id));
        }
        return customer;

    }

    @Override
    public List<CmsCustomer> getCustomerByLastName(String lastName) throws  CmsRecordNotFoundException {
        List<CmsCustomer> cmsCustomerList;
        try {
            cmsCustomerList = customerRepository.findByLastName(lastName);
        } catch (Exception e) {
            logger.error(getClass().getName() + " : getByLastName Failed : " + e.getMessage());
            throw new  CmsRecordNotFoundException(false,lastName);
        }
        return cmsCustomerList;
    }

    @Override
    public Optional<CmsCustomer> getCustomerByEmailAndPhone(String email, Long phone) throws  CmsRecordNotFoundException {
        Optional<CmsCustomer> user;
        try {
            user = customerRepository.getCustomerByEmailAndMobileNumber(email, phone);
        } catch (Exception e) {
            logger.error(getClass().getName() + " : getByEmailAndPhone Failed : " + e.getMessage());
            throw new  CmsRecordNotFoundException(false, email + String.valueOf(phone));
        }
        return user;
    }

    @Override
    public boolean createCustomer(CmsCustomer cmsCustomer) throws  CmsRecordNotFoundException {
        boolean savedAll = false;
        try {
            if ((cmsCustomer.getId() == null || "".equals(cmsCustomer.getId()) && cmsCustomer.validCustomer())) {
                cmsCustomer.setId(sequenceGenerator.generateSequence(CmsCustomer.SEQUENCE_NAME));
                customerRepository.save(cmsCustomer);
            }
            savedAll = true;
        } catch (Exception e) {
            logger.error(getClass().getName() + " : create Failed" + e.getMessage());
            throw new  CmsRecordNotFoundException(false, cmsCustomer.toString());
        }
        return savedAll;
    }

    @Override

    public Collection<CmsCustomer> getAllCustomers(Long userId) throws  CmsRecordNotFoundException {
        Collection<CmsCustomer> cmsCustomerList = null;
        try {
            cmsCustomerList = customerRepository.getAllCustomers(userId);
        } catch (Exception e) {
            logger.error(getClass().getName() + " : GetAll Failed : " + e.getMessage());
            throw new  CmsRecordNotFoundException(false, String.valueOf(userId));
        }
        return cmsCustomerList;
    }

    @Override
    public Boolean updateCustomer(List<CmsCustomer> cmsCustomerList) throws  CmsRecordNotFoundException {
        boolean updated = false;
        try {
            for (CmsCustomer cmsCustomer : cmsCustomerList) {
                if (cmsCustomer != null && cmsCustomer.getId() != 0) {
                    customerRepository.save(cmsCustomer);
                    updated = true;
                }
            }
        } catch (Exception e) {
            logger.error(getClass().getName() + " : Update Failed : " + e.getMessage());
            throw new  CmsRecordNotFoundException(false,cmsCustomerList.toString());
        }
        return updated;
    }

    @Override
    public Boolean deleteCustomer(List<CmsCustomer> cmsCustomerList) throws  CmsRecordNotFoundException {
        boolean deleted = true;
        try {
            for (CmsCustomer cmsCustomer : cmsCustomerList) {
                if (cmsCustomer != null) {
                    customerRepository.delete(cmsCustomer);
                } else {
                    deleted = false;
                }
            }
        } catch (Exception e) {
            logger.error(getClass().getName() + " : Delete Failed : " + e.getMessage());
            throw new  CmsRecordNotFoundException(false, customerRepository.toString());
        }
        return deleted;
    }
}

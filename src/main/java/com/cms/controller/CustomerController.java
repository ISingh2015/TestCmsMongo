package com.cms.controller;

import com.cms.exception.CmsRecordNotFoundException;
import com.cms.service.CmsCustomerService;
import com.cms.vo.CmsCustomer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

/**
 * @author : Inderjit SS
 * @version: v1.0.0
 * @github: https://github.com/ISingh2015/TestMongo
 * @since : 06.06.2019
 */
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CustomerController {

    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Autowired
    private CmsCustomerService cmsCustomerService;

    /**
     * @param cmsCustomer
     * @return
     */
    @PostMapping(value = "/customer/create")
    public ResponseEntity<?> createCustomer(@RequestBody CmsCustomer cmsCustomer) throws CmsRecordNotFoundException {
        boolean saved = false;
        cmsCustomerService.createCustomer(cmsCustomer);
        return saved ? ResponseEntity.ok(cmsCustomer) : ResponseEntity.ok("Could Not Save");
    }

    /**
     * @return
     */
    @GetMapping(value = "/customer/{id}")
    public ResponseEntity<?> getCustomerById(@PathVariable("id") long id) {
        Optional<CmsCustomer> customer = null;
        try {
            customer = cmsCustomerService.getCustomerById(id);
        } catch (Exception e) {
            logger.error(getClass().getName() + " COULD NOT Get CmsCustomer(s) " + e.getMessage());
        }
        return ResponseEntity.ok(customer);
    }
    //TODO add error page in view resolver
    @GetMapping(value = "/customer/getForUser/{id}")
    public ResponseEntity<?> getCustomers(@PathVariable("id") long id) {
        Collection<CmsCustomer> cmsCustomerList = null;
        try {
            cmsCustomerList = cmsCustomerService.getAllCustomers(id);
        } catch (Exception e) {
            logger.error(getClass().getName() + " COULD NOT List CmsCustomer(s) " + e.getMessage());
        }
        return ResponseEntity.ok(cmsCustomerList);
    }

    /**
     * @param cmsCustomer
     * @return
     */
    @PutMapping(value = "/customer/edit")
    public ResponseEntity<?> updateCustomer(@RequestBody CmsCustomer cmsCustomer) {
        boolean updated = false;
        ArrayList<CmsCustomer> cmsCustomerList = new ArrayList<>();
        cmsCustomerList.add(cmsCustomer);
        try {
            updated = cmsCustomerService.updateCustomer(cmsCustomerList);
        } catch (Exception e) {
            logger.error(getClass().getName() + " COULD NOT Update CmsCustomer " + e.getMessage());
        }
        return updated ? ResponseEntity.ok(cmsCustomerList) : ResponseEntity.ok("Could Not Update CmsCustomer " + cmsCustomer.getFirstName());
    }

    /**
     * @param cmsCustomer
     * @return
     */
    @PostMapping(value = "/customer/delete")
    public ResponseEntity<?> deleteCustomer(@RequestBody CmsCustomer cmsCustomer) {
        boolean deleted = false;
        ArrayList<CmsCustomer> cmsCustomerList = new ArrayList<>();
        cmsCustomerList.add(cmsCustomer);
        try {
            deleted = cmsCustomerService.deleteCustomer(cmsCustomerList);
        } catch (Exception e) {
            logger.error(getClass().getName() + " COULD NOT Delete CmsCustomer(s) " + e.getMessage());
        }
        return deleted ? ResponseEntity.ok(deleted) : ResponseEntity.ok("Could Not Delete CmsCustomer");
    }

}

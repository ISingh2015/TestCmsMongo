package com.cms;

import com.cms.service.CmsCustomerService;
import com.cms.vo.CmsCustomer;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * @author : Inderjit SS
 * @version: v1.0.0
 * @github: https://github.com/ISingh2015/TestMongo
 * @since : 06.06.2019
 */


@RunWith(SpringRunner.class)
@SpringBootTest
public class CmsCmsCustomerServiceTest {

    @Autowired
    private CmsCustomerService cmsCustomerService;

    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Test
    public void saveCustomer() {
        CmsCustomer cmsCustomer = new CmsCustomer(2004l,2000l,"Inderjit", "Singh", "New Address", new Long(0), "testEmail@testdomain.com", new Long(0),"issan","1234");
        try {
            cmsCustomerService.createCustomer(cmsCustomer);
        } catch (Exception e) {
            logger.error(getClass().getName() + " : save CmsCustomer" + e.getMessage());
        }
        Assert.assertNotEquals(cmsCustomer.getId(), null);
    }

    @Test
    public void getAllCustomers() {
        Collection<CmsCustomer> cmsCustomerList = null;
        try {
            cmsCustomerList = cmsCustomerService.getAllCustomers(1l);
            System.out.println(cmsCustomerList.toString());
        } catch (Exception e) {
            logger.error(getClass().getName() + " : getAll CmsCustomer" + e.getMessage());
        }
        Assert.assertNotEquals(cmsCustomerList, null);
    }

    @Test
    public void updateCustomer() {
        List<CmsCustomer> cmsCustomerList = new ArrayList<>();
        CmsCustomer oliver = new CmsCustomer(2002l,2000l,"Oliver August", "Matthews", "New Address ", new Long(0), "oliver@testdomain.com", new Long(0),"oliver","1234");
        oliver.setAddress(oliver.getAddress() + " New");
        oliver.setUserRole(oliver.getUserRole()+" Tester");
        cmsCustomerList.add(oliver);
        try {
            cmsCustomerService.updateCustomer(cmsCustomerList);
        } catch (Exception e) {
            logger.error(getClass().getName() + " : update CmsCustomer" + e.getMessage());
        }
        Assert.assertNotEquals(cmsCustomerList, null);
    }

    @Test
    public void getCustomerByFirstName() {
        List<CmsCustomer> cmsCustomerList = null;
        try {
            cmsCustomerList = cmsCustomerService.getCustomerByFirstName("Dave");
        } catch (Exception e) {
            logger.error(getClass().getName() + " : customer By FirstName" + e.getMessage());
        }
        Assert.assertNotEquals(cmsCustomerList, null);

    }

    @Test
    public void getCustomerByLastName() {
        List<CmsCustomer> cmsCustomerList = null;
        try {
            cmsCustomerList = cmsCustomerService.getCustomerByLastName("Sanhotra");
        } catch (Exception e) {
            logger.error(getClass().getName() + " : customer By LastName" + e.getMessage());
        }
        Assert.assertNotEquals(cmsCustomerList, null);

    }

    @Test
    public void getCustomerByEmailAndPhone() {
        Optional<CmsCustomer> customer = null;
        try {
            customer = cmsCustomerService.getCustomerByEmailAndPhone("iss@zed.ee", 77399988l);
        } catch (Exception e) {
            logger.error(getClass().getName() + " : customer By Email And Phone " + e.getMessage());
        }
        Assert.assertNotEquals(customer, null);

    }

    @Test
    public void getCustomerById() {
        Optional<CmsCustomer> customer = null;
        Long custId=2000l;
        try {
            customer= cmsCustomerService.getCustomerById(custId);
        } catch (Exception e) {
            logger.error(getClass().getName() + " : customerBy Email And Phone " + e.getMessage());
        }
        Assert.assertNotEquals(customer, null);

    }
}

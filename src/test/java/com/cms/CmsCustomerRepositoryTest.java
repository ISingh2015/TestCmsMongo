package com.cms;

import static org.assertj.core.api.Assertions.*;

import java.lang.invoke.MethodHandles;
import java.util.List;

import com.cms.repo.CustomerRepository;
import com.cms.vo.CmsCustomer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author : Inderjit SS
 * @version: v1.0.0
 * @github: https://github.com/ISingh2015/TestMongo
 * @since : 06.06.2019
 */


@RunWith(SpringRunner.class)
@SpringBootTest
public class CmsCustomerRepositoryTest {

    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Autowired
    CustomerRepository repository;

    CmsCustomer dave, oliver, carter, inderjit;

    @Before
    public void setUp() {

        repository.deleteAll();
        logger.info(getClass().getName() + " Test Deleting all CmsCustomer(s) : " );
        dave = repository.save(new CmsCustomer(2000l,2000l,"Dave", "Matthews", "New Address", new Long(0), "dave@testdomain.com", new Long(0),"dave","1234"));
        oliver = repository.save(new CmsCustomer(2002l,2000l,"Oliver August", "Matthews", "New Address", new Long(0), "oliver@testdomain.com", new Long(0),"oliver","1234"));
        carter = repository.save(new CmsCustomer(2003l,2000l,"Carter", "Beauford", "New Address", new Long(0), "carter@testdomain.com", new Long(0),"carter","1234"));
        inderjit = repository.save(new CmsCustomer(2004l,2000l,"Inderjit", "Sanhotra", "New Address", new Long(0), "issan@testdomain.com", new Long(0),"issan","1234"));
        logger.info(getClass().getName() + " Test Initialized CmsCustomer(s) : " );
    }

    @Test
    public void setsIdOnSave() {
        dave = repository.save(dave);
        logger.info(getClass().getName() + " Test Saved CmsCustomer(s) : Dave " );
        assertThat(dave.getId()).isNotNull();

    }

    @Test
    public void findsByLastName() {
        List<CmsCustomer> result = repository.findByLastName("Beauford");
        logger.info(getClass().getName() + " Test Find By Last Name CmsCustomer(s) : " );
        assertThat(result).hasSize(1).extracting("firstName").contains("Carter");
    }

    @Test
    public void findsByExample() {
        CmsCustomer probe = new CmsCustomer(null, null,null, "Matthews", "", new Long(0), "", new Long(0),"","");
        List<CmsCustomer> result = repository.findAll(Example.of(probe));
        logger.info(getClass().getName() + " Test Find all CmsCustomer(s) and Probe : " );
        assertThat(result).hasSize(2).extracting("firstName").contains("Dave", "Oliver August");
    }

//    @Test
//    public void deleteEntry() {
//        String deleted = repository.deleteCustomerByFirstName("Inderjit");
//        System.out.println("Deleted " + deleted);
//    }

}

package com.cms;

import com.cms.service.impl.CmsUserDetailsService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.invoke.MethodHandles;

/**
 * @author : Inderjit SS
 * @version: v1.0.0
 * @github: https://github.com/ISingh2015/TestMongo
 * @since : 06.06.2019
 */


@RunWith(SpringRunner.class)
@SpringBootTest

public class UserDetailServiceTest {
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Autowired
    private CmsUserDetailsService cmsUserDetailsService;

    @Test
    public void getCMSUserTest() {
        UserDetails userDetails = cmsUserDetailsService.loadUserByUsername("User");
        Assert.assertNotEquals(userDetails, null);
    }
}

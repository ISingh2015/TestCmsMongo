package com.cms;

import com.cms.service.CmsKeyValueService;
import com.cms.vo.CmsKeyValues;
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

/**
 * @author : Inderjit SS
 * @version: v1.0.0
 * @github: https://github.com/ISingh2015/TestMongo
 * @since : 22.07.2019
 */


@RunWith(SpringRunner.class)
@SpringBootTest
public class CmsKeyValuesServiceTest {

    @Autowired
    private CmsKeyValueService keyValuesService;

    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Test
    public void saveKeyValues() {
        CmsKeyValues cmsKeyValues = new CmsKeyValues(1l, 2000l,"key","value");
        List<CmsKeyValues> cmsKeyValuesList = new ArrayList<>();
        cmsKeyValuesList.add(cmsKeyValues);
        try {
            keyValuesService.createKeyValues(cmsKeyValuesList);
        } catch (Exception e) {
            logger.error(getClass().getName() + " : save CmsKeyValues" + e.getMessage());
        }
        Assert.assertNotEquals(cmsKeyValuesList.get(0).getId(), null);
    }

    @Test
    public void getAllKeyValuess() {
        Collection<CmsKeyValues> cmsKeyValuesList = null;
        try {
            cmsKeyValuesList = keyValuesService.getAllKeyValues(2000l);
        } catch (Exception e) {
            logger.error(getClass().getName() + " : getAll CmsKeyValues" + e.getMessage());
        }
        Assert.assertNotEquals(cmsKeyValuesList, null);
    }

    @Test
    public void updateKeyValues() {
        List<CmsKeyValues> cmsKeyValuesList = new ArrayList<>();
        CmsKeyValues oliverKey = new CmsKeyValues(1l,2000l,"key","new value");
        cmsKeyValuesList.add(oliverKey);
        try {
            keyValuesService.updateKeyValues(cmsKeyValuesList);
        } catch (Exception e) {
            logger.error(getClass().getName() + " : update CmsKeyValues" + e.getMessage());
        }
        Assert.assertNotEquals(cmsKeyValuesList, null);
    }


    @Test
    public void getKeyValuesById() {
        CmsKeyValues cmsKeyValues = null;
        Long keyValuesId=14l;
        try {
            cmsKeyValues = keyValuesService.getKeyValuesById(keyValuesId);
        } catch (Exception e) {
            logger.error(getClass().getName() + " : keyValuesBy Id " + e.getMessage());
        }
        Assert.assertNotEquals(cmsKeyValues, null);

    }
}

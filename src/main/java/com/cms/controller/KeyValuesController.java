package com.cms.controller;

import com.cms.exception.CmsRecordNotFoundException;
import com.cms.service.CmsKeyValueService;
import com.cms.vo.CmsKeyValues;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.Collection;

/**
 * @author : Inderjit SS
 * @version: v1.0.0
 * @github: https://github.com/ISingh2015/TestMongo
 * @since : 06.06.2019
 */
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")

public class KeyValuesController {

    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Autowired
    private CmsKeyValueService cmsKeyValueService;

    @PostMapping(value = "/keyValue/create")
    public ResponseEntity<?> createKeyValue(@RequestBody CmsKeyValues keyValue) {
        boolean saved = false;
        ArrayList<CmsKeyValues> cmsKeyValuesList = new ArrayList<>();
        cmsKeyValuesList.add(keyValue);
        try {
            saved = cmsKeyValueService.createKeyValues(cmsKeyValuesList);
        } catch (Exception e) {
            logger.error(getClass().getName() + " COULD NOT Create CmsCustomer(s) " + e.getMessage());
        }
        return saved ? ResponseEntity.ok(cmsKeyValuesList) : ResponseEntity.ok("Could Not Save");
    }

    @GetMapping(value = "/keyValue/getForUser/{userId}")
    public ResponseEntity<?> getKeyValues(@PathVariable("userId") long userId) {
        Collection<CmsKeyValues> cmsKeyValuesList = null;
        try {
            cmsKeyValuesList = cmsKeyValueService.getAllKeyValues(userId);
            if (cmsKeyValuesList == null || cmsKeyValuesList.isEmpty())
                return new ResponseEntity(new CmsRecordNotFoundException(false, "Nothing Found!"), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            logger.error(getClass().getName() + " COULD NOT List CmsCustomer(s) " + e.getMessage());
        }
        return ResponseEntity.ok(cmsKeyValuesList);
    }

    /**
     * @param id
     * @return
     */
    @GetMapping(value = "/keyValue/{id}")
    public ResponseEntity<?> getKeyValueById(@PathVariable("id") long id) {
        CmsKeyValues keyValue = cmsKeyValueService.getKeyValuesById(id);
        return ResponseEntity.ok(keyValue);
    }

    @PostMapping(value = "/keyValue/edit")
    public ResponseEntity<?> updateKeyValue(@RequestBody CmsKeyValues keyValue) {
        boolean updated = false;
        ArrayList<CmsKeyValues> cmsKeyValuesList = new ArrayList<>();
        cmsKeyValuesList.add(keyValue);
        try {
            updated = cmsKeyValueService.updateKeyValues(cmsKeyValuesList);
        } catch (Exception e) {
            logger.error(getClass().getName() + " COULD NOT Update KeyValue" + e.getMessage());
        }
        return updated ? ResponseEntity.ok(cmsKeyValuesList) : ResponseEntity.ok("Could Not Update Key : " + keyValue.getKeyName());
    }

    @PostMapping(value = "/keyValue/delete")
    public ResponseEntity<?> deleteKeyValue(@RequestBody CmsKeyValues cmsKeyValues) {
        boolean deleted = false;
        ArrayList<CmsKeyValues> cmsKeyValuesList = new ArrayList<>();
        cmsKeyValuesList.add(cmsKeyValues);
        try {
            deleted = cmsKeyValueService.deleteKeyValues(cmsKeyValuesList);
        } catch (Exception e) {
            logger.error(getClass().getName() + " COULD NOT Delete KeyValue " + e.getMessage());
        }
        return deleted ? ResponseEntity.ok(deleted) : ResponseEntity.ok("Could Not Delete Key : " + cmsKeyValues.getKeyName());
    }

}

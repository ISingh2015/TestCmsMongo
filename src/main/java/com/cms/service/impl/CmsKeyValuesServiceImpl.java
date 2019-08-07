package com.cms.service.impl;

import com.cms.exception.CmsRecordNotFoundException;
import com.cms.repo.KeyValuesRepository;
import com.cms.service.CmsKeyValueService;
import com.cms.service.CmsMongoSequenceGeneratorService;
import com.cms.vo.CmsKeyValues;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.invoke.MethodHandles;
import java.util.Collection;
import java.util.List;

/**
 * @author : Inderjit SS
 * @version: v1.0.0
 * @github: https://github.com/ISingh2015/TestMongo
 * @since : 22.07.2019
 */

public class CmsKeyValuesServiceImpl implements CmsKeyValueService {

    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Autowired
    private KeyValuesRepository keyValuesRepository;

    @Autowired
    CmsMongoSequenceGeneratorService sequenceGenerator;

    @Override
    public Collection<CmsKeyValues> getAllKeyValues(long userId) throws  CmsRecordNotFoundException {
        Collection<CmsKeyValues> cmsKeyValuesList;
        try {
            cmsKeyValuesList = keyValuesRepository.getAllKeyValues(userId);
        } catch (Exception e) {
            logger.error(getClass().getName() + " : getAllKeyValues Failed : " + e.getMessage());
            throw new  CmsRecordNotFoundException(false, String.valueOf(userId));
        }
        return cmsKeyValuesList;
    }

    @Override
    public CmsKeyValues getKeyValuesById(long id) {
        return keyValuesRepository.findById(id).orElseThrow( () -> new CmsRecordNotFoundException(false,"Key Value Not Found"));
    }

    @Override
    public Boolean updateKeyValues(List<CmsKeyValues> cmsKeyValuesList) throws  CmsRecordNotFoundException {
        boolean updated = false;
        try {
            for (CmsKeyValues cmsKeyValues : cmsKeyValuesList) {
                if (cmsKeyValues != null && cmsKeyValues.getId()!=0) {
                    keyValuesRepository.save(cmsKeyValues);
                    updated = true;
                }
            }
        } catch (Exception e) {
            logger.error(getClass().getName() + " : Update Failed : " + e.getMessage());
            throw new  CmsRecordNotFoundException(false,cmsKeyValuesList.toString());
        }
        return updated;
    }

    @Override
    public Boolean deleteKeyValues(List<CmsKeyValues> cmsKeyValuesList) throws  CmsRecordNotFoundException {
        boolean deleted  = false;
        try {
            for (CmsKeyValues cmsKeyValues : cmsKeyValuesList) {
                if (cmsKeyValues != null) {
                    keyValuesRepository.delete(cmsKeyValues);
                    deleted= true;
                }
            }
        } catch (Exception e) {
            logger.error(getClass().getName() + " : Delete Failed : " + e.getMessage());
            throw new  CmsRecordNotFoundException(false, cmsKeyValuesList.toString());
        }
        return deleted;
    }

    @Override
    public boolean createKeyValues(List<CmsKeyValues> cmsKeyValuesList) throws  CmsRecordNotFoundException {
        boolean savedAll = false;
        try {
            cmsKeyValuesList.stream().forEach(keyValues -> {
                if ((keyValues.getId()==null || "".equals(keyValues.getId()) && !keyValues.getKeyName().isEmpty())) {
                    keyValues.setId(sequenceGenerator.generateSequence(CmsKeyValues.SEQUENCE_NAME));
                    keyValuesRepository.save(keyValues);
                }
            });
            savedAll = true;
        } catch (Exception e) {
            logger.error(getClass().getName() + " : create Failed" + e.getMessage());
            throw new  CmsRecordNotFoundException(false, cmsKeyValuesList.toString());
        }
        return savedAll;
    }
}

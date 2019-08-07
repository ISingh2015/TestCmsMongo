package com.cms.service;

import com.cms.exception.CmsRecordNotFoundException;
import com.cms.vo.CmsKeyValues;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public interface CmsKeyValueService {

    /**
     *
     * @param userId
     * @return
     * @throws CmsRecordNotFoundException
     */
    public Collection<CmsKeyValues> getAllKeyValues(long userId) throws CmsRecordNotFoundException;

    /**
     *
     * @param id
     * @return
     * @throws  CmsRecordNotFoundException
     */
    public CmsKeyValues getKeyValuesById(long id) ;

    /**
     *
     * @param cmsKeyValuesList
     * @return
     * @throws  CmsRecordNotFoundException
     */
    public Boolean updateKeyValues(List<CmsKeyValues> cmsKeyValuesList) throws  CmsRecordNotFoundException;

    /**
     *
     * @param cmsKeyValuesList
     * @return
     * @throws  CmsRecordNotFoundException
     */
    public Boolean deleteKeyValues(List<CmsKeyValues> cmsKeyValuesList) throws  CmsRecordNotFoundException;

    /**
     *
     * @param cmsKeyValuesList
     * @return
     * @throws  CmsRecordNotFoundException
     */
    public boolean createKeyValues(List<CmsKeyValues> cmsKeyValuesList) throws  CmsRecordNotFoundException;
}

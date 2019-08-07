package com.cms.repo;

import com.cms.exception.CmsRecordNotFoundException;
import com.cms.vo.CmsKeyValues;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Collection;

public interface KeyValuesRepository extends MongoRepository<CmsKeyValues, Long> {

    @Query("{ 'userId' : ?0 }")
    public Collection<CmsKeyValues> getAllKeyValues(Long userId) throws CmsRecordNotFoundException;

}

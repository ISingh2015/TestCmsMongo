package com.cms.event;

import com.cms.service.CmsMongoSequenceGeneratorService;
import com.cms.vo.CmsCustomer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;

/**
 * @author : Inderjit SS
 * @version: v1.0.0
 * @github: https://github.com/ISingh2015/TestMongo
 * @since : 08.07.2019
 */

public class CmsCustomerEventListener extends AbstractMongoEventListener<CmsCustomer> {

    private CmsMongoSequenceGeneratorService seqenceGenerator;

    @Autowired
    public CmsCustomerEventListener(CmsMongoSequenceGeneratorService seqenceGenerator) {
        this.seqenceGenerator = seqenceGenerator;
    }

    @Override
    public void onBeforeConvert (BeforeConvertEvent<CmsCustomer> event) {
        if (event.getSource().getId() < 1) event.getSource().setId(seqenceGenerator.generateSequence(CmsCustomer.SEQUENCE_NAME));
    }
}

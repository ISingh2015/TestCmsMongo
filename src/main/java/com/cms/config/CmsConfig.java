package com.cms.config;


import com.cms.service.CmsAudioVideoImageFileService;
import com.cms.service.CmsCustomerService;
import com.cms.service.CmsKeyValueService;
import com.cms.service.impl.CmsAudioVideoImageFileServiceImpl;
import com.cms.service.impl.CmsCustomerServiceImpl;
import com.cms.service.impl.CmsKeyValuesServiceImpl;
import com.cms.service.impl.CmsUserDetailsService;
import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.data.mongodb.SessionSynchronization;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.convert.DbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Component;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * @author : Inderjit SS
 * @version: v1.0.0
 * @github: https://github.com/ISingh2015/TestMongo
 * @since : 06.06.2019
 */
@Component
@EnableMongoRepositories("com.cms.repo")
@EnableSwagger2
public class CmsConfig extends AbstractMongoConfiguration {

    @Value("${spring.data.mongodb.database}")
    private String dbName;

    @Value("${spring.data.mongodb.host}")
    private String dbHost;

    @Value("${spring.data.mongodb.port}")
    private int dbPort;

    @Override
    public String getDatabaseName() {
        return dbName;
    }

    @Bean
    public MongoClient mongoClient() {
        return new MongoClient(dbHost, dbPort);
    }

    @Bean
    public GridFsTemplate gridFsTemplate() throws Exception {
        return new GridFsTemplate(mongoDbFactory(), mappingMongoConverter());
    }

    @Bean
    public MongoTemplate mongoTemplate() {
        DbRefResolver dbRefResolver = new DefaultDbRefResolver(mongoDbFactory());
        MappingMongoConverter mappingMongoConverter = new MappingMongoConverter(dbRefResolver, new MongoMappingContext());
        // Don't save _class to mongo
        mappingMongoConverter.setTypeMapper(new DefaultMongoTypeMapper(null));
        MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory(), mappingMongoConverter);
        mongoTemplate.setSessionSynchronization(SessionSynchronization.ON_ACTUAL_TRANSACTION);
        return mongoTemplate;
    }

    public MongoTemplate fetchMongoTemplate(int projectId) {
        DbRefResolver dbRefResolver = new DefaultDbRefResolver(mongoDbFactory());
        MappingMongoConverter mappingMongoConverter = new MappingMongoConverter(dbRefResolver, new MongoMappingContext());
        // Don't save _class to mongo
        mappingMongoConverter.setTypeMapper(new DefaultMongoTypeMapper(null));
        MongoDbFactory customizedDBFactory = new SimpleMongoDbFactory(mongoClient(), dbName + "_" + projectId);
        MongoTemplate mongoTemplate = new MongoTemplate(customizedDBFactory, mappingMongoConverter);
        MongoTransactionManager mongoTransactionManager = new MongoTransactionManager(customizedDBFactory);
        return mongoTemplate;
    }

    @Bean
    public MongoTransactionManager mongoTransactionManager() {
        return new MongoTransactionManager(mongoDbFactory());
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

    @Bean
    public CmsCustomerService customerService() {
        return new CmsCustomerServiceImpl();
    }

    @Bean
    public CmsKeyValueService keyValueService() {
        return new CmsKeyValuesServiceImpl();
    }

    @Bean
    public CmsAudioVideoImageFileService audioVideoImageFileService() {return new CmsAudioVideoImageFileServiceImpl();
    }
}

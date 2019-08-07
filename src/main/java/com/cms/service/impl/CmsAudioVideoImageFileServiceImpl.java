package com.cms.service.impl;

import com.cms.service.CmsAudioVideoImageFileService;
import com.mongodb.DBObject;
import com.mongodb.client.gridfs.GridFSFindIterable;
import com.mongodb.client.gridfs.model.GridFSFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * @author : Inderjit SS
 * @version: v1.0.0
 * @github: https://github.com/ISingh2015/TestMongo
 * @since : 23.07.2019
 */

public class CmsAudioVideoImageFileServiceImpl implements CmsAudioVideoImageFileService {

    // File Audio and Video Store

    @Value("${image.files.store.path}")
    private String FILE_DIRECTORY;

    @Autowired
    private GridFsTemplate gridFsTemplate;

    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    public String store(MultipartFile file, DBObject metaData) throws IOException {
        File filesDirectory = new File(FILE_DIRECTORY);
        if (!filesDirectory.exists()) filesDirectory.mkdir();
        Path filePath = Paths.get(FILE_DIRECTORY + File.separator + file.getOriginalFilename());
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        String rtnId = gridFsTemplate.store(file.getInputStream(), file.getOriginalFilename(), file.getContentType(), metaData).toString();
        Files.deleteIfExists(filePath);
//        logger.info("File Stored at: " + filePath.toString());
        return rtnId;
    }

    @Override
    public GridFSFile retrive(String fileName) {
        return gridFsTemplate.findOne(new Query(Criteria.where("filename").is(fileName)));
    }

    @Override
    public GridFSFile getById(String id) {
        return this.gridFsTemplate.findOne(new Query(Criteria.where("_id").is(id)));
    }

    @Override
    public GridFSFile getByFileName(String fileName) {
        return gridFsTemplate.findOne(new Query(Criteria.where("filename").is(fileName)));
    }

    @Override
    public void deleteByFileName(String fileName) {
        gridFsTemplate.delete(new Query(Criteria.where("filename").is(fileName)));
    }

    @Override
    public List<GridFSFile> findAll(long userId, String fileType) {
        List<GridFSFile> filesList = new ArrayList<>();
        GridFSFindIterable gridFSFindIterable = gridFsTemplate.find(new Query(Criteria.where("_id").exists(true)));
        Iterator iterator = gridFSFindIterable.iterator();
        while (iterator.hasNext()) {
            filesList.add((GridFSFile) iterator.next());
        }
        return filesList;
    }
}

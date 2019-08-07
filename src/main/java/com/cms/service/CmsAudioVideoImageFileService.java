package com.cms.service;

import com.mongodb.DBObject;
import com.mongodb.client.gridfs.GridFSFindIterable;
import com.mongodb.client.gridfs.model.GridFSFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @author : Inderjit SS
 * @version: v1.0.0
 * @github: https://github.com/ISingh2015/TestMongo
 * @since : 23.07.2019
 */
@Service
public interface CmsAudioVideoImageFileService {

    public String store(MultipartFile file, DBObject metaData) throws IOException;

    public GridFSFile retrive(String fileName);

    public GridFSFile getById(String id);

    public GridFSFile getByFileName(String fileName);

    public void deleteByFileName(String fileName);

    public List <GridFSFile> findAll(long userId, String fileType);

}

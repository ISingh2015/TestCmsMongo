package com.cms.controller;

import com.cms.service.CmsAudioVideoImageFileService;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.gridfs.model.GridFSFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.util.List;

/**
 * @author : Inderjit SS
 * @version: v1.0.0
 * @github: https://github.com/ISingh2015/TestMongo
 * @since : 30.07.2019
 */

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class VideoController {

    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Autowired
    private CmsAudioVideoImageFileService cmsAudioVideoImageFileService;

    /**
     * @param file
     * @throws IOException
     */
    @PostMapping(value = "/demo-video/create")
    @ResponseStatus(HttpStatus.OK)
    public void handleFileUpload(@RequestParam("file") MultipartFile file) throws IOException {
        DBObject metaData = new BasicDBObject();
        metaData.put("user","CMSDemo");
        cmsAudioVideoImageFileService.store(file,metaData);
    }

    @GetMapping(value = "/demo-video/getForUser/{userId}")
    public ResponseEntity<?> handleFileGet(@PathVariable("userId") Long userId) throws IOException {
        List<GridFSFile> filesList = cmsAudioVideoImageFileService.findAll(userId, "file/video");
        return ResponseEntity.ok(filesList);
    }

    @DeleteMapping(value = "/demo-video/delete")
    @ResponseStatus(HttpStatus.OK)
    public void handleFileDelete(@RequestParam("file") MultipartFile file) throws IOException {
        //TODO fileService.deleteFile(file);
    }

}

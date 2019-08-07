package com.cms;

import com.cms.service.CmsAudioVideoImageFileService;
import com.mongodb.client.gridfs.model.GridFSFile;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.lang.invoke.MethodHandles;
import java.util.List;

/**
 * @author : Inderjit SS
 * @version: v1.0.0
 * @github: https://github.com/ISingh2015/TestMongo
 * @since : 29.07.2019
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class CmsAudioVideoImageFileServiceTest {

    @Value("${image.files.store.path}")
    private String FILE_DIRECTORY;

    @Autowired
    private CmsAudioVideoImageFileService cmsAudioVideoImageFileService;

    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Test
    public void getAllFiles () {
        List<GridFSFile> filesList = cmsAudioVideoImageFileService.findAll(500l,"file/image");
        logger.info(filesList.toString());
        Assert.assertNotNull("Get All files" + filesList.toString());
    }

    @Test
    public void getFileByName () {
        GridFSFile file = cmsAudioVideoImageFileService.getByFileName("song.mp3");
        logger.info(file.toString());
        Assert.assertNotNull(file);
    }

    @Test
    public void retriveFileByName () {
        GridFSFile file = cmsAudioVideoImageFileService.retrive("song.mp3");
        logger.info(file.toString());
        Assert.assertNotNull(file);
    }

    @Test
    public void getfileById() {
        GridFSFile file = cmsAudioVideoImageFileService.getById("5d3ee6444f24cc2314b14e06");
        logger.info(file.toString());
        Assert.assertNotNull(file);
    }
}

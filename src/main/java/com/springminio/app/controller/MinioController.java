package com.springminio.app.controller;

import com.springminio.app.exception.FileResponseException;
import com.springminio.app.payload.FileResponse;
import com.springminio.app.service.MinioService;
import com.springminio.app.util.FileTypeUtils;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/minio")
public class MinioController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MinioController.class);

    private final MinioService minioService;

    @Value("${server.port}")
    private int portNumber;

    @PostMapping("/upload")
    public FileResponse uploadFile(MultipartFile file, String bucketName) {

        LOGGER.info("MinioController | uploadFile is called");

        LOGGER.info("MinioController | uploadFile | bucketName : " + bucketName);

        String fileType = FileTypeUtils.getFileType(file);

        LOGGER.info("MinioController | uploadFile | fileType : " + fileType);

        if (fileType != null) {
            return minioService.putObject(file, bucketName, fileType);
        }
        throw new FileResponseException("File cannot be Upload");
    }

    @PostMapping("/addBucket/{bucketName}")
    public String addBucket(@PathVariable String bucketName) {

        LOGGER.info("MinioController | addBucket is called");

        LOGGER.info("MinioController | addBucket | bucketName : " + bucketName);

        minioService.makeBucket(bucketName);
        return "Bucket name "+ bucketName +" created";
    }

    @GetMapping("/show/{bucketName}")
    public List<String> show(@PathVariable String bucketName) {

        LOGGER.info("MinioController | show is called");

        LOGGER.info("MinioController | show | bucketName : " + bucketName);

        return minioService.listObjectNames(bucketName);
    }

    @GetMapping("/showBucketName")
    public List<String> showBucketName() {

        LOGGER.info("MinioController | showBucketName is called");

        return minioService.listBucketName();
    }

    @DeleteMapping("/removeBucket/{bucketName}")
    public String delBucketName(@PathVariable String bucketName) {

        LOGGER.info("MinioController | delBucketName is called");

        LOGGER.info("MinioController | delBucketName | bucketName : " + bucketName);

        boolean state =  minioService.removeBucket(bucketName);

        LOGGER.info("MinioController | delBucketName | state : " + state);

        if(state){
            return " Delete Bucket Name successfully ";
        }else{
            return " Delete failed ";
        }
    }

    @DeleteMapping("/removeObject/{bucketName}/{objectName}")
    public String delObject(@PathVariable("bucketName") String bucketName, @PathVariable("objectName") String objectName) {

        LOGGER.info("MinioController | delObject is called");

        LOGGER.info("MinioController | delObject | bucketName : " + bucketName);
        LOGGER.info("MinioController | delObject | objectName : " + objectName);

        boolean state =  minioService.removeObject(bucketName, objectName);

        LOGGER.info("MinioController | delBucketName | state : " + state);

        if(state){
            return " Delete Object successfully ";
        }else {
            return " Delete failed ";
        }
    }

    @DeleteMapping("/removeListObject/{bucketName}")
    public String delListObject(@PathVariable("bucketName") String bucketName, @RequestBody List<String> objectNameList) {

        LOGGER.info("MinioController | delListObject is called");

        LOGGER.info("MinioController | delListObject | bucketName : " + bucketName);
        LOGGER.info("MinioController | delListObject | objectNameList size : " + objectNameList.size());

        boolean state =  minioService.removeListObject(bucketName, objectNameList) ;

        LOGGER.info("MinioController | delBucketName | state : " + state);

        if(state){
            return " Delete List Object successfully ";
        }else {
            return " Delete failed ";
        }
    }

    @GetMapping("/showListObjectNameAndDownloadUrl/{bucketName}")
    public Map<String, String> showListObjectNameAndDownloadUrl(@PathVariable String bucketName) {

        LOGGER.info("MinioController | showListObjectNameAndDownloadUrl is called");

        LOGGER.info("MinioController | showListObjectNameAndDownloadUrl | bucketName : " + bucketName);

        Map<String, String> map = new HashMap<>();
        List<String> listObjectNames = minioService.listObjectNames(bucketName);

        LOGGER.info("MinioController | showListObjectNameAndDownloadUrl | listObjectNames size : " + listObjectNames.size());

        String url = "localhost:" + portNumber + "/minio/download/" + bucketName + "/";
        LOGGER.info("MinioController | showListObjectNameAndDownloadUrl | url : " + url);

        for (int i = 0; i <listObjectNames.size() ; i++) {
            map.put(listObjectNames.get(i),url+listObjectNames.get(i));
        }

        LOGGER.info("MinioController | showListObjectNameAndDownloadUrl | map : " + map.toString());

        return map;
    }

    @GetMapping("/download/{bucketName}/{objectName}")
    public void download(HttpServletResponse response, @PathVariable("bucketName") String bucketName,
                         @PathVariable("objectName") String objectName) {

        LOGGER.info("MinioController | download is called");
        LOGGER.info("MinioController | download | bucketName : " + bucketName);
        LOGGER.info("MinioController | download | objectName : " + objectName);

        InputStream in = null;
        try {
            in = minioService.downloadObject(bucketName, objectName);
            response.setHeader("Content-Disposition", "attachment;filename="
                    + URLEncoder.encode(objectName, "UTF-8"));
            response.setCharacterEncoding("UTF-8");
            // Remove bytes from InputStream Copied to the OutputStream .
            IOUtils.copy(in, response.getOutputStream());
        } catch (UnsupportedEncodingException e) {
            LOGGER.info("MinioController | download | UnsupportedEncodingException : " + e.getMessage());
        } catch (IOException e) {
            LOGGER.info("MinioController | download | IOException : " + e.getMessage());
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    LOGGER.info("MinioController | download | IOException : " + e.getMessage());
                }
            }
        }

    }
}

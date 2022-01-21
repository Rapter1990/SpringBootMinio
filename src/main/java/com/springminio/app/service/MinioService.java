package com.springminio.app.service;

import com.springminio.app.payload.FileResponse;
import io.minio.messages.Bucket;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

public interface MinioService {

    //Check Whether bucket already exists
    boolean bucketExists(String bucketName);

    // Create a bucket
    void makeBucket(String bucketName);

    // List all bucket names
    List<String> listBucketName();

    //List all buckets
    List<Bucket> listBuckets();

    // Delete Bucket by Name
    boolean removeBucket(String bucketName);

    // List all object names in the bucket
    List<String> listObjectNames(String bucketName);

    // Upload files in the bucket
    FileResponse putObject(MultipartFile multipartFile, String bucketName, String fileType);

    // Download file from bucket
    InputStream downloadObject(String bucketName, String objectName);

    // Delete file in bucket
    boolean removeObject(String bucketName, String objectName);

    // Delete files in bucket
    boolean removeListObject(String bucketName, List<String> objectNameList);

    // Get file path from bucket
    String getObjectUrl(String bucketName,String objectName);
}

package com.thecodingenthusiast.learning.gateway;


import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.DeleteObjectsRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.google.inject.Singleton;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Singleton
public class S3ServiceGateway {
    private AmazonS3 s3Client;
    private AWSCredentials credentials;
    public S3ServiceGateway() {
        AWSCredentials credentials = new BasicAWSCredentials(
                "AKIASJWYKVFKYJ6NH3QP",
                "p+JxqYHFSUefbudwpy0EzK202FfmzJbe2mURCWs7"
        );
        s3Client = AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(Regions.US_EAST_2)
                .build();
    }
    public void createBucket(final String bucketName) {
        if(s3Client.doesBucketExistV2(bucketName)) {
            log.info("Bucket name is not available."
                    + " Try again with a different Bucket name.");
            return;
        }
        s3Client.createBucket(bucketName);
    }

    public List<String> getAllBuckets() {
        List<Bucket> buckets = s3Client.listBuckets();
        List<String> bucketNames = new ArrayList<>();
        buckets.forEach(b -> bucketNames.add(b.getName()));
        return bucketNames;
    }

    public void deleteBucket(final String bucketName) {
        try {
            s3Client.deleteBucket(bucketName);
        } catch (AmazonServiceException e) {
            System.err.println(e.getErrorMessage());
        }
    }

    public List<String> listObjects(final String bucketName) {
        ObjectListing objectListing = s3Client.listObjects(bucketName);
        List<String> objectNames = new ArrayList<>();
        objectListing.getObjectSummaries().forEach(os -> objectNames.add(os.getKey()));
        return objectNames;
    }

    public void deleteObject(final String bucketName, final String objectKey) {
        s3Client.deleteObject(bucketName, objectKey);
    }

    public void deleteObjects(final String bucketName, final String[] obectKeys) {
        DeleteObjectsRequest deleteObjectRequest = new DeleteObjectsRequest(bucketName).withKeys(obectKeys);
    }

    public void uploadObjects(final String bucketName, final String key, final File file) {
        s3Client.putObject(bucketName, key, file);
    }

}

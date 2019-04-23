package com.thecodingenthusiast.learning.controller;

import com.thecodingenthusiast.learning.gateway.S3ServiceGateway;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

@Slf4j
@Path("/s3")
public class S3Controller {

    private S3ServiceGateway s3ServiceGateway;

    public S3Controller() {
        s3ServiceGateway = new S3ServiceGateway();
    }

    @GET
    @Path("/bucket/{bucketName}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<String> listObjects(@PathParam("bucketName") final String bucketName) {
        return s3ServiceGateway.listObjects(bucketName);
    }

    @POST
    @Path("/bucket/{bucketName}")
    @Produces(MediaType.APPLICATION_JSON)
    public String createBucket(@PathParam("bucketName") final String bucketName) {
        s3ServiceGateway.createBucket(bucketName);
        return "Success.";
    }

    @DELETE
    @Path("/bucket/{bucketName}")
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteBucket(@PathParam("bucketName") final String bucketName) {
        s3ServiceGateway.deleteBucket(bucketName);
        return "Success.";
    }

    @GET
    @Path("/bucket/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<String> getAllBuckets() {
        return s3ServiceGateway.getAllBuckets();
    }

    @DELETE
    @Path("/bucket/{bucketName}/{objectKey}")
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteObject(@PathParam("bucketName") final String bucketName,
                                @PathParam("objectKey") final String objectKey) {
        s3ServiceGateway.deleteObject(bucketName, objectKey);
        return "Success.";
    }

    @POST
    @Path("/bucket/{bucketName}/{objectKey}")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response uploadObject(@PathParam("bucketName") final String bucketName,
                               @PathParam("objectKey") final String objectKey,
                               @FormDataParam("file") final InputStream stream,
                               @FormDataParam("file") final FormDataContentDisposition fileDetails) {
        try {
            File tempFile = File.createTempFile(fileDetails.getFileName(), "txt");
            FileOutputStream out = new FileOutputStream(tempFile);
            int read;
            byte[] bytes = new byte[1024];
            while ((read = stream.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            out.flush();
            out.close();

            s3ServiceGateway.uploadObjects(bucketName, fileDetails.getFileName(), tempFile);
        } catch (IOException e) {log.error("Exception: ", e);}
        String output = "File successfully uploaded to S3";
        return Response.status(200).entity(output).build();
    }

}

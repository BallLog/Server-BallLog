package com.ddoddo.balllog.infra.service;

import com.amazonaws.services.s3.AmazonS3;
import com.ddoddo.balllog.global.exception.ErrorCode;
import com.ddoddo.balllog.global.exception.InternalServerException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FileService {

    private String bucket;

    private String region;

    private final AmazonS3 amazonS3;

    public void deleteFile(String imgUrl) {
        try {
            String prefix = "https://" + bucket + ".s3" + region + ".amazonaws.com/";
            String keyName = imgUrl.replace(prefix, "");
            boolean isObjectExist = amazonS3.doesObjectExist(bucket, keyName);

            if (isObjectExist) {
                amazonS3.deleteObject(bucket, keyName);
            }
        } catch (Exception e) {
            throw new InternalServerException(ErrorCode.FILE_DELETE_ERROR);
        }
    }
}

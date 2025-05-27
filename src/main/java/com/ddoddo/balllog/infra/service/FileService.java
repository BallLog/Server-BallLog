package com.ddoddo.balllog.infra.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.HeadObjectRequest;
import software.amazon.awssdk.services.s3.model.NoSuchKeyException;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.PresignedPutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.model.PutObjectPresignRequest;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileService {

    private final S3Client s3Client;
    private final S3Presigner s3Presigner;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Value("${cloud.aws.region}")
    private String region;

    public void deleteFile(String imgUrl) {
        String key = extractKeyFromUrl(imgUrl);

        try {
            // 객체 존재 여부 확인 (HEAD 요청)
            s3Client.headObject(HeadObjectRequest.builder()
                    .bucket(bucket)
                    .key(key)
                    .build());

            // 삭제
            s3Client.deleteObject(DeleteObjectRequest.builder()
                    .bucket(bucket)
                    .key(key)
                    .build());

        } catch (NoSuchKeyException e) {
            // 파일이 이미 없을 경우 로깅만
            System.out.println("파일이 존재하지 않습니다: " + imgUrl);
        } catch (Exception e) {
            throw new RuntimeException("파일 삭제 실패", e);
        }
    }

    private String extractKeyFromUrl(String url) {
        String prefix = "https://" + bucket + ".s3." + region + "amazonaws.com/";

        return url.replace(prefix, "");
    }

    public String generatePresignedPutUrl(String prefix, String filename) {
        String fullKey = buildObjectKey(prefix, filename);

        PutObjectRequest objectAclRequest = PutObjectRequest.builder()
                .bucket(bucket)
                .key(fullKey)
                .acl("public-read")
                .cacheControl("No-cache")
                .contentDisposition("attachment; filename=" + encodeFileName(filename))
                .build();

        PutObjectPresignRequest presignRequest = PutObjectPresignRequest.builder()
                .signatureDuration(Duration.ofMinutes(2))
                .putObjectRequest(objectAclRequest)
                .build();

        PresignedPutObjectRequest presignedRequest = s3Presigner.presignPutObject(presignRequest);

        return presignedRequest.url().toString();
    }

    private String buildObjectKey(String prefix, String fileName) {
        String uuid = UUID.randomUUID().toString();
        if (prefix == null || prefix.isBlank()) {
            return uuid + "-" + fileName;
        }

        return prefix + "/" + uuid + "/" + fileName;
    }

    private String encodeFileName(String fileName) {
        return URLEncoder.encode(fileName, StandardCharsets.UTF_8);
    }
}

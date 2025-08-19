package com.ddoddo.balllog.infra.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.HeadObjectRequest;
import software.amazon.awssdk.services.s3.model.NoSuchKeyException;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;
import software.amazon.awssdk.services.s3.model.ServerSideEncryption;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.PresignedPutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.model.PutObjectPresignRequest;

import java.net.URI;
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

        } catch (S3Exception e) {
            // 파일이 이미 없을 경우 로깅만
            if (e.statusCode() == 404) {
                System.out.println("파일이 존재하지 않습니다: " + imgUrl);
            } else {
                throw new RuntimeException("파일 삭제 실패", e);
            }
        }
    }

    private String extractKeyFromUrl(String url) {
        // 가상호스팅 스타일만 처리
        String host = bucket + ".s3." + region + ".amazonaws.com";
        try {
            URI uri = URI.create(url);
            if (uri.getHost() != null && uri.getHost().equals(host)) {
                String path = uri.getPath(); // "/key/like/this"
                return path.startsWith("/") ? path.substring(1) : path;
            }
        } catch (Exception ignored) {}
        throw new IllegalArgumentException("지원하지 않는 S3 URL 형식입니다: " + url);
    }

    public String generatePresignedPutUrl(String prefix, String filename) {
        String fullKey = buildObjectKey(prefix, filename);

        PutObjectRequest.Builder obr = PutObjectRequest.builder()
                .bucket(bucket)
                .key(fullKey)
                .cacheControl("no-cache")
                .contentDisposition("attachment; filename=" + encodeFileName(filename))
                .serverSideEncryption(ServerSideEncryption.AES256);

        PutObjectPresignRequest presignRequest = PutObjectPresignRequest.builder()
                .signatureDuration(Duration.ofMinutes(2))
                .putObjectRequest(obr.build())
                .build();

        return s3Presigner.presignPutObject(presignRequest).url().toString();
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

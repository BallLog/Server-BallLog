package com.ddoddo.balllog.infra.controller;

import com.ddoddo.balllog.global.response.DataResponseDto;
import com.ddoddo.balllog.infra.dto.response.UrlResponse;
import com.ddoddo.balllog.infra.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/file")
public class FileController {

    private final FileService fileService;

    @GetMapping("/presigned-url")
    public DataResponseDto<UrlResponse> getPresignedUrl(
            @RequestParam String fileName
    ) {
        String activeProfile = System.getProperty("spring.profiles.active");
        UrlResponse urlResponse = UrlResponse.from(fileService.generatePresignedPutUrl("images/" + activeProfile, fileName));

        return DataResponseDto.from(urlResponse);
    }
}

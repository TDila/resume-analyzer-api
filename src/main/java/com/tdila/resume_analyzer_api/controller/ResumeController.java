package com.tdila.resume_analyzer_api.controller;

import com.tdila.resume_analyzer_api.dto.ResumeUploadResponse;
import com.tdila.resume_analyzer_api.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/resumes")
@RequiredArgsConstructor
public class ResumeController {
    private final ResumeService resumeService;

    @PostMapping("/upload")
    public ResponseEntity<ResumeUploadResponse> uploadResume(@RequestParam("file")MultipartFile file){
        ResumeUploadResponse response = resumeService.extractTextFromResume(file);
        return ResponseEntity.ok(response);
    }
}

package com.tdila.resume_analyzer_api.controller;

import com.tdila.resume_analyzer_api.dto.ResumeAnalysisRequest;
import com.tdila.resume_analyzer_api.dto.ResumeAnalysisResult;
import com.tdila.resume_analyzer_api.dto.ResumeUploadResponse;
import com.tdila.resume_analyzer_api.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @PostMapping("/analyze")
    public ResponseEntity<ResumeAnalysisResult> analyzeResume(@RequestBody ResumeAnalysisRequest request){
        ResumeAnalysisResult result = resumeService.analyzeResumeWithAI(request.getResumeText(), request.getJobDescription());
        return ResponseEntity.ok(result);
    }
}

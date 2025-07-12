package com.tdila.resume_analyzer_api.service;

import com.tdila.resume_analyzer_api.dto.ResumeUploadResponse;
import org.springframework.web.multipart.MultipartFile;

public interface ResumeService {
    ResumeUploadResponse extractTextFromResume(MultipartFile file);
}

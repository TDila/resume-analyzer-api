package com.tdila.resume_analyzer_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResumeUploadResponse {
    private String fileName;
    private String extractedText;
}

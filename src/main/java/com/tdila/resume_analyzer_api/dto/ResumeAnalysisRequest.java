package com.tdila.resume_analyzer_api.dto;

import lombok.Data;

@Data
public class ResumeAnalysisRequest {
    private String resumeText;
    private String jobDescription;
}

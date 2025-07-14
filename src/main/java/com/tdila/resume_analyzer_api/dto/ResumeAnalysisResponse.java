package com.tdila.resume_analyzer_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResumeAnalysisResponse {
    private String skillMatch;
    private String matchingSkills;
    private String missingSkills;
    private String fitSummary;
}

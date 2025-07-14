package com.tdila.resume_analyzer_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResumeAnalysisResult {
    private int skillMatchPercentage;
    private String matchingSkills;
    private String missingSkills;
    private String summary;
}

package com.tdila.resume_analyzer_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResumeAnalysisResult {
    private int skillMatchPercentage;
    private List<String> matchingSkills;
    private List<String> missingSkills;
    private String summary;
}

package com.tdila.resume_analyzer_api.service.impl;

import com.tdila.resume_analyzer_api.ai.OpenAiServiceWrapper;
import com.tdila.resume_analyzer_api.dto.ResumeAnalysisResult;
import com.tdila.resume_analyzer_api.dto.ResumeUploadResponse;
import com.tdila.resume_analyzer_api.service.ResumeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ResumeServiceImpl implements ResumeService {
    private final OpenAiServiceWrapper openAiServiceWrapper;
    @Override
    public ResumeUploadResponse extractTextFromResume(MultipartFile file) {
        Tika tika = new Tika();
        String text;

        try {
            text = tika.parseToString(file.getInputStream());
        }catch (IOException | TikaException e){
            throw new RuntimeException("Failed to parse PDF file: "+e.getMessage());
        }

        return new ResumeUploadResponse(file.getOriginalFilename(), text);
    }

    @Override
    public ResumeAnalysisResult analyzeResumeWithAI(String resumeText, String jobDescription) {
        String aiResponse = openAiServiceWrapper.getSkillMatchAnalysis(resumeText, jobDescription);
        System.out.println(aiResponse);

        ResumeAnalysisResult result = new ResumeAnalysisResult();

        String[] lines = aiResponse.split("\n");

        List<String> matchingSkills = new ArrayList<>();
        List<String> missingSkills = new ArrayList<>();
        StringBuilder summary = new StringBuilder();

        String currentSection = "";

        for (String line : lines) {
            String lower = line.toLowerCase().trim();

            if (lower.contains("skill match percentage")) {
                result.setSkillMatchPercentage(Integer.parseInt(line.replaceAll("[^0-9]", "")));
                currentSection = "";
            } else if (lower.contains("key matching skills")) {
                currentSection = "matching";
            } else if (lower.contains("missing important skills")) {
                currentSection = "missing";
            } else if (lower.contains("overall job fit summary")) {
                currentSection = "summary";

                // ✅ Handle inline summary (after the colon on same line)
                if (line.contains(":")) {
                    String inlineSummary = line.substring(line.indexOf(":") + 1).trim();
                    if (!inlineSummary.isEmpty()) {
                        summary.append(inlineSummary).append(" ");
                    }
                }
            } else {
                // ✅ Collect section content
                switch (currentSection) {
                    case "matching":
                        if (!line.isEmpty() && line.startsWith("-")) {
                            matchingSkills.add(line.replace("-", "").trim());
                        }
                        break;
                    case "missing":
                        if (!line.isEmpty() && line.startsWith("-")) {
                            missingSkills.add(line.replace("-", "").trim());
                        }
                        break;
                    case "summary":
                        if (!line.isEmpty()) {
                            summary.append(line.trim()).append(" ");
                        }
                        break;
                }
            }
        }

        result.setMatchingSkills(matchingSkills);
        result.setMissingSkills(missingSkills);
        result.setSummary(summary.toString().trim());

        return result;
    }
}

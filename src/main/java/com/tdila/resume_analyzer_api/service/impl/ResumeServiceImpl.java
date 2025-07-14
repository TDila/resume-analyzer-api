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

        ResumeAnalysisResult result = new ResumeAnalysisResult();

        String[] lines = aiResponse.split("\n");

        for(String line : lines){
            if(line.toLowerCase().contains("skill match")){
                result.setSkillMatchPercentage(Integer.parseInt(line.replaceAll("[^0-9]", "")));
            } else if (line.toLowerCase().contains("matching skills")) {
                result.setMatchingSkills(line.substring(line.indexOf(":") + 1).trim());
            } else if (line.toLowerCase().contains("missing")) {
                result.setMissingSkills(line.substring(line.indexOf(":") + 1).trim());
            } else if (line.toLowerCase().contains("summary")) {
                result.setSummary(line.substring(line.indexOf(":") + 1).trim());
            }
        }

        return result;
    }
}

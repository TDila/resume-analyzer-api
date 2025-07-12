package com.tdila.resume_analyzer_api.service.impl;

import com.tdila.resume_analyzer_api.dto.ResumeUploadResponse;
import com.tdila.resume_analyzer_api.service.ResumeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@Slf4j
public class ResumeServiceImpl implements ResumeService {
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
}

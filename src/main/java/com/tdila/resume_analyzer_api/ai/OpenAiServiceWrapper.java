package com.tdila.resume_analyzer_api.ai;

import com.tdila.resume_analyzer_api.config.DotenvConfig;
import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.service.OpenAiService;
import org.springframework.stereotype.Component;

@Component
public class OpenAiServiceWrapper {
    private final OpenAiService openAiService;

    public OpenAiServiceWrapper(){
        String apiKey = DotenvConfig.dotenv.get("OPENAI_API_KEY");
        this.openAiService = new OpenAiService(apiKey);
    }

    public String getSkillMatchAnalysis(String resumeText, String jobDescription){
        String prompt = String.format("""
                Analyze the following resume against the job description.
                Resume: %s
                Job Description: %s

                Respond with:
                - Skill Match Percentage (0-100)
                - Key Matching Skills
                - Missing Important Skills
                - Overall Job Fit Summary
                """, resumeText, jobDescription);

        CompletionRequest request = CompletionRequest.builder()
                .model("gpt-3.5-turbo-instruct")
                .prompt(prompt)
                .temperature(0.5)
                .maxTokens(500)
                .build();

        return openAiService.createCompletion(request).getChoices().get(0).getText().trim();
    }
}

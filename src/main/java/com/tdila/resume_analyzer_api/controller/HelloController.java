package com.tdila.resume_analyzer_api.controller;

import com.tdila.resume_analyzer_api.config.DotenvConfig;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/hello")
public class HelloController {
    @GetMapping
    public String hello(){
        return DotenvConfig.dotenv.get("OPENAI_API_KEY");
    }
}

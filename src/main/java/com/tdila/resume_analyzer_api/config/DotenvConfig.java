package com.tdila.resume_analyzer_api.config;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DotenvConfig {
    public static final Dotenv dotenv = Dotenv.load();
}

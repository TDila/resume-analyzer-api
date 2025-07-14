package com.tdila.resume_analyzer_api;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ResumeAnalyzerApiApplication {

	public static void main(String[] args) {
		Dotenv.configure().ignoreIfMissing().load();
		SpringApplication.run(ResumeAnalyzerApiApplication.class, args);
	}

}

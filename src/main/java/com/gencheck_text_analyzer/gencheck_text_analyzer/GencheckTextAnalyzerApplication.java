package com.gencheck_text_analyzer.gencheck_text_analyzer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GencheckTextAnalyzerApplication {

	public static void main(String[] args) {
		System.out.println("Active profile: " + System.getProperty("spring.profiles.active"));


		SpringApplication.run(GencheckTextAnalyzerApplication.class, args);
	}

}

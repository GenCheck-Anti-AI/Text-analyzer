package com.gencheck_text_analyzer.gencheck_text_analyzer.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HuggingFaceConfig {
    @Value("${huggingface.api-url}")
    public String apiUrl;

//    @Value("${huggingface.api-key}")
//    public String apiKey;
}

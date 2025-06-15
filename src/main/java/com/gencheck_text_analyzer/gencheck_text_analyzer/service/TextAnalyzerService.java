package com.gencheck_text_analyzer.gencheck_text_analyzer.service;

import com.gencheck_text_analyzer.gencheck_text_analyzer.model.AnalysisResponse;
import org.springframework.web.multipart.MultipartFile;

public interface TextAnalyzerService {
    AnalysisResponse analyzeTextFile(MultipartFile file);
}

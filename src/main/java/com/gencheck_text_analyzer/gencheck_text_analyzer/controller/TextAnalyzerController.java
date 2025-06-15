package com.gencheck_text_analyzer.gencheck_text_analyzer.controller;

import com.gencheck_text_analyzer.gencheck_text_analyzer.model.AnalysisResponse;
import com.gencheck_text_analyzer.gencheck_text_analyzer.service.TextAnalyzerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin
@RestController
@RequestMapping("/analyze")
public class TextAnalyzerController {

    @Autowired
    private TextAnalyzerService textAnalyzerService;

    @PostMapping("/text")
    public ResponseEntity<AnalysisResponse> analyzeText(@RequestParam("file") MultipartFile file) {
        AnalysisResponse response = textAnalyzerService.analyzeTextFile(file);
        return ResponseEntity.ok(response);
    }
}

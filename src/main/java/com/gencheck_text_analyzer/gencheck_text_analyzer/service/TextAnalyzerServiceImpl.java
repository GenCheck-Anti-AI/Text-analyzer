package com.gencheck_text_analyzer.gencheck_text_analyzer.service;

import com.gencheck_text_analyzer.gencheck_text_analyzer.config.HuggingFaceConfig;
import com.gencheck_text_analyzer.gencheck_text_analyzer.model.AnalysisResponse;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
// other imports...


import java.util.HashMap;
import java.util.Map;

@Service
public class TextAnalyzerServiceImpl implements TextAnalyzerService {

    @Autowired
    private TextExtractor textExtractor;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HuggingFaceConfig config;

    public AnalysisResponse analyzeTextFile(MultipartFile file) {
        String text = textExtractor.extract(file);
        String cleanedText = preprocess(text);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, String> body = new HashMap<>();
        body.put("text", cleanedText);

        HttpEntity<Map<String, String>> request = new HttpEntity<>(body, headers);

        try {
            ResponseEntity<String> response = restTemplate.postForEntity(config.apiUrl, request, String.class);
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonArray = mapper.readTree(response.getBody());

            // Determine the label with highest score
            String topLabel = "";
            double topScore = 0.0;

            for (JsonNode node : jsonArray) {
                double score = node.get("score").asDouble();
                if (score > topScore) {
                    topScore = score;
                    topLabel = node.get("label").asText();
                }
            }

            String prediction = topLabel.toLowerCase().contains("human") ? "Human" : "AI";
            return new AnalysisResponse(prediction, Math.round(topScore * 10000.0) / 10000.0);

        } catch (Exception e) {
            e.printStackTrace();
            return new AnalysisResponse("Error", 0.0);
        }
    }

    private String preprocess(String text) {
        return text.replaceAll("[^\\x00-\\x7F]", "")
                .replaceAll("\\s+", " ")
                .trim();
    }
}

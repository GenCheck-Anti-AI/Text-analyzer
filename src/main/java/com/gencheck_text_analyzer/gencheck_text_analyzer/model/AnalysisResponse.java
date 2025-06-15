package com.gencheck_text_analyzer.gencheck_text_analyzer.model;

public class AnalysisResponse {
    private String prediction;
    private double probability;

    public AnalysisResponse() {}

    public AnalysisResponse(String prediction, double probability) {
        this.prediction = prediction;
        this.probability = probability;
    }

    public String getPrediction() {
        return prediction;
    }

    public void setPrediction(String prediction) {
        this.prediction = prediction;
    }

    public double getProbability() {
        return probability;
    }

    public void setProbability(double probability) {
        this.probability = probability;
    }
}


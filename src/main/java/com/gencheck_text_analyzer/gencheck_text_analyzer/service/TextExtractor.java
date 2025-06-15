package com.gencheck_text_analyzer.gencheck_text_analyzer.service;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
public class TextExtractor {

    public String extract(MultipartFile file) {
        try {
            String filename = file.getOriginalFilename();
            if (filename.endsWith(".txt")) {
                return new String(file.getBytes(), StandardCharsets.UTF_8);
            } else if (filename.endsWith(".pdf")) {
                PDDocument document = PDDocument.load(file.getInputStream());
                PDFTextStripper stripper = new PDFTextStripper();
                String text = stripper.getText(document);
                document.close();
                return text;
            } else {
                throw new IllegalArgumentException("Unsupported file format");
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading file", e);
        }
    }
}


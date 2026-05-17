package com.enrollment.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import java.util.Map;

@Component
public class StudentClient {

    private final RestTemplate restTemplate;

    @Value("${student.service.url}")
    private String studentServiceUrl;

    public StudentClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Map getStudentById(Long studentId) {
        try {
            String url = studentServiceUrl + "/students/" + studentId;
            return restTemplate.getForObject(url, Map.class);
        } catch (Exception e) {
            return null;
        }
    }
}
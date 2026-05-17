package com.enrollment.controller;

import com.enrollment.service.EnrollmentService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/enrollments")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @GetMapping
    public List<Map<String, Object>> getAllEnrollments() {
        return enrollmentService.getAllEnrollmentsWithStudents();
    }

    @GetMapping("/health")
    public String health() {
        return "Enrollment Service is Running!";
    }
}
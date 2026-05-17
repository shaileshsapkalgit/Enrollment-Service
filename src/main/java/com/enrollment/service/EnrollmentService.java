package com.enrollment.service;

import com.enrollment.client.StudentClient;
import com.enrollment.model.Enrollment;
import com.enrollment.repository.EnrollmentRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

@Service
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final StudentClient studentClient;

    public EnrollmentService(EnrollmentRepository enrollmentRepository,
                             StudentClient studentClient) {
        this.enrollmentRepository = enrollmentRepository;
        this.studentClient = studentClient;
    }

    public List<Map<String, Object>> getAllEnrollmentsWithStudents() {
        List<Enrollment> enrollments = enrollmentRepository.findAll();
        List<Map<String, Object>> result = new ArrayList<>();

        for (Enrollment enrollment : enrollments) {
            Map<String, Object> enrollmentData = new HashMap<>();
            enrollmentData.put("enrollmentId", enrollment.getId());
            enrollmentData.put("courseName", enrollment.getCourseName());
            enrollmentData.put("status", enrollment.getStatus());

            // student-service ला call करा!
            Map student = studentClient.getStudentById(enrollment.getStudentId());
            enrollmentData.put("student", student);

            result.add(enrollmentData);
        }
        return result;
    }
}
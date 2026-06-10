package com.example.pams.service;

import com.example.pams.model.Patient;
import com.example.pams.repository.PatientRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class PatientService {

    private final PatientRepository patientRepository;
    private final ObjectMapper objectMapper;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule());
        this.objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        this.objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    public void saveAll(List<Patient> patients) {
        patientRepository.saveAll(patients);
    }

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    /** Returns all patients sorted by age descending (oldest first). */
    public List<Patient> getPatientsSortedByAgeDescending() {
        return patientRepository.findAll().stream()
                .sorted(Comparator.comparingInt(Patient::getAge).reversed())
                .toList();
    }

    public String convertPatientsToJson(List<Patient> patients) {
        try {
            return objectMapper.writeValueAsString(patients);
        } catch (Exception e) {
            throw new RuntimeException("Failed to serialize patients to JSON", e);
        }
    }
}

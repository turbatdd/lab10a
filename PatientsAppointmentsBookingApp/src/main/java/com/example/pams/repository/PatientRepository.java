package com.example.pams.repository;

import com.example.pams.model.Patient;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PatientRepository {

    private final List<Patient> patients = new ArrayList<>();

    public void save(Patient patient) {
        patients.add(patient);
    }

    public void saveAll(List<Patient> patientList) {
        patients.addAll(patientList);
    }

    public List<Patient> findAll() {
        return new ArrayList<>(patients);
    }

    public Patient findById(int patientId) {
        return patients.stream()
                .filter(p -> p.getPatientId() == patientId)
                .findFirst()
                .orElse(null);
    }

    public void deleteById(int patientId) {
        patients.removeIf(p -> p.getPatientId() == patientId);
    }

    public int count() {
        return patients.size();
    }
}

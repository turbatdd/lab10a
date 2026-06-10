package com.example.pams;

import com.example.pams.model.Patient;
import com.example.pams.service.PatientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PAMSAppTests {

    @Autowired
    private PatientService patientService;

    @Test
    void contextLoads() {
    }

    @Test
    void patientsSortedOldestFirst() {
        List<Patient> patients = Arrays.asList(
            new Patient(1, "Young",  "Person", null, null, null, LocalDate.of(2000, 1, 1)),
            new Patient(2, "Old",    "Person", null, null, null, LocalDate.of(1950, 1, 1)),
            new Patient(3, "Middle", "Person", null, null, null, LocalDate.of(1975, 1, 1))
        );
        patientService.saveAll(patients);

        List<Patient> sorted = patientService.getPatientsSortedByAgeDescending();
        assertTrue(sorted.get(0).getAge() >= sorted.get(1).getAge());
        assertTrue(sorted.get(1).getAge() >= sorted.get(2).getAge());
    }

    @Test
    void jsonContainsAgeField() {
        Patient p = new Patient(99, "Test", "User", null, null, null, LocalDate.of(1990, 6, 1));
        String json = patientService.convertPatientsToJson(List.of(p));
        assertTrue(json.contains("\"age\""));
        assertTrue(json.contains("\"firstName\""));
        assertTrue(json.contains("\"dateOfBirth\""));
    }
}

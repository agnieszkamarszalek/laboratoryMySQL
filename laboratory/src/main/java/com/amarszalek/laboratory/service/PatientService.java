package com.amarszalek.laboratory.service;

import com.amarszalek.laboratory.model.Patient;

import java.util.List;
import java.util.Optional;

public interface PatientService {
    List<Patient> findAll();
    Optional<Patient> findById(Long id);
    Patient createPatient(Patient patient);
    void deletePatientById(Long id);
}

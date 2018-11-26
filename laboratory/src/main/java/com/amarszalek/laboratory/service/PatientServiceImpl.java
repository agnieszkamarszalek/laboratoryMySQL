package com.amarszalek.laboratory.service;

import com.amarszalek.laboratory.exception.PatientNotFoundException;
import com.amarszalek.laboratory.model.Patient;
import com.amarszalek.laboratory.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PatientServiceImpl implements PatientService {
    @Autowired
    PatientRepository patientRepository;

    @Override
    public Patient findByPesel(String pesel) throws PatientNotFoundException {
        try {
            return patientRepository.findByPesel(pesel);
        } catch (NullPointerException e) {
            throw new PatientNotFoundException("Patient not found by pesel:" + pesel );
        }
    }

    @Override
    public List<Patient> findAll() {
        return patientRepository.findAll();
    }

    @Override
    public Optional<Patient> findById(Long id) {
        return patientRepository.findById(id);
    }

    @Override
    public Patient createPatient(Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    public void deletePatientById(Long id) {
        patientRepository.deleteById(id);
    }
}

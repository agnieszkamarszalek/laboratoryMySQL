package com.amarszalek.laboratory.repository;


import com.amarszalek.laboratory.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    Patient findByPesel(String pesel);
}

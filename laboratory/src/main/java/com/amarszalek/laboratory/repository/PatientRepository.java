package com.amarszalek.laboratory.repository;


import com.amarszalek.laboratory.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    Optional<Patient> findByPesel(String pesel);
    Optional<List<Patient>> findByFirstNameAndLastName(String firstName, String lastName);


}

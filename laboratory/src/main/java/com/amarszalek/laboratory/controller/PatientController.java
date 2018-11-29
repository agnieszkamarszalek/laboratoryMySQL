package com.amarszalek.laboratory.controller;

import com.amarszalek.laboratory.exception.PatientNotFoundException;
import com.amarszalek.laboratory.exception.PeselNotUniqueException;
import com.amarszalek.laboratory.model.Patient;
import com.amarszalek.laboratory.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/patients")
@CrossOrigin(origins = "*")
public class PatientController {

    @Autowired
    private PatientRepository patientRepository;

    @GetMapping()
    public List<Patient> findAll(){
        return patientRepository.findAll();
    }

    @PostMapping()
    public void createPatient(@RequestBody Patient patient){
        try {
            patientRepository.save(patient);
        } catch (Exception e) {
            String message = e.getMessage();
            if(message.contains("pesel_UNIQUE")){
                throw new PeselNotUniqueException("Patient with pesel: " + patient.getPesel() + " already exist");
            }

        }
    }

    @GetMapping()
    @RequestMapping(params = "pesel")
    public Patient findByPesel(@RequestParam String pesel) {
        Optional<Patient> patient = patientRepository.findByPesel(pesel);
        return patient.orElseThrow(() -> new PatientNotFoundException("Patient not found by pesel: " + pesel));

    }
}

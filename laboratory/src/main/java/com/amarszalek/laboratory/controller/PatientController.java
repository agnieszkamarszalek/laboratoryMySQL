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
@RequestMapping("/patient")
@CrossOrigin(origins = "*")
public class PatientController {

    @Autowired
    private PatientRepository patientRepository;

    @GetMapping()
    public List<Patient> findAll() {
        return patientRepository.findAll();
    }

    @PostMapping()
    public Patient createPatient(@RequestBody Patient patient) {
        return savePatient(patient);
    }

    @GetMapping()
    @RequestMapping(params = "pesel")
    public Patient findByPesel(@RequestParam String pesel) {
        Optional<Patient> patient = patientRepository.findByPesel(pesel);
        return patient.orElseThrow(() -> new PatientNotFoundException("Nie znaleziono pacjenta z nr pesel: " + pesel));

    }

    @GetMapping()
    @RequestMapping(params = {"firstName", "lastName"})
    public List<Patient> findPatientByFirstNameAndLastName
            (@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {
        Optional<List<Patient>> patientsList = patientRepository.findByFirstNameAndLastName(firstName, lastName);
        return patientsList.orElseThrow(
                () -> new PatientNotFoundException("Nie znaleziono pacjenta " + firstName + " " + lastName));

    }

    @PutMapping()
    @CrossOrigin(origins = "*")
    @RequestMapping(path = "/edit", params = "pesel")
    public Patient updatePatientData(@RequestParam String pesel, @RequestBody Patient updatedPatient) {
        Optional<Patient> optionalPatient = patientRepository.findByPesel(pesel);
        Patient patient = optionalPatient.orElseThrow(
                () -> new PatientNotFoundException("Nie znaleziono pacjenta z peselem: " + pesel));
        patient.setFirstName(updatedPatient.getFirstName());
        patient.setLastName(updatedPatient.getLastName());
        patient.setGender(updatedPatient.getGender());
        patient.setPesel(updatedPatient.getPesel());
        patient.setPhoneNumber(updatedPatient.getPhoneNumber());
        return savePatient(patient);
    }

    private Patient savePatient(Patient patient) {

        try {
            Patient patient1 = patientRepository.save(patient);
            return patient1;
        } catch (Exception e) {
            String message = e.getMessage();
            if (message.contains("pesel_UNIQUE")) {
                throw new PeselNotUniqueException("Pacjent z peselem: " + patient.getPesel() + " już istnieje");
            }
            throw e;
        }
    }

}

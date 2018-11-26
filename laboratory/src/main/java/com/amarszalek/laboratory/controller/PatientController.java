package com.amarszalek.laboratory.controller;

import com.amarszalek.laboratory.exception.PatientNotFoundException;
import com.amarszalek.laboratory.model.Patient;
import com.amarszalek.laboratory.service.PatientService;
import com.amarszalek.laboratory.service.PatientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/patient")
@CrossOrigin(origins = "*")
public class PatientController {


    private PatientService patientService = new PatientServiceImpl();

    @GetMapping()
    public List<Patient> findAll(){
        return patientService.findAll();
    }

    @PostMapping()
    public Patient createPatient(@RequestBody Patient patient){
        return patientService.createPatient(patient);
    }

    @GetMapping()
    @RequestMapping(params = "pesel")
    public Patient findByPesel(@RequestParam String pesel){
        return patientService.findByPesel(pesel);
    }
}

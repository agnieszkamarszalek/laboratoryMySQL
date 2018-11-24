package com.amarszalek.laboratory.controller;

import com.amarszalek.laboratory.model.Patient;
import com.amarszalek.laboratory.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/patients")
@CrossOrigin(origins = "*")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @GetMapping()
    public List<Patient> findAll(){
        return patientService.findAll();
    }

    @PostMapping()
    public Patient createPatient(@RequestBody Patient patient){
        return patientService.createPatient(patient);
    }

}

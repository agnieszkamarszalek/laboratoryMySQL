package com.amarszalek.laboratory.controller;

import com.amarszalek.laboratory.LaboratoryApplication;
import com.amarszalek.laboratory.model.Patient;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import static com.amarszalek.laboratory.model.Gender.K;
import static org.junit.Assert.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = LaboratoryApplication.class)
@RunWith(SpringRunner.class)
public class PatientControllerTest {

    @LocalServerPort
    int localPort;

    @Autowired
    TestRestTemplate testRestTemplate;

    @Test
    public void shouldAddPatientData(){
        //given
        Patient patient = new Patient();
        patient.setFirstName("Małgorzata");
        patient.setLastName("Kwiatkowska");
        patient.setPesel("0123456");
        patient.setPhoneNumber("800888888");
        patient.setGender(K);

        //when
        Patient patientSaved = testRestTemplate.postForObject("http://localhost:" + localPort + "/patient", patient, Patient.class);

        //then
        Assert.assertEquals(patient.getFirstName(), patientSaved.getFirstName());
        Assert.assertEquals(patient.getLastName(), patientSaved.getLastName());
        Assert.assertEquals(patient.getPesel(), patientSaved.getPesel());
        Assert.assertEquals(patient.getGender(), patientSaved.getGender());
        Assert.assertEquals(patient.getPhoneNumber(), patientSaved.getPhoneNumber());
    }

}
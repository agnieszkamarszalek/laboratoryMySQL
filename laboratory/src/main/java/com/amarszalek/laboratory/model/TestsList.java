package com.amarszalek.laboratory.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class TestsList {
    @Id
    private Long id;
    @OneToMany(mappedBy = "testsList")
    private List<Test> tests;
    @Temporal(TemporalType.TIMESTAMP)
    Date creationDateTime;
    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;
}

package com.amarszalek.laboratory.model;

import javax.persistence.*;

@Entity
public class TestParameter {
    @Id
    private Long id;
    private String name;
    private int min;
    private int max;
    @ManyToOne
    @JoinColumn(name="test_id", nullable=false)
    private Test test;
    @OneToOne
    @JoinColumn(name = "result_id")
    private Result result;
}

package com.example.algorithms.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Algorithms extends BaseEntity {
    private String name;
    private String accuracy;
    private String learning_time;
    private boolean linear;
    private Integer params;
    private String notes;
    private String how_works;
    private String how_uses;

    public Algorithms(UUID id, String name, String accuracy, String learning_time, boolean linear, Integer params, String notes, String how_works, String how_uses) {
        super(id);
        this.name = name;
        this.accuracy = accuracy;
        this.learning_time = learning_time;
        this.linear = linear;
        this.params = params;
        this.notes = notes;
        this.how_works = how_works;
        this.how_uses = how_uses;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(String accuracy) {
        this.accuracy = accuracy;
    }

    public String getLearning_time() {
        return learning_time;
    }

    public void setLearning_time(String learning_time) {
        this.learning_time = learning_time;
    }

    public boolean isLinear() {
        return linear;
    }

    public void setLinear(boolean linear) {
        this.linear = linear;
    }

    public Integer getParams() {
        return params;
    }

    public void setParams(Integer params) {
        this.params = params;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getHow_works() {
        return how_works;
    }

    public void setHow_works(String how_works) {
        this.how_works = how_works;
    }

    public String getHow_uses() {
        return how_uses;
    }

    public void setHow_uses(String how_uses) {
        this.how_uses = how_uses;
    }
}

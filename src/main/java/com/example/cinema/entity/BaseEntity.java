package com.example.cinema.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseEntity {
    private Integer id;

    public int getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
}

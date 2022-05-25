package com.example.algorithms.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Favourites extends BaseEntity {
    private UUID name;

    public Favourites(UUID id, UUID name) {
        super(id);
        this.name = name;
    }

    public UUID getName() {
        return name;
    }

    public void setName(UUID name) {
        this.name = name;
    }
}
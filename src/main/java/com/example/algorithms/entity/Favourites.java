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
    private UUID us;

    public Favourites(UUID id, UUID name, UUID us) {
        super(id);
        this.name = name;
        this.us = us;
    }

    public UUID getName() {
        return name;
    }

    public void setName(UUID name) {
        this.name = name;
    }

    public UUID getUs() {
        return us;
    }

    public void setUs(UUID us) {
        this.us = us;
    }
}
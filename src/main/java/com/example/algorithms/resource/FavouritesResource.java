package com.example.algorithms.resource;

import com.example.algorithms.entity.Favourites;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FavouritesResource extends BaseResource {
    private UUID id;
    private UUID name;

    public FavouritesResource(Favourites director) {
        this.id = director.getId();
        this.name = director.getName();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getName() {
        return name;
    }

    public void setName(UUID name) {
        this.name = name;
    }

    public Favourites toEntity(){
        return new Favourites(
                this.id,
                this.name
        );
    }
}

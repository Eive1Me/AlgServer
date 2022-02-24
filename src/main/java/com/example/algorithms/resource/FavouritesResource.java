package com.example.algorithms.resource;

import com.example.algorithms.entity.Favourites;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FavouritesResource extends BaseResource {
    private Integer id;
    private String name;

    public FavouritesResource(Favourites director) {
        this.id = director.getId();
        this.name = director.getName();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Favourites toEntity(){
        return new Favourites(
                this.id,
                this.name
        );
    }
}

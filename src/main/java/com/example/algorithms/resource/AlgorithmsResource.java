package com.example.algorithms.resource;

import com.example.algorithms.entity.Algorithms;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlgorithmsResource extends BaseResource {
    private Integer id;
    private String name;

    public AlgorithmsResource(Algorithms actor) {
        this.id = actor.getId();
        this.name = actor.getName();
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

    public Algorithms toEntity(){
        return new Algorithms(
                this.id,
                this.name
        );
    }
}

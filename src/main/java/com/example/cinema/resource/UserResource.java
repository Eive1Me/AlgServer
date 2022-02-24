package com.example.cinema.resource;

import com.example.cinema.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResource {
    private Integer id;
    private String genre;

    public UserResource(User genre) {
        this.id = genre.getId();
        this.genre = genre.getGenre();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public User toEntity(){
        return new User(
                this.id,
                this.genre
        );
    }
}

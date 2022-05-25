package com.example.algorithms.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseEntity {
    private String login;
    private String password;
    private UUID favourites;
    private String role;

    public User(UUID id, String login, String password, UUID favourites, String role) {
        super(id);
        this.login = login;
        this.password = password;
        this.favourites = favourites;
        this.role = role;
    }

    public String getLogin() {
        return login;
    }

    public void setGenre(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UUID getFavourites() {
        return favourites;
    }

    public void setFavourites(UUID favourites) {
        this.favourites = favourites;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
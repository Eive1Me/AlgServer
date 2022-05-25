package com.example.algorithms.resource;

import com.example.algorithms.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResource {
    private UUID id;
    private String login;
    private String password;
    private UUID favourites;
    private String role;

    public UserResource(User user) {
        this.id = user.getId();
        this.login = user.getLogin();
        this.password = user.getPassword();
        this.favourites = user.getFavourites();
        this.role = user.getRole();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
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

    public User toEntity(){
        return new User(
                this.id,
                this.login,
                this.password,
                this.favourites,
                this.role
        );
    }
}

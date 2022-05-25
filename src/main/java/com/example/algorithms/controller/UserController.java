package com.example.algorithms.controller;

import com.example.algorithms.entity.User;
import com.example.algorithms.repository.UserRepository;
import com.example.algorithms.resource.UserResource;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@CrossOrigin
@RestController
@RequestMapping(value = "/users")
public class UserController {
    private final UserRepository genreRepository;

    public UserController(UserRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    UserResource[] getAll(@RequestParam(required = false) Integer name,
                          @RequestParam(required = false) Object expand) {
        User[] entities = name == null ?
                genreRepository.select() :
                genreRepository.selectByName(name);
        return Arrays.stream(entities)
                .map(entity -> {
                    UserResource resource = new UserResource(entity);
//                    if (expand != null)
//                        resource.setSource(new ShowResource(
//                                showRepository.select(entity.getShow()))
//                        );
                    return resource;
                })
                .toArray(UserResource[]::new);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    UserResource get(@PathVariable Integer id,
                     @RequestParam(required = false) Object expand) {
        User entity = genreRepository.select(id);
        if (entity == null) return null;
        UserResource resource = new UserResource(entity);
//        if (expand != null)
//            resource.setSource(
//                    new ShowResource(showRepository.select(entity.getShow()))
//            );
        return resource;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    UserResource post(@RequestBody UserResource resource) {
        User entity = genreRepository.insert(resource.toEntity());
        if (entity == null) return null;
        resource = new UserResource(entity);
        return resource;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    UserResource put(@PathVariable Integer id,
                     @RequestBody UserResource resource) {
        User entity = genreRepository.update(id, resource.toEntity());
        if (entity == null) return null;
        resource = new UserResource(entity);
        return resource;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    UserResource delete(@PathVariable Integer id) {
        User entity = genreRepository.delete(id);
        if (entity == null) return null;
        return new UserResource(entity);
    }
}

package com.example.algorithms.controller;

import com.example.algorithms.entity.Favourites;
import com.example.algorithms.repository.FavouritesRepository;
import com.example.algorithms.resource.FavouritesResource;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@CrossOrigin
@RestController
@RequestMapping(value = "/director")
public class FavouritesController {
    private final FavouritesRepository directorRepository;

    public FavouritesController(FavouritesRepository directorRepository) {
        this.directorRepository = directorRepository;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    FavouritesResource[] getAll(@RequestParam(required = false) Integer name,
                                @RequestParam(required = false) Object expand) {
        Favourites[] entities = name == null ?
                directorRepository.select() :
                directorRepository.selectByName(name);
        return Arrays.stream(entities)
                .map(entity -> {
                    FavouritesResource resource = new FavouritesResource(entity);
//                    if (expand != null)
//                        resource.setSource(new ShowResource(
//                                showRepository.select(entity.getShow()))
//                        );
                    return resource;
                })
                .toArray(FavouritesResource[]::new);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    FavouritesResource get(@PathVariable Integer id,
                           @RequestParam(required = false) Object expand) {
        Favourites entity = directorRepository.select(id);
        if (entity == null) return null;
        FavouritesResource resource = new FavouritesResource(entity);
//        if (expand != null)
//            resource.setSource(
//                    new ShowResource(showRepository.select(entity.getShow()))
//            );
        return resource;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    FavouritesResource post(@RequestBody FavouritesResource resource) {
        Favourites entity = directorRepository.insert(resource.toEntity());
        if (entity == null) return null;
        resource = new FavouritesResource(entity);
        return resource;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    FavouritesResource put(@PathVariable Integer id,
                           @RequestBody FavouritesResource resource) {
        Favourites entity = directorRepository.update(id, resource.toEntity());
        if (entity == null) return null;
        resource = new FavouritesResource(entity);
        return resource;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    FavouritesResource delete(@PathVariable Integer id) {
        Favourites entity = directorRepository.delete(id);
        if (entity == null) return null;
        return new FavouritesResource(entity);
    }
}

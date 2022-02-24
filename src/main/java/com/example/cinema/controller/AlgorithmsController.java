package com.example.cinema.controller;

import com.example.cinema.entity.Algorithms;
import com.example.cinema.repository.AlgorithmsRepository;
import com.example.cinema.resource.AlgorithmsResource;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@CrossOrigin
@RestController
@RequestMapping(value = "/actor")
public class AlgorithmsController {
    private final AlgorithmsRepository actorRepository;

    public AlgorithmsController(AlgorithmsRepository actorRepository) {
        this.actorRepository = actorRepository;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    AlgorithmsResource[] getAll(@RequestParam(required = false) Integer name,
                                @RequestParam(required = false) Object expand) {
        Algorithms[] entities = name == null ?
                actorRepository.select() :
                actorRepository.selectByName(name);
        return Arrays.stream(entities)
                .map(entity -> {
                    AlgorithmsResource resource = new AlgorithmsResource(entity);
//                    if (expand != null)
//                        resource.setSource(new ShowResource(
//                                showRepository.select(entity.getShow()))
//                        );
                    return resource;
                })
                .toArray(AlgorithmsResource[]::new);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    AlgorithmsResource get(@PathVariable Integer id,
                           @RequestParam(required = false) Object expand) {
        Algorithms entity = actorRepository.select(id);
        if (entity == null) return null;
        AlgorithmsResource resource = new AlgorithmsResource(entity);
//        if (expand != null)
//            resource.setSource(
//                    new ShowResource(showRepository.select(entity.getShow()))
//            );
        return resource;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    AlgorithmsResource post(@RequestBody AlgorithmsResource resource) {
        Algorithms entity = actorRepository.insert(resource.toEntity());
        if (entity == null) return null;
        resource = new AlgorithmsResource(entity);
        return resource;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    AlgorithmsResource put(@PathVariable Integer id,
                           @RequestBody AlgorithmsResource resource) {
        Algorithms entity = actorRepository.update(id, resource.toEntity());
        if (entity == null) return null;
        resource = new AlgorithmsResource(entity);
        return resource;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    AlgorithmsResource delete(@PathVariable Integer id) {
        Algorithms entity = actorRepository.delete(id);
        if (entity == null) return null;
        return new AlgorithmsResource(entity);
    }
}
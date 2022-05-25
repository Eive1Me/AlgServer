package com.example.algorithms.controller;

import com.example.algorithms.entity.Algorithms;
import com.example.algorithms.repository.AlgorithmsRepository;
import com.example.algorithms.resource.AlgorithmsResource;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@CrossOrigin
@RestController
@RequestMapping(value = "/algorithms")
public class AlgorithmsController {
    private final AlgorithmsRepository algorithmsRepository;

    public AlgorithmsController(AlgorithmsRepository actorRepository) {
        this.algorithmsRepository = actorRepository;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    AlgorithmsResource[] getAll(@RequestParam(required = false) Integer name,
                                @RequestParam(required = false) Object expand) {
        Algorithms[] entities = name == null ?
                algorithmsRepository.select() :
                algorithmsRepository.selectByName(name);
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
        Algorithms entity = algorithmsRepository.select(id);
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
        Algorithms entity = algorithmsRepository.insert(resource.toEntity());
        if (entity == null) return null;
        resource = new AlgorithmsResource(entity);
        return resource;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    AlgorithmsResource put(@PathVariable Integer id,
                           @RequestBody AlgorithmsResource resource) {
        Algorithms entity = algorithmsRepository.update(id, resource.toEntity());
        if (entity == null) return null;
        resource = new AlgorithmsResource(entity);
        return resource;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    AlgorithmsResource delete(@PathVariable Integer id) {
        Algorithms entity = algorithmsRepository.delete(id);
        if (entity == null) return null;
        return new AlgorithmsResource(entity);
    }
}
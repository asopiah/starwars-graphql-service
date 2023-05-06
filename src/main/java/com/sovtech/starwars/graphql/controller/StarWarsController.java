package com.sovtech.starwars.graphql.controller;

import com.sovtech.starwars.graphql.data.model.api.response.PersonResponse;
import com.sovtech.starwars.graphql.service.StarWarsService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author asopia
 */
@Controller
public class StarWarsController {
    private final StarWarsService service;

    public StarWarsController(StarWarsService service) {
        this.service = service;
    }

    @QueryMapping
    public Flux<PersonResponse> getAllPeople(@Argument int  page) {
        return service.getAllPeople(page);
    }

    @QueryMapping
    public Flux<PersonResponse> searchPerson(@Argument String name){
        return service.searchPerson(name);
    }

    @QueryMapping
    public Mono<PersonResponse> getPerson(@Argument String name){
        return service.getPerson(name);
    }
}

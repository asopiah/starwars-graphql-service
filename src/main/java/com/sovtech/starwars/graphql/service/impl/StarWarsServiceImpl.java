package com.sovtech.starwars.graphql.service.impl;

import com.sovtech.starwars.graphql.data.model.api.response.PersonResponse;
import com.sovtech.starwars.graphql.service.StarWarsService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author asopia
 */
@Service
public class StarWarsServiceImpl implements StarWarsService {

    @Override
    public Flux<PersonResponse> getAllPeople(int page) {
        return null;
    }

    @Override
    public Flux<PersonResponse> searchPerson(String name) {
       return null;
    }

    @Override
    public Mono<PersonResponse> getPerson(String name) {
        return null;
    }

}

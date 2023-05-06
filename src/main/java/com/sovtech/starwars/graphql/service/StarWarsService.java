package com.sovtech.starwars.graphql.service;

import com.sovtech.starwars.graphql.data.model.api.response.PersonResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author asopia
 */
public interface StarWarsService {
    Flux<PersonResponse> getAllPeople(int page);

    Flux<PersonResponse> searchPerson(String name);

    Mono<PersonResponse> getPerson(String name);
}

package com.sovtech.starwars.graphql.service.impl;

import com.sovtech.starwars.graphql.data.model.api.response.PeopleResponse;
import com.sovtech.starwars.graphql.data.model.api.response.PersonResponse;
import com.sovtech.starwars.graphql.service.StarWarsService;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author asopia
 */
@Service
public class StarWarsServiceImpl implements StarWarsService {
    private static final String BASE_URL = "https://swapi.dev/api";

    private final WebClient webClient;

    public StarWarsServiceImpl(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(BASE_URL).build();
    }

    @Override
    public Flux<PersonResponse> getAllPeople(int page) {
        return webClient.get()
                .uri("/people/?page={page}", page)
                .retrieve()
                .bodyToMono(PeopleResponse.class)
                .flatMapIterable(PeopleResponse::getResults)
                .map(PeopleResponse::mapToPerson);    }

    @Override
    public Flux<PersonResponse> searchPerson(String name) {
       return null;
    }

    @Override
    public Mono<PersonResponse> getPerson(String name) {
        return null;
    }

}

package com.sovtech.starwars.graphql.service.impl;

import com.sovtech.starwars.graphql.config.LoggingFilter;
import com.sovtech.starwars.graphql.data.model.api.response.PeopleResponse;
import com.sovtech.starwars.graphql.data.model.api.response.PersonResponse;
import com.sovtech.starwars.graphql.service.StarWarsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author asopia
 */
@Service
@Slf4j
public class StarWarsServiceImpl implements StarWarsService {
    private static final String BASE_URL = "https://swapi.dev/api";

    private final WebClient webClient;

    public StarWarsServiceImpl(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(BASE_URL)
                .filter(new LoggingFilter()) // Add the logResponse() filter here
                .build();
    }

    @Override
    public Flux<PersonResponse> getAllPeople(int page) {
        log.info("Request received to retrieve all people in page={}", page);
        return webClient.get()
                .uri("/people/?page={page}", page)
                .retrieve()
                .bodyToMono(PeopleResponse.class)
                .doOnNext(response -> log.info("Retrieved all people in page={} | previousPage={} | nextPage={}", page, response.getPrevious(),response.getNext()))
                .flatMapIterable(PeopleResponse::getResults)
                .map(PeopleResponse::mapToPerson);
    }

    @Override
    public Flux<PersonResponse> searchPerson(String name) {
        log.info("Request received to search all people with name like={}", name);
        return webClient.get()
                .uri("/people/?search={name}", name)
                .retrieve()
                .bodyToMono(PeopleResponse.class)
                .doOnNext(response -> log.info("Retrieved all persons with name like={}", name))
                .flatMapIterable(PeopleResponse::getResults)
                .map(PeopleResponse::mapToPerson);
    }

    @Override
    public Mono<PersonResponse> getPerson(String name) {
        log.info("Request received to retrieve a person with name={}", name);
        return webClient.get()
                .uri("/people/?search={name}", name)
                .retrieve()
                .bodyToMono(PeopleResponse.class)
                .doOnNext(response -> log.info("Retrieved a person named={}", name))
                .flatMapIterable(PeopleResponse::getResults)
                .next()
                .map(PeopleResponse::mapToPerson);
    }

}

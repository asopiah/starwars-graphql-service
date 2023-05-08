package com.sovtech.starwars.graphql.service.impl;

import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import com.sovtech.starwars.graphql.config.Properties;
import com.sovtech.starwars.graphql.data.model.api.response.PersonResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author asopia
 */
@ExtendWith(MockitoExtension.class)
@WireMockTest
class StarWarsServiceImplTest {
    private final WebClient webClient = WebClient.create();
    @Mock
    private Properties properties;
    @InjectMocks
    private StarWarsServiceImpl starWarsService;


    @BeforeEach
    void setUp() {
        starWarsService = new StarWarsServiceImpl(webClient, properties);
    }

    @Test
    void test_getAllPeople(WireMockRuntimeInfo wireMockRuntimeInfo) {
        // arrange
        int page = 1;
        //stubs for create customer
        stubFor(get("/api/people/?page=" + page)
                        .willReturn(aResponse()
                                            .withHeader("Content-Type", String.valueOf(MediaType.APPLICATION_JSON))
                                            .withBody(responseBody()).withStatus(200)));

        Mockito.when(properties.getBaseUrl()).thenReturn(wireMockRuntimeInfo.getHttpBaseUrl() + "/api");

        //Act
        Flux<PersonResponse> allPeople = starWarsService.getAllPeople(page);


        //Assert --- verify

        StepVerifier.create(allPeople).consumeNextWith(personResponse -> {
            assertEquals("Sly Moore", personResponse.getName());
        }).verifyComplete();

    }

    @Test
    void test_searchPerson(WireMockRuntimeInfo wireMockRuntimeInfo) {
        // arrange
        var name = "Sly";
        //stubs for create customer
        stubFor(get("/api/people/?search=" + name)
                        .willReturn(aResponse()
                                            .withHeader("Content-Type", String.valueOf(MediaType.APPLICATION_JSON))
                                            .withBody(responseBody()).withStatus(200)));

        Mockito.when(properties.getBaseUrl()).thenReturn(wireMockRuntimeInfo.getHttpBaseUrl() + "/api");

        //Act
        Flux<PersonResponse> allPeople = starWarsService.searchPerson(name);


        //Assert --- verify

        StepVerifier.create(allPeople).consumeNextWith(personResponse -> {
            assertEquals("Sly Moore", personResponse.getName());
        }).verifyComplete();
    }

    @Test
    void test_getPerson(WireMockRuntimeInfo wireMockRuntimeInfo) {
        // arrange
        var name = "Sly";
        //stubs for create customer
        stubFor(get("/api/people/?search=" + name)
                        .willReturn(aResponse()
                                            .withHeader("Content-Type", String.valueOf(MediaType.APPLICATION_JSON))
                                            .withBody(responseBody()).withStatus(200)));

        Mockito.when(properties.getBaseUrl()).thenReturn(wireMockRuntimeInfo.getHttpBaseUrl() + "/api");

        //Act
        Mono<PersonResponse> allPeople = starWarsService.getPerson(name);


        //Assert --- verify

        StepVerifier.create(allPeople).consumeNextWith(personResponse -> {
            assertEquals("Sly Moore", personResponse.getName());
        }).verifyComplete();
    }

    private static String responseBody() {
        return """
                {
                    "count": 82,
                    "next": "https://swapi.dev/api/people/?page=2",
                    "previous": null,
                    "results": [
                        {
                            "name": "Sly Moore",
                            "height": "178",
                            "mass": "48",
                            "hair_color": "none",
                            "skin_color": "pale",
                            "eye_color": "white",
                            "birth_year": "unknown",
                            "gender": "female",
                            "homeworld": "https://swapi.dev/api/planets/60/",
                            "films": [
                                "https://swapi.dev/api/films/5/",
                                "https://swapi.dev/api/films/6/"
                            ],
                            "species": [],
                            "vehicles": [],
                            "starships": [],
                            "created": "2014-12-20T20:18:37.619000Z",
                            "edited": "2014-12-20T21:17:50.496000Z",
                            "url": "https://swapi.dev/api/people/82/"
                        }
                    ]
                }
                """;
    }
}
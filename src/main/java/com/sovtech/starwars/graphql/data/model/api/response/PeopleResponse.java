package com.sovtech.starwars.graphql.data.model.api.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author asopia
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PeopleResponse {
    private int count;
    private String next;
    private String previous;
    private List<PersonResponse> results;

    public static PersonResponse mapToPerson(PersonResponse personResult) {
        return PersonResponse.builder()
                .name(personResult.getName())
                .height(personResult.getHeight())
                .mass(personResult.getMass())
                .gender(personResult.getGender())
                .homeworld(personResult.getHomeworld())
                .build();
    }
}

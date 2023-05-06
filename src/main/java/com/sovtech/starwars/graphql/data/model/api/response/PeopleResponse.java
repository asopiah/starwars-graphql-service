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
}

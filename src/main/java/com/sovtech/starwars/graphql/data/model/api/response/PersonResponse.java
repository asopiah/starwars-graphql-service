package com.sovtech.starwars.graphql.data.model.api.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author asopia
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonResponse {
    private String name;
    private String height;
    private String mass;
    private String gender;
    private String homeworld;
}

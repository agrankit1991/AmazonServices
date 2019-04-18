package com.thecodingenthusiast.learning.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@ToString
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDetail {
    private String firstName;
    private String lastName;
}

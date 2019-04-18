package com.thecodingenthusiast.learning.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@ToString
@Getter
@Setter
public class UserDetail {
    private String firstName;
    private String lastName;
}

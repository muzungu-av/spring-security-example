package ru.example.restgatewayapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class User {

    private String login;

    private String password;

    private String firstname;

    private String lastname;

    private Integer age;

}

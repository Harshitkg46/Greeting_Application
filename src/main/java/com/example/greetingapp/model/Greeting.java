package com.example.greetingapp.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Greeting {
    private String message;

    public Greeting() {}

    public Greeting(String message) {
        this.message = message;
    }

}
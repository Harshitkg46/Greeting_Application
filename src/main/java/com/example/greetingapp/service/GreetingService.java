package com.example.greetingapp.service;


import com.example.greetingapp.model.Greeting;
import org.springframework.stereotype.Service;

@Service
public class GreetingService {

    public Greeting getGreeting() {
        return new Greeting("Hello World");
    }

    public Greeting postGreeting(Greeting greeting) {
        return new Greeting("Received POST request with message: " + greeting.getMessage());
    }

    public Greeting putGreeting(Greeting greeting) {
        return new Greeting("Received PUT request with message: " + greeting.getMessage());
    }
}
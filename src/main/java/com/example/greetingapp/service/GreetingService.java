package com.example.greetingapp.service;

import com.example.greetingapp.model.Greeting;
import org.springframework.stereotype.Service;

@Service
public class GreetingService {

    public Greeting getGreeting(String firstName, String lastName) {
        if ((firstName == null || firstName.isBlank()) && (lastName == null || lastName.isBlank())) {
            return new Greeting("Hello World");
        }

        String fullName = (firstName != null ? firstName : "") +
                (lastName != null ? " " + lastName : "");

        return new Greeting("Hello " + fullName.trim());
    }
}
package com.example.greetingapp.service;

import com.example.greetingapp.model.Greeting;
import com.example.greetingapp.repository.GreetingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GreetingService {

    private final GreetingRepository greetingRepository;

    public GreetingService(GreetingRepository greetingRepository) {
        this.greetingRepository = greetingRepository;
    }

    public Greeting getGreeting(String firstName, String lastName) {
        String message;
        if ((firstName == null || firstName.isBlank()) && (lastName == null || lastName.isBlank())) {
            message = "Namaste User!!";
        } else {
            String fullName = (firstName != null ? firstName : "") +
                    (lastName != null ? " " + lastName : "");
            message = "Namaste " + fullName.trim();
        }

        Greeting greeting = new Greeting(message);
        return greetingRepository.save(greeting);
    }

    public Greeting getGreetingById(Long id) {
        Optional<Greeting> greeting = greetingRepository.findById(id);
        return greeting.orElseThrow(() -> new RuntimeException("No Greeting found with id: " + id));
    }

    public Greeting saveGreeting(Greeting greeting) {
        return greetingRepository.save(greeting);
    }

    public List<Greeting> getAllGreetings() {
        return greetingRepository.findAll();
    }

    public Greeting updateGreeting(Long id, Greeting newGreeting) {
        return greetingRepository.findById(id).map(existingGreeting -> {
            existingGreeting.setMessage(newGreeting.getMessage());
            return greetingRepository.save(existingGreeting);
        }).orElseThrow(() -> new RuntimeException("No Greeting found with id: " + id));
    }

    public void deleteGreeting(Long id) {
        if (!greetingRepository.existsById(id)) {
            throw new RuntimeException("No Greeting found with id: " + id);
        }
        greetingRepository.deleteById(id);
    }
}
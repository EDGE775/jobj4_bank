package ru.job4j.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import ru.job4j.bank.model.Client;
import ru.job4j.bank.model.Passport;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BankController {
    @Autowired
    private RestTemplate rest;

    private static final String API = "http://localhost:8080/%s";

    @PostMapping("/create")
    public ResponseEntity<Client> createClient(@RequestBody Passport passport) {
        Passport rsl = rest.postForObject(String.format(API, "save"), passport, Passport.class);
        return new ResponseEntity<>(
                new Client(0, rsl),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/all")
    public List<Client> findAllClients() {
        List<Client> rsl = new ArrayList<>();
        List<Passport> passports = rest.exchange(
                String.format(API, "find"),
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Passport>>() {
                }).getBody();
        int id = 0;
        for (Passport passport : passports) {
            Client client = new Client(id++, passport);
            rsl.add(client);
        }
        return rsl;
    }
}

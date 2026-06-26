package com.example.Client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
public class ClientController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/getBlog/{id}")
    public String getBlog(@PathVariable Long id) {

        String url = "http://localhost:8081/api/blogs/" + id;
        return restTemplate.getForObject(url, String.class);
    }
}

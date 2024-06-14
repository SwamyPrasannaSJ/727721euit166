package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.model.NumberResponse;

import java.util.*;

@Service
public class NumberService {

    private static final int WINDOW_SIZE = 10;
    private final Map<String, Deque<Integer>> numberWindow = new HashMap<>();

    @Autowired
    private RestTemplate restTemplate;

    public NumberResponse processRequest(String numberid) {
        List<Integer> newNumbers = fetchNumbersFromTestServer(numberid);
        List<Integer> windowPrevState = new ArrayList<>(numberWindow.getOrDefault(numberid, new LinkedList<>()));
        updateWindow(numberid, newNumbers);
        List<Integer> windowCurrState = new ArrayList<>(numberWindow.get(numberid));
        double average = windowCurrState.stream().mapToInt(Integer::intValue).average().orElse(0);

        return new NumberResponse(newNumbers, windowPrevState, windowCurrState, average);
    }

    private List<Integer> fetchNumbersFromTestServer(String numberid) {
        String url = null;

        switch (numberid) {
            case "p":
                url = "http://20.244.56.144/test/primes";
                break;
            case "f":
                url = "http://20.244.56.144/test/fibo";
                break;
            case "e":
                url = "http://20.244.56.144/test/even";
                break;
            case "r":
                url = "http://20.244.56.144/test/rand";
                break;
            default:
                throw new IllegalArgumentException("Invalid number ID");
        }

        try {
            ResponseEntity<Map<String, List<Integer>>> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<Map<String, List<Integer>>>() {}
            );

            if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                return response.getBody().get("numbers");
            } else {
                return new ArrayList<>();
            }
        } catch (Exception e) {
            // Log the error and return an empty list
            System.err.println("Error fetching numbers: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    private void updateWindow(String numberid, List<Integer> newNumbers) {
        Deque<Integer> window = numberWindow.getOrDefault(numberid, new LinkedList<>());

        for (int number : newNumbers) {
            if (!window.contains(number)) {
                if (window.size() >= WINDOW_SIZE) {
                    window.poll();
                }
                window.add(number);
            }
        }

        numberWindow.put(numberid, window);
    }
}

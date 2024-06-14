package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.NumberResponse;
import com.example.demo.service.NumberService;

@RestController
@RequestMapping("/numbers")
public class NumberController {

    @Autowired
    private NumberService numberService;

    @GetMapping("/{numberid}")
    public ResponseEntity<NumberResponse> getNumbers(@PathVariable String numberid) {
        NumberResponse response = numberService.processRequest(numberid);
        return ResponseEntity.ok(response);
    }
}

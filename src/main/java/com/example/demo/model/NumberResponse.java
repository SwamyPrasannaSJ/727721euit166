package com.example.demo.model;

import java.util.List;

public class NumberResponse {
    private List<Integer> numbers;
    private List<Integer> windowPrevState;
    private List<Integer> windowCurrState;
    private double avg;

    // Getters and Setters
    public List<Integer> getNumbers() {
        return numbers;
    }

    public void setNumbers(List<Integer> numbers) {
        this.numbers = numbers;
    }

    public List<Integer> getWindowPrevState() {
        return windowPrevState;
    }

    public void setWindowPrevState(List<Integer> windowPrevState) {
        this.windowPrevState = windowPrevState;
    }

    public List<Integer> getWindowCurrState() {
        return windowCurrState;
    }

    public void setWindowCurrState(List<Integer> windowCurrState) {
        this.windowCurrState = windowCurrState;
    }

    public double getAvg() {
        return avg;
    }

    public void setAvg(double avg) {
        this.avg = avg;
    }
    
    public NumberResponse(List<Integer> numbers, List<Integer> windowPrevState, List<Integer> windowCurrState, double avg) {
        this.numbers = numbers;
        this.windowPrevState = windowPrevState;
        this.windowCurrState = windowCurrState;
        this.avg = avg;
    }
}

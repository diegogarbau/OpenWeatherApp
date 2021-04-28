package com.example.weatherApp.exceptions;

public class JsonErrorMapping extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public JsonErrorMapping(String message) {
        super(message);
        // TODO Auto-generated constructor stub
    }
}

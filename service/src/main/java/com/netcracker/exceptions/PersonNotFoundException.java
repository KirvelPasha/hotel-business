package com.netcracker.exceptions;

public class PersonNotFoundException extends RuntimeException {
    public PersonNotFoundException(String s) {
        super(s);
    }
}

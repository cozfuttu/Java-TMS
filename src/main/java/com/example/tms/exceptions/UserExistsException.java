package com.example.tms.exceptions;

public class UserExistsException extends Exception {
    public UserExistsException() {
        super("Kullanici zaten mevcut.");
    }

    @Override
    public String toString() {
        return "Kullanici zaten mevcut.";
    }
}

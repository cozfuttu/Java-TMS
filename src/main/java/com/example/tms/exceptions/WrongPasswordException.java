package com.example.tms.exceptions;

public class WrongPasswordException extends Exception {
    public WrongPasswordException() {
        super("Hatali Sifre.");
    }

    @Override
    public String toString() {
        return "Hatali Sifre.";
    }
}

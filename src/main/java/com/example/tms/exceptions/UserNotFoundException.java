package com.example.tms.exceptions;

public class UserNotFoundException extends Exception {
    public UserNotFoundException() {
        super("Kullanici Bulunamadi. Lutfen TCKN'yi kontrol ediniz.");
    }

    @Override
    public String toString() {
        return "Kullanici Bulunamadi. Lutfen TCKN'yi kontrol ediniz.";
    }
}

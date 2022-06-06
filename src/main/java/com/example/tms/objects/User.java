package com.example.tms.objects;

import com.example.tms.Stack;

interface UserInterface {
    public void setTCKN(String email);

    public void setSifre(String sifre);

    public String getTCKN();

    public String getSifre();
}

public class User implements UserInterface {
    private String TCKN;
    private String sifre;
    private int odenmemisVergiMiktari;
    private int odenmisVergiMiktari;

    public User() {
        setTCKN("11111111111");
        setSifre("111111");
        setOdenmemisVergiMiktari(999);
        setOdenmisVergiMiktari(999);
    }

    public User(String TCKN, String sifre, int odenmemisVergiMiktari, int odenmisVergiMiktari) {
        setTCKN(TCKN);
        setSifre(sifre);
        setOdenmemisVergiMiktari(odenmemisVergiMiktari);
        setOdenmisVergiMiktari(odenmisVergiMiktari);
    }

    public void setTCKN(String TCKN) {
        this.TCKN = TCKN;
    }

    public void setSifre(String sifre) {
        this.sifre = sifre;
    }

    public void setOdenmemisVergiMiktari(int odenmemisVergiMiktari) {
        this.odenmemisVergiMiktari = odenmemisVergiMiktari;
    }

    public void setOdenmisVergiMiktari(int odenmisVergiMiktari) {
        this.odenmisVergiMiktari = odenmisVergiMiktari;
    }

    public String getTCKN() {
        return TCKN;
    }

    public String getSifre() {
        return sifre;
    }

    public int getOdenmemisVergiMiktari() {
        return odenmemisVergiMiktari;
    }

    public int getOdenmisVergiMiktari() {
        return odenmisVergiMiktari;
    }

    public boolean palindromMu() {
        int count = TCKN.length();
        Stack<Character> stack = new Stack<>(count);

        for (int i = 0; i < TCKN.length(); i++) {
            stack.push(TCKN.charAt(i));
        }

        String reverseInput = "";

        while (!stack.bos_mu()) {
            reverseInput += stack.pop();
        }

        if (TCKN.equals(reverseInput))
            return true;
        else
            return false;
    }
}

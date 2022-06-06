package com.example.tms;

public class Stack <T> {
    private int boyut;
    private Object[] dizi;
    private int top;

    public Stack(int boyut){
        this.boyut=boyut;
        dizi=new Object[boyut];
        this.top=-1; //boş stack için -1
    }

    public void push(Object item){ //eleman ekleme
        if(dolu_mu()){
            System.out.println("Stack dolu, eleman eklenemez");
        }
        else{
            top=top+1;
            dizi[top]=item;
        }
    }
    public boolean dolu_mu(){ //dolu mu kontrol
        if(top==boyut-1){
            return true;
        }
        return false;
    }
    public T pop(){ //eleman çıkarma
        if(bos_mu()){
            System.out.println("Stack Boş");
        }
        T eleman = (T)dizi[top];
        dizi[top]=null;
        top=top-1;
        return eleman;
    }

    public T peek(){ //eleman okuma
        if(bos_mu()){ //eleman okumadan önce varlığı kontrol edilir
            System.out.println("Stack Boş, okunacak eleman yok");
            return null;
        }
        T eleman = (T)dizi[top];
        return eleman;
    }

    public boolean bos_mu(){ //boş mu olup olmadığı kontrol edilir
        if(top==-1){
            return true;
        }
        return false;
    }
}

package com.faktury;

public class Client {
    String buyerName;
    Long buyerNip;
    String buyerAdress;

    public Client(String buyerName, Long buyerNip2, String buyerAdress) {
        this.buyerName = buyerName;
        this.buyerNip = buyerNip2;
        this.buyerAdress = buyerAdress;
    }

    @Override
    public String toString() {
        return "Name: " + this.buyerName + " NIP: " + this.buyerNip + " Adress: " + this.buyerAdress;
    }
}

package com.faktury;

public class Client {
    String buyerName;
    Integer buyerNip;
    String buyerAdress;

    public Client(String buyerName, Integer buyerNip, String buyerAdress) {
        this.buyerName = buyerName;
        this.buyerNip = buyerNip;
        this.buyerAdress = buyerAdress;
    }

    @Override
    public String toString() {
        return "Name: " + this.buyerName + " NIP: " + this.buyerNip + " Adress: " + this.buyerAdress;
    }
}

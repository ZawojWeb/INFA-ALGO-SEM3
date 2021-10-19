package com.lab2;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int pierwsza;
        int druga;
        char znak;
        Scanner scan = new Scanner(System.in);
        System.out.println("Podaj pierwszą liczbę:");
        pierwsza = scan.nextInt();
        System.out.println("Podaj drugą liczbę:");
        druga = scan.nextInt();
        znak = scan.next().charAt(0);
        switch(znak) {
            case '+': {
                Add.addN(pierwsza, druga);
                break;
            }
            case '-': {
                Substract.substractN(pierwsza, druga);
                break;
            }
        }
        scan.close();
    }
}

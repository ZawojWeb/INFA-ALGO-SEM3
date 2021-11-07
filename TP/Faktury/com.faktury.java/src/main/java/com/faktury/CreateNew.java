package com.faktury;

//* Two table of information 1) Comapny 2) Klinet 

//! Requier inforamtion
//?  NIP
//?  Owner Name Surrname
//?  Vat
//?  Price
//?  Name of rec
//?  

public class CreateNew {

    private CreateNew() {
        throw new IllegalStateException("Utility class");
    }

    public static void test() {
        System.err.println("Create New Method");
        Save.saveTest();

    }
}

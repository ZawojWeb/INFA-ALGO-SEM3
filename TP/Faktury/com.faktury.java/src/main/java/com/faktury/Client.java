package com.faktury;
/**
* Basce class which represent a client
*
* @param  buyerName  a name of buyer's company
* @param  buyerNip the most important param, bsc this is kind of id in our app
* @param  buyerAdress a adress of buyer's company
* @return      the image at the specified URL
* @see         Image
*/
public class Client {
    String buyerName;
    Long buyerNip;
    String buyerAdress;

    /**
     * Just the counstructor
     * */ 
    public Client(String buyerName, Long buyerNip2, String buyerAdress) {
        this.buyerName = buyerName;
        this.buyerNip = buyerNip2;
        this.buyerAdress = buyerAdress;
    }
        /**
        * This method is for help save clints in pdf
        * @return      the string 
        */
    @Override
    public String toString() {
        return "Name: " + this.buyerName + " NIP: " + this.buyerNip + " Adress: " + this.buyerAdress;
    }
}

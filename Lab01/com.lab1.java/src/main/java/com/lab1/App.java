package com.lab1;

import java.util.ArrayList;
import java.util.Scanner;


public class App 
{
    public static ArrayList<String> allTasks = new ArrayList<String>();
    public static String option;
    public static Scanner in = new Scanner(System.in);
    
    public static void main( String[] args )
    {
        Boolean runApp =    true;

        System.out.println("Hello in your TODO list");
         do {
            System.out.println("What you want to do?");
            System.out.print("> ");
            option = in.nextLine();
            switch (option.replaceAll(" ", "").toLowerCase()) {
                case "add":
                    Add.addFunction(allTasks); 
                    option = "show";
                    break;

               case "remove":
                    Remove.removeFunction(allTasks);
                   break;

               case "show":
                    Show.showFunction(allTasks);
                   break;
               case "close":
                    runApp = false;
                    in.close();
                   break;
                case "help":
                    System.out.println("Comend which you can use: \n" + "add - for add new task\n" + "remove - for remove one task\n" + "show - for print all tasks\n" + "close - for close app\n");
                    break;
                default:
                    System.out.println("!!! Sorry I dont know what you want to do !!! Write help for more info");
                    break;
            }
         } while (runApp);

    }

}

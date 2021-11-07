package com.faktury;

import de.codeshelf.consoleui.prompt.ConsolePrompt;
import de.codeshelf.consoleui.prompt.ListResult;
import de.codeshelf.consoleui.prompt.PromtResultItemIF;
import de.codeshelf.consoleui.prompt.builder.PromptBuilder;
import jline.TerminalFactory;
import org.fusesource.jansi.AnsiConsole;
import org.w3c.dom.TypeInfo;

import java.io.IOException;
import java.util.HashMap;

import static org.fusesource.jansi.Ansi.ansi;

// TODO in this app
/**
 * * I want to leran making GUI in java so... * So we have to make "faktura"
 ** Code have to be prepare to connect with db (but try to save this localy in
 ** pdf) * Have to be albe to create new faktura in gui, we will need nip
 ** namecomapny owner (name and surrname) * Everyone fakture have to have
 ** indiviudal id
 * 
 */

public class App {

    public static void main(final String[] args) {
        AnsiConsole.systemInstall(); // #1
        System.out.println(ansi().eraseScreen().render("Welcome in invoice generator."));

        try {
            ConsolePrompt prompt = new ConsolePrompt();
            PromptBuilder promptBuilder = prompt.getPromptBuilder();

            promptBuilder.createListPrompt().name("whatToDo").message("What you want to do ").newItem("createNew")
                    .text("Create New").add().newItem("edit").text("Edit").add().newItem("dataOfCompany")
                    .text("Add a data of my company").add().addPrompt();

            HashMap<String, ? extends PromtResultItemIF> result = prompt.prompt(promptBuilder.build());
            String choice = ((ListResult) result.get("whatToDo")).getSelectedId();

            switch (choice) {
            case "createNew":
                CreateNew.test();
                break;
            case "edit":
                Load.loadTest();
                break;
            case "dataOfCompany":
                Company.Comapny();
                break;
            default:
                break;
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                TerminalFactory.get().restore();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}

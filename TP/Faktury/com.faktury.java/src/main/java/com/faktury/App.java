package com.faktury;

import de.codeshelf.consoleui.prompt.ConsolePrompt;
import de.codeshelf.consoleui.prompt.ListResult;
import de.codeshelf.consoleui.prompt.PromtResultItemIF;
import de.codeshelf.consoleui.prompt.builder.PromptBuilder;
import jline.TerminalFactory;
import org.fusesource.jansi.AnsiConsole;

import java.io.IOException;
import java.util.HashMap;

import static org.fusesource.jansi.Ansi.ansi;

public class App {

    public static void main(final String[] args) {
        AnsiConsole.systemInstall();
        System.out.println(ansi().eraseScreen().render("Welcome in invoice generator."));

        try {
            ConsolePrompt prompt = new ConsolePrompt();
            PromptBuilder promptBuilder = prompt.getPromptBuilder();

            promptBuilder.createListPrompt().name("whatToDo").message("What you want to do ").newItem("createNew")
                    .text("Create New").add().newItem("edit").text("Edit").add().addPrompt();

            HashMap<String, ? extends PromtResultItemIF> result = prompt.prompt(promptBuilder.build());
            String choice = ((ListResult) result.get("whatToDo")).getSelectedId();

            switch (choice) {
            case "createNew":
                Invoice.createInvoice();
                break;
            case "edit":
                System.out.println("In building");
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

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
import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;

public class App {

    public static void main(final String[] args) {
        AnsiConsole.systemInstall();
        Folder.createFolder();
        System.out.println(ansi().eraseScreen().render("Welcome in invoice generator."));

        try {
            ConsolePrompt prompt = new ConsolePrompt();
            PromptBuilder promptBuilder = prompt.getPromptBuilder();

            promptBuilder.createListPrompt().name("whatToDo").message("What you want to do ").newItem("createNew")
                    .text("Create New").add().newItem("load").text("Load").add().newItem("exit").text("Exit").add()
                    .addPrompt();

            HashMap<String, ? extends PromtResultItemIF> result = prompt.prompt(promptBuilder.build());
            String choice = ((ListResult) result.get("whatToDo")).getSelectedId();

            switch (choice) {
            case "createNew":
                Invoice.createInvoice();
                break;
            case "load":
                TextIO textIO = TextIoFactory.getTextIO();
                Integer NIP = textIO.newIntInputReader().read("Put NIP wchih invoices you want to load");
                Invoice.Load(NIP);
                break;
            case "exit":
                System.exit(0);
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

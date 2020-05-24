package pl.komponentowe.logic;

import javafx.application.Application;

public class Main {

    public static void main(String[] args)  {
        if (0 == args.length) {
            try {
                Application.launch(GraphicalUserInterface.class, args);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if ("-c".equals(args[0]) && 1 == args.length) {
            CommandLine commandLine = new CommandLine();
        } else if ("-h".equals(args[0]) && 1 == args.length) {
            System.out.println("No arguments - GUI version, full functionality");
            System.out.println("-c - CLI version, no car driving functionality");
            System.out.println("-h - view help (this message)");
        } else {
            System.out.println("Wrong arguments");
        }
    }


}

package yh.gulaboken;

import yh.gulaboken.session.SessionFactory;

import java.util.Scanner;

public class Application {
    private Scanner scanner;

    /**
     * Application constructor.
     */
    Application() {
        scanner = new Scanner(System.in);
    }

    private void mainMenu() {
        var session = SessionFactory.getSingleton();
        while(true) {
            System.out.println("""
                    Yellow Book - Main Menu
                                    
                    Commands:
                        search <keywords>
                        add
                    """);
            if(session.getUser().)
                        login <username> <password>
                        quit
                    """);
            var command = getLine();
            if(command[0].equals("quit")) {
                
            } else if (command[0].equals("search") && command.length > 1) {
                
            } else if (command[0].equals("add")) {
                
            } else if (command[0].equals("login") && command.length == 3) {
                
                
            }
            else {
                System.out.println("Invalid command.");
            }
        }
    }


    private String[] getLine() {
        return scanner.nextLine().trim().split("\\s+");
    }

    /**
     * Main function
     * @param args
     */
    public static void main(String[] args) {
        new Application().mainMenu();
    }
}
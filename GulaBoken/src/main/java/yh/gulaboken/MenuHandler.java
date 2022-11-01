package yh.gulaboken;

import java.util.Arrays;

public class MenuHandler {
    private final IAppContext context;

    /**
     * Constructor
     * @param context Application context
     */
    public MenuHandler(IAppContext context) {
        this.context = context;
    }

    /**
     * Show and handle main menu.
     */
    public void mainMenu() {
        var session = context.getSession();
        while(true) {

            // print menu

            System.out.format("""
                    Yellow Book - Main Menu
                    Welcome %s.
                    
                    Commands:
                        search <keywords>
                        add
                    > """, session.getUser().getUsername());
            if(session.getUser().isAdmin()) {
                System.out.println("    logout");
            } else {
                System.out.println("    login <username> <password>");
            }
            System.out.println("    quit");

            // handle command

            var command = getLine(); // read command

            if(command[0].equals("quit")) {
                return;
            } else if (command[0].equals("search") && command.length > 1) {
                search(Arrays.copyOfRange(command, 1, command.length));
            } else if (command[0].equals("add")) {
                addMenu();
            } else if (command[0].equals("login") && command.length == 3) {
                var user = context.getUserDatabase().authenticate(command[1],command[2]);
                if(user==null) {
                    System.out.println("Login failed.");
                    continue;
                }
                session.setUser(user);
                System.out.format("User %s logged in.\n", user.getUsername());
            }
            else if (command[0].equals("logout")) {
                session.setUser(null);
            }
            else {
                System.out.println("Invalid command.");
            }
        }
    }

    private void addMenu() {
    }

    private void search(String[] keywords) {
        var contacts = context.getContactDatabase().query(keywords);
        if(contacts.length == 1) {
            printContact(contacts[0]);
        } else if (contacts.length > 1) {
            for (var contact : contacts) {
                System.out.format("%d: %s %s\n",
                        contact.getContactId(),
                        contact.getName(),
                        contact.getSurname()
                );
            }
            //TODO: show choices: show, delete, update
        } else {
            System.out.println("Nothing found.");
        }
    }


    private String[] getLine() {
        return context.getScanner().nextLine().trim().split("\\s+");
    }

    private void printContact() {
        //TODO: print contact
    }
}

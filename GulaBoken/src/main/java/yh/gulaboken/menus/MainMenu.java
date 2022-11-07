package yh.gulaboken.menus;

import yh.gulaboken.IAppContext;

import java.util.List;

public class MainMenu extends BaseMenu {

    public MainMenu(IAppContext context) {
        super(context);
    }

    /**
     * Enter main menu.
     */
    public void show() {
        var session = context.getSession();
        var reader = context.getLineReader();
        while (true) {
            // print menu
            System.out.format("""
                    ----------------------------------------
                    Yellow Book - Main Menu
                        Hello %s
                    ----------------------------------------
                        COMMANDS
                    free KEYWORD...
                    search "name"|"surname"|"street"|"phone" VALUE
                    add
                    """, session.getUser().getUsername());
            if (session.getUser().isAdmin()) {
                System.out.println("logout");
            } else {
                System.out.println("login USERNAME PASSWORD");
            }
            System.out.print("quit\n----------------------------------------\n> ");

            // handle command

            var commandLine = reader.getLine(); // read command
            var command = commandLine.get(INDEX_0);
            var size = commandLine.size();

            if (command.equals("quit")) {
                return;
            } else if (command.equals("free") && size > 1) {
                freeSearch(commandLine.subList(INDEX_1, size));
            } else if (command.equals("search") && size == 3) {
                search(commandLine.get(INDEX_1), commandLine.get(INDEX_2));
            } else if (command.equals("add")) {
                new EditMenu(context).show(null);
            } else if (command.equals("login") && size == 3) {
                loginUser(commandLine.get(INDEX_1), commandLine.get(INDEX_2));
            } else if (command.equals("logout")) {
                session.setUser(null);
            } else {
                System.out.println("Invalid command.");
            }
        }
    }

    /**
     * Search over specific property in contacts.
     *
     * @param property search property name
     * @param needle   query string
     */
    private void search(String property, String needle) {
        // Get database from context and query for contacts.
        var contacts = context.getContactDatabase().queryByProperty(property, needle);
        // Handle results.
        new SearchResultsMenu(context).show(contacts);
    }

    /**
     * Free text search in contacts.
     *
     * @param keywords
     */
    private void freeSearch(List<String> keywords) {
        // Get database from context and query for contacts.
        var contacts = context.getContactDatabase().queryByKeywords(keywords);
        // Handle results.
        new SearchResultsMenu(context).show(contacts);
    }


}

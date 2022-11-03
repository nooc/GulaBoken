package yh.gulaboken;

import java.util.*;

public class MenuHandler {
    private static List<String> ADD_ITEMS = new ArrayList<>(Arrays.asList(
            "name","surname","age","phone","street","city","zip"
    ));
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
                    ----------------------------------------
                    Yellow Book - Main Menu
                        Hello %s
                    ----------------------------------------
                        COMMANDS
                    free KEYWORD...
                    search "name"|"surname"|"street"|"phone" VALUE
                    add
                    """, session.getUser().getUsername());
            if(session.getUser().isAdmin()) {
                System.out.println("logout");
            } else {
                System.out.println("login USERNAME PASSWORD");
            }
            System.out.print("quit\n----------------------------------------\n> ");

            // handle command

            var commandLine = getLine(); // read command
            var command = commandLine.get(0);
            var size = commandLine.size();

            if(command.equals("quit")) {
                return;
            } else if (command.equals("free") && size > 1) {
                freeSearch(commandLine.subList(1,size));
            }
            else if (command.equals("search") && size == 3) {
                search(commandLine.get(1), commandLine.get(2));
            }else if (command.equals("add")) {
                editMenu(null);
            } else if (command.equals("login") && size == 3) {
                // find authenticated user
                var user = context.getUserDatabase().authenticate(commandLine.get(1),commandLine.get(2));
                if(user==null) {
                    System.out.println("Login failed.");
                    continue;
                }
                // set session user
                session.setUser(user);
                System.out.format("User %s logged in.\n", user.getUsername());
            }
            else if (command.equals("logout")) {
                session.setUser(null);
            }
            else {
                System.out.println("Invalid command.");
            }
        }
    }

    /**
     * Create or edit contact.
     * @param contact Contact or null
     */
    private void editMenu(IContact contact) {
        var contactProperties = getContactProperties(contact);
        System.out.format("""
            ----------------------------------------
            Yellow Book - %s Contact
                Hello %s
            ----------------------------------------
        """,
                contact==null ? "Create" : "Edit",
                context.getSession().getUser().getUsername());
        while(true) {
            System.out.print("""
                    COMMANDS
                name VALUE
                surname VALUE
                age VALUE
                phone VALUE...
                street VALUE
                city VALUE
                zip VALUE
                apply
                cancel
                ----------------------------------------
                """);
            if (!contactProperties.isEmpty()) {
                for (var entry : contactProperties.entrySet()) {
                    System.out.format("%s = %s\n", entry.getKey(), entry.getValue());
                }
                System.out.println("----------------------------------------");
            }
            System.out.print("> ");
            var commandLine = getLine(2); // read command
            var command = commandLine.get(0);

            if(command.equals("apply")) {
                // validate
                if(!contactProperties.containsKey("name")) {
                    System.out.println("Name is missing.");
                    continue;
                }
                if(!contactProperties.containsKey("surname")) {
                    System.out.println("Surname is missing.");
                    continue;
                }
                if(!contactProperties.containsKey("phone")) {
                    System.out.println("Phone numbers are missing.");
                    continue;
                }
                // create / update

                if(contact == null) {
                    var newContact = context.getContactDatabase().create(contactProperties);
                    System.out.format("Created contact with id %d.\n", newContact.getContactId());
                    break;
                } else if(context.getContactDatabase().update(contact)) {
                    System.out.format("Updated contact with id %d.\n", contact.getContactId());
                    break;
                } else {
                    System.out.format("%s failed\n", contact==null ? "Create" : "Update");
                }
            } else if (command.equals("cancel")) {
                break;
            } else if(commandLine.size() > 1 && ADD_ITEMS.contains(command)) {
                // has value
                contactProperties.put(command, commandLine.get(1));
            } else {
                System.out.println("Invalid input.");
            }
        }
    }

    /**
     * Get contact properties as map.
     * @param contact Contact
     * @return Key value map.
     */
    private Map<String,String> getContactProperties(IContact contact) {
        var properties = new HashMap<String,String>();
        if(contact != null) {
            properties.put("name", contact.getName());
            properties.put("surname", contact.getSurname());
            properties.put("age", contact.getAge());
            properties.put("phone", contact.getTelephoneNumber());
            properties.put("street", contact.getStreet());
            properties.put("city", contact.getCity());
            properties.put("zip", contact.getZipCode());
        }
        return properties;
    }

    /**
     * Search over specific property in contacts.
     * @param property search property name
     * @param needle query string
     */
    private void search(String property, String needle) {
        // Query for contacts
        var contacts = context.getContactDatabase().query(property, needle);
        // Handle results.
        searchResultsMenu(contacts);
    }

    /**
     * Free text search in contacts.
     * @param keywords
     */
    private void freeSearch(List<String> keywords) {
        // Query for contacts
        var contacts = context.getContactDatabase().query(keywords);
        // Handle results.
        searchResultsMenu(contacts);
    }

    /**
     * Handle search results.
     * @param contacts Search results
     */
    private void searchResultsMenu(List<IContact> contacts) {
        if(contacts.isEmpty()) {
            System.out.println("Nothing found.");
            return;
        }
        System.out.format("""
        ----------------------------------------
        Yellow Book - Search Results
            Hello %s
        ----------------------------------------
        """, context.getSession().getUser().getUsername());
        while(true) {
            if (contacts.size() == 1) {
                // if only one result, show it
                printContact(contacts.get(0));
            } else if (contacts.size() > 1) {
                // if more than one result, show a simplified list of results
                for (var contact : contacts) {
                    System.out.format("%d: %s %s\n",
                            contact.getContactId(),
                            contact.getName(),
                            contact.getSurname()
                    );
                }
            } else {
                // no more results
                break;
            }
            System.out.println("    COMMANDS");
            if(contacts.size() > 1) {
                System.out.println("show ID");
            }
            if(context.getSession().getUser().isAdmin()) {
                System.out.println("update ID");
                System.out.println("delete ID");
            }
            System.out.print("""
                        back
                        ----------------------------------------
                        > """);

            var commandLine = getLine(2); // read command
            var command = commandLine.get(0);
            int contactId;

            if(command.equals("back")) { break; }
            else if(commandLine.size() == 2) {
                try {
                    contactId = Integer.parseInt(commandLine.get(1));
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                    continue;
                }
            } else {
                // all commands except back take one id argument
                System.out.println("Invalid parameters.");
                continue;
            }

            if(command.equals("show")) {
                for (var contact : contacts) {
                    if(contact.getContactId()==contactId) {
                        printContact(contact);
                    }
                }
            } else if(context.getSession().getUser().isAdmin()) {
                if(command.equals("update")) {
                    for (var contact : contacts) {
                        if(contact.getContactId()==contactId) {
                            editMenu(contact);
                        }
                    }
                } else if(command.equals("delete")) {
                    for (var contact : contacts) {
                        if(contact.getContactId() == contactId) {
                            contacts.remove(contact);
                            System.out.format("Removed contact %d.\n", contactId);
                        }
                    }
                }
            }
        }
    }



        private List<String> getLine(int splitLimit) {
        var line= context.getScanner().nextLine().trim().split("\\s+", splitLimit);
        return Arrays.stream(line).toList();
    }
        private List<String> getLine() {
            return getLine(0);
        }

    private void printContact(IContact contact) {
        var addressStr = contact.getAddressLine();
        System.out.format("""
                CONTACT %d
                   Name: %s %s
                    Age: %s
                    Tel: %s
                """,
                contact.getContactId(),
                contact.getName(), contact.getSurname(),
                contact.getAge(),
                contact.getTelephoneNumber());
        if(!addressStr.isEmpty()) {
            System.out.format("Address: %s\n", addressStr);
        }
    }
}

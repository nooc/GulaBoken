package yh.gulaboken;

import yh.gulaboken.models.Contact;

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
                    Yellow Book - Main Menu
                    Welcome %s.
                    
                    Commands:
                    freesearch KEYWORDS...
                        search name|surname|street VALUE
                           add
                    > """, session.getUser().getUsername());
            if(session.getUser().isAdmin()) {
                System.out.println("    logout");
            } else {
                System.out.println("    login <username> <password>");
            }
            System.out.println("    quit");

            // handle command

            var commandLine = getLine(); // read command
            var command = commandLine.get(0);
            var size = commandLine.size();

            if(command.equals("quit")) {
                return;
            } else if (command.equals("freesearch") && size > 1) {
                freeSearch(commandLine.subList(1,size));
            }
            else if (command.equals("search") && size == 2) {
                search(command, commandLine.get(1));
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
    private void editMenu(Contact contact) {
        var contactProperties = getContactProperties(contact);
        System.out.format("Yellow Book - %s Contact\n", contact==null ? "Create" : "Edit");
        while(true) {
            if (!contactProperties.isEmpty()) {
                System.out.println("Current properties:");
                for (var entry : contactProperties.entrySet()) {
                    System.out.format("   $s: $s\n", entry.getKey(), entry.getValue());
                }
            }
            System.out.format("""
                    Commands:
                        name <value>
                        surname <value>
                        age <value>
                        phone <value> [<value> ...]
                        street <value>
                        city <value>
                        zip <value>
                        apply
                        cancel
                    > """);
            var commandLine = getLine(1); // read command
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
                Contact contact2;
                if(contact == null) {
                    contact2 = new Contact();
                } else {
                    contact2 = contact;
                }
                var addr = contact2.getAddress();
                contact2.setName(contactProperties.get("name"));
                contact2.setSurname(contactProperties.get("surname"));
                contact2.setAge(contactProperties.get("age"));
                contact2.setTelephoneNumber(contactProperties.get("phone"));
                addr.setStreet(contactProperties.get("street"));
                addr.setCity(contactProperties.get("city"));
                addr.setZipCode(contactProperties.get("zip"));

                if(contact == null) {
                    contact2 = context.getContactDatabase().create(contact2);
                    System.out.format("Created contact with id %d.\n", contact.getContactId());
                    break;
                } else if(context.getContactDatabase().update(contact2)) {
                    System.out.format("Updated contact with id %d.\n", contact2.getContactId());
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
    private Map<String,String> getContactProperties(Contact contact) {
        var properties = new HashMap<String,String>();
        if(contact != null) {
            var addr = contact.getAddress();
            properties.put("name", contact.getName());
            properties.put("surname", contact.getSurname());
            properties.put("age", contact.getAge());
            properties.put("phone", contact.getTelephoneNumber());
            properties.put("street", addr.getStreet());
            properties.put("city", addr.getCity());
            properties.put("zip", addr.getZipCode());
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
    private void searchResultsMenu(List<Contact> contacts) {
        if(contacts.isEmpty()) {
            System.out.println("Nothing found.");
            return;
        }
        System.out.println("Yellow Book - Search Results");
        while(true) {
            if (contacts.size() == 1) {
                // if only one result, show it
                printContact(contacts.get(1));
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
            System.out.format("""
                    Commands:
                        show <id>
                    """);
            if(context.getSession().getUser().isAdmin()) {
                System.out.println("    update <id>");
                System.out.println("    delete <id>");
            }
            System.out.format("""
                        back
                    > """);

            var commandLine = getLine(1); // read command
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

            if(context.getSession().getUser().isAdmin()) {
                if(command.equals("update")) {
                    for (var contact : contacts) {
                        if(contact.getContactId()==contactId) {
                            editMenu(contact);
                        }
                    }
                } else if(command.equals("delete")) {
                    for (var contact : contacts) {
                        if(contact.getContactId()==contactId) {
                            contacts.remove(contact);
                            System.out.format("Removed contact %d.\n", contactId);
                        }
                    }
                }
            }
            else if(command.equals("show")) {
                for (var contact : contacts) {
                    if(contact.getContactId()==contactId) {
                        printContact(contact);
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

    private void printContact(Contact contact) {
        System.out.format("""
                --- Contact id: %d ---
                   Name: %s %s
                    Age: %s
                    Tel: %s
                Address: %s
                """,
                contact.getContactId(),
                contact.getName(), contact.getSurname(),
                contact.getAge(),
                contact.getTelephoneNumber(),
                contact.getAddress());
    }
}

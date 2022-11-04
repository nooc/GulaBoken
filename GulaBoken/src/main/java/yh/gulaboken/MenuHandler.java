package yh.gulaboken;

import yh.gulaboken.utils.StringValidator;

import java.util.*;

public class MenuHandler {
    private static final int SPLIT_IN_TWO = 2;
    private static final int INDEX_0 = 0;
    private static final int INDEX_1 = 1;
    private static final int INDEX_2 = 2;
    private static final List<String> ADDABLE_ITEMS = new ArrayList<>(Arrays.asList(
            "name", "surname", "age", "phone", "street", "city", "zip"
    ));
    private final IAppContext context;

    /**
     * Constructor
     *
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
                editMenu(null);
            } else if (command.equals("login") && size == 3) {
                // find authenticated user
                var user = context.getUserDatabase()
                        .authenticate(commandLine.get(INDEX_1), commandLine.get(INDEX_2));
                if (user == null) {
                    System.out.println("Login failed.");
                    continue;
                }
                // set session user
                session.setUser(user);
                System.out.format("User %s logged in.\n", user.getUsername());
            } else if (command.equals("logout")) {
                session.setUser(null);
            } else {
                System.out.println("Invalid command.");
            }
        }
    }

    /**
     * Create or edit contact.
     *
     * @param contact Contact or null
     */
    private void editMenu(IContact contact) {
        var reader = context.getLineReader();
        var contactProperties = getContactProperties(contact);
        System.out.format("""
                            ----------------------------------------
                            Yellow Book - %s Contact
                                Hello %s
                            ----------------------------------------
                        """,
                contact == null ? "Create" : "Edit",
                context.getSession().getUser().getUsername());
        while (true) {
            System.out.print("""
                        COMMANDS
                    name VALUE      - Name.
                    surname VALUE   - Surname.
                    age VALUE       - Age 0-130.
                    phone VALUE     - Comma separated list of phone numbers.
                    street VALUE    - Street name and number.
                    city VALUE      - City name.
                    zip VALUE       - Zip code of 5 digits.
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
            var commandLine = reader.getLine(SPLIT_IN_TWO); // read command
            var command = commandLine.get(INDEX_0);

            if (command.equals("apply")) {
                // validate
                if (!contactProperties.containsKey("name")) {
                    System.out.println("Name is missing.");
                    continue;
                }
                if (!contactProperties.containsKey("surname")) {
                    System.out.println("Surname is missing.");
                    continue;
                }
                if (!contactProperties.containsKey("phone")) {
                    System.out.println("Phone numbers are missing.");
                    continue;
                }
                // create / update

                if (contact == null) {
                    var newContact = context.getContactDatabase().create(contactProperties);
                    System.out.format("Created contact with id %d.\n", newContact.getContactId());
                    break;
                } else if (context.getContactDatabase().update(contact)) {
                    System.out.format("Updated contact with id %d.\n", contact.getContactId());
                    break;
                } else {
                    System.out.format("%s failed\n", contact == null ? "Create" : "Update");
                }
            } else if (command.equals("cancel")) {
                break;
            } else if (commandLine.size() > 1 && ADDABLE_ITEMS.contains(command)) {

                if (command.equals("phone")) {
                    // get string of number(s) and validate using StringValidator
                    var numbers = commandLine.get(INDEX_1);
                    if (!StringValidator.validatePhoneNumbers(numbers)) {
                        System.out.println("Invalid phone number(s).");
                        continue;
                    }
                    // split and join to reformat and ensures proper separator
                    // then replace item in the list.
                    var numbersArray = numbers.split(",\\s*");
                    commandLine.set(INDEX_1, String.join(", ", numbersArray));

                } else if (command.equals("zip")
                        && !StringValidator.validateZipcode(commandLine.get(1))) {
                    // validate zip
                    System.out.println("Invalid zip code.");
                    continue;
                }
                // command has value -> add/replace in map
                contactProperties.put(command, commandLine.get(1));
            } else {
                System.out.println("Invalid input.");
            }
        }
    }

    /**
     * Get contact properties as map.
     *
     * @param contact Contact
     * @return Key value map.
     */
    private Map<String, String> getContactProperties(IContact contact) {
        var properties = new HashMap<String, String>();
        if (contact != null) {
            properties.put("name", contact.getName());
            properties.put("surname", contact.getSurname());
            properties.put("age", contact.getAge());
            properties.put("phone", contact.getPhoneNumber());
            properties.put("street", contact.getStreet());
            properties.put("city", contact.getCity());
            properties.put("zip", contact.getZipCode());
        }
        return properties;
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
        manageContactsMenu(contacts);
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
        manageContactsMenu(contacts);
    }

    /**
     * Manage set of provided contacts.
     * Show, update or delete provided contacts.
     *
     * @param contactsIn List of contacts
     */
    private void manageContactsMenu(List<IContact> contactsIn) {
        var reader = context.getLineReader();
        var contacts = new ArrayList<IContact>(contactsIn);
        if (contacts.isEmpty()) {
            // Nothing to do
            System.out.println("Nothing found.");
            return;
        }
        System.out.format("""
                ----------------------------------------
                Yellow Book - Search Results
                    Hello %s
                ----------------------------------------
                """, context.getSession().getUser().getUsername());
        // menu loop
        while (true) {
            if (contacts.size() == 1) {
                // if only one contact, show it
                printContact(contacts.get(INDEX_0));
            } else if (contacts.size() > 1) {
                // if more than one contact, show a list of contacts
                for (var contact : contacts) {
                    System.out.format("%d: %s %s\n",
                            contact.getContactId(),
                            contact.getName(),
                            contact.getSurname()
                    );
                }
            } else {
                // empty contacts -> break loop
                break;
            }
            System.out.println("    COMMANDS");
            if (contacts.size() > 1) { // single contact is auto shown. hide in that case.
                System.out.println("show ID");
            }
            if (context.getSession().getUser().isAdmin()) {
                // show admin menu items
                System.out.println("update ID");
                System.out.println("delete ID");
            }
            System.out.print("""
                    back
                    ----------------------------------------
                    > """);

            var commandLine = reader.getLine(SPLIT_IN_TWO); // read command and value
            var command = commandLine.get(INDEX_0);
            long contactId;

            if (command.equals("back")) {
                // break loop on back
                break;
            } else if (commandLine.size() == 2) {
                // command has one value
                try {
                    // value should be a number id
                    contactId = Long.parseLong(commandLine.get(INDEX_1));
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                    continue;
                }
            } else {
                // all commands except back take one argument
                System.out.println("Invalid parameters.");
                continue;
            }

            if (command.equals("show")) {
                // show contact with contactId
                for (var contact : contacts) {
                    // search and show
                    if (contact.getContactId() == contactId) {
                        printContact(contact);
                    }
                }
            } else if (context.getSession().getUser().isAdmin()) {
                // update contact with contactId
                if (command.equals("update")) {
                    // search and edit
                    for (var contact : contacts) {
                        if (contact.getContactId() == contactId) {
                            // edit contact
                            editMenu(contact);
                        }
                    }
                } else if (command.equals("delete")) {
                    // delete contact with contactId
                    for (var contact : contacts) {
                        // search and remove
                        if (contact.getContactId() == contactId) {
                            contacts.remove(contact);
                            context.getContactDatabase().delete(contactId);
                            System.out.format("Removed contact %d.\n", contactId);
                            break;
                        }
                    }
                }
            }
        }
    }

    /**
     * Output contact to console.
     *
     * @param contact
     */
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
                contact.getPhoneNumber());
        if (!addressStr.isEmpty()) {
            System.out.format("Address: %s\n", addressStr);
        }
    }
}

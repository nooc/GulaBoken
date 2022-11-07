package yh.gulaboken.menus;

import yh.gulaboken.IAppContext;
import yh.gulaboken.IContact;

import java.util.ArrayList;
import java.util.List;

public class SearchResultsMenu extends BaseMenu {

    /**
     * Constructor
     *
     * @param context Application context
     */
    protected SearchResultsMenu(IAppContext context) {
        super(context);
    }

    /**
     * Manage set of provided contacts.
     * Show, update or delete provided contacts.
     *
     * @param contactsIn List of contacts
     */
    public void show(List<IContact> contactsIn) {
        var reader = context.getLineReader();
        var contacts = new ArrayList<>(contactsIn);
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
                // empty contacts -> exit loop
                return;
            }
            System.out.println("    COMMANDS");
            if (contacts.size() > 1) { // single contact is auto shown. hide in that case.
                System.out.println("show ID");
            }
            if (context.getSession().getUser().isAdmin()) {
                // show admin menu items
                System.out.println("update ID");
                System.out.println("delete ID");
                System.out.println("logout");
            } else {
                System.out.println("login USERNAME PASSWORD");
            }
            System.out.print("back\n----------------------------------------\n> ");

            var commandLine = reader.getLine(); // read command and value
            var command = commandLine.get(INDEX_0);
            var size = commandLine.size();
            long contactId = 0;

            if (command.equals("back")) {
                // exit loop on back
                return;
            } else if (size == 2) {
                // command has one value
                try {
                    // value should be a number id
                    contactId = Long.parseLong(commandLine.get(INDEX_1));
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                    continue;
                }
            }

            if (command.equals("show") && contactId > 0) {
                // show contact with contactId
                for (var contact : contacts) {
                    // search and show
                    if (contact.getContactId() == contactId) {
                        printContact(contact);
                    }
                }
            } else if (command.equals("update") && contactId > 0) {
                if (!context.getSession().getUser().isAdmin()) {
                    System.out.println("Please log in to update.");
                    continue;
                }
                // search and edit
                for (var contact : contacts) {
                    if (contact.getContactId() == contactId) {
                        // edit contact
                        new EditMenu(context).show(contact);
                    }
                }
            } else if (command.equals("delete") && contactId > 0) {
                if (!context.getSession().getUser().isAdmin()) {
                    System.out.println("Please log in to delete.");
                    continue;
                }
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
            } else if (command.equals("login") && size == 3) {
                loginUser(commandLine.get(INDEX_1), commandLine.get(INDEX_2));
            } else if (command.equals("logout")) {
                context.getSession().setUser(null);
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
        var age = contact.getAge();
        System.out.format("""
                        CONTACT %d
                           Name: %s
                        Surname: %s
                            Tel: %s
                        """,
                contact.getContactId(),
                contact.getName(),
                contact.getSurname(),
                contact.getPhoneNumbers());
        if (!age.isEmpty()) {
            System.out.format("    Age: %s\n");
        }
        if (!addressStr.isEmpty()) {
            System.out.format("Address: %s\n", addressStr);
        }
    }
}

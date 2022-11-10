package yh.gulaboken.menus;

import yh.gulaboken.IAppContext;
import yh.gulaboken.IContact;
import yh.gulaboken.utils.StringValidator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Enter edit menu.
 * A menu for editing new or existing contact.
 */
public class EditMenu extends BaseMenu {
    private static final List<String> ADDABLE_ITEMS = new ArrayList<>(Arrays.asList(
            "name", "surname", "age", "phone", "street", "city", "zip"
    ));
    private final IContact contact;

    /**
     * Constructor
     * @param context App context
     * @param contact Contact instance or null
     */
    public EditMenu(IAppContext context, IContact contact) {
        super(context);
        this.contact = contact;
    }

    @Override
    public void show() {
        var reader = context.getLineReader();
        var contactProperties =
                contact == null ? new HashMap<String, String>() : contact.asPropertiesMap();
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
                    clear PROPERTY  - Clear the property value.
                    apply
                    cancel
                    ----------------------------------------
                    """);
            if (!contactProperties.isEmpty()) {
                for (var entry : contactProperties.entrySet()) {
                    var value = entry.getValue();
                    System.out.format("%s = %s\n", entry.getKey(),
                            value.isEmpty() ? "<clear>" : value);
                }
                System.out.println("----------------------------------------");
            }
            System.out.print("> ");
            var commandLine = reader.getLine(SPLIT_IN_TWO); // read command
            var command = commandLine.get(INDEX_0);

            if (command.equals("apply")) {
                // must have minimum of name, surname and phone.
                boolean fail = false;
                if (!contactProperties.containsKey("name")) {
                    System.out.println("Name is missing.");
                    fail = true;
                }
                if (!contactProperties.containsKey("surname")) {
                    System.out.println("Surname is missing.");
                    fail = true;
                }
                if (!contactProperties.containsKey("phone")) {
                    System.out.println("Phone numbers are missing.");
                    fail = true;
                }
                if (fail) {
                    continue;
                }

                IContact newContact;
                if (contact == null
                        && (newContact = context.getContactDatabase().create(contactProperties)) != null) {
                    // contact == null -> create new
                    System.out.format("Created contact with id %d.\n", newContact.getContactId());
                    return;
                } else if (context.getContactDatabase().update(contact.getContactId(), contactProperties)) {
                    // updated existing contact
                    System.out.format("Updated contact with id %d.\n", contact.getContactId());
                    return;
                }
                // operation failed
                System.out.format("%s failed\n", contact == null ? "Create" : "Update");
            } else if (command.equals("cancel")) {
                return;
            } else if (command.equals("clear") && commandLine.size() == 2) {
                // clear key/value from properties map
                var key = commandLine.get(INDEX_1).toLowerCase();
                if (ADDABLE_ITEMS.contains(key)
                        && contactProperties.containsKey(key)) {
                    contactProperties.replace(key, "");
                }
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
                        && !StringValidator.validateZipcode(commandLine.get(INDEX_1))) {
                    // validate zip
                    System.out.println("Invalid zip code.");
                    continue;
                }
                // command has value -> add/replace in map
                contactProperties.put(command, commandLine.get(INDEX_1));
            } else {
                System.out.println("Invalid input.");
            }
        }
    }
}

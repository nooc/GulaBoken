package yh.gulaboken.filedatabase;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import yh.gulaboken.IContact;
import yh.gulaboken.IContactDatabase;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.*;

/**
 * A contact database stored as a json file.
 */
class FileContactDatabase implements IContactDatabase {
    /**
     * Gson serializer.
     */
    private final Gson gson;
    /**
     * File backend.
     */
    private final File dataFile;
    /**
     * Wrapper instance.
     */
    private DataWrapper wrapper;

    /**
     * Constructor
     * Try reading data from file, else initialize with new, empty data.
     *
     * @param dataFile json file
     */
    FileContactDatabase(File dataFile) {
        this.dataFile = dataFile;
        // Create gson instance with pretty printing.
        this.gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
        // Create wrapper from json.
        try (FileReader fileReader = new FileReader(dataFile)) {
            this.wrapper = gson.fromJson(fileReader, DataWrapper.TYPE);
            if (this.wrapper != null) {
                return;
            }
        } catch (Exception e) {
        }
        // Create new, wrapper if read fails.
        this.wrapper = new DataWrapper();
    }

    @Override
    public IContact create(Map<String, String> properties) {
        // Check for minimum info
        if (properties.containsKey("name")
                && properties.containsKey("surname")
                && properties.containsKey("phone")) {
            // Create contact instance and set data.
            var contact = new Contact(wrapper.nextId());
            contact.setName(properties.get("name"));
            contact.setSurname(properties.get("surname"));
            contact.setAge(properties.get("age"));
            contact.setPhoneNumbers(properties.get("phone"));
            contact.setStreet(properties.get("street"));
            contact.setCity(properties.get("city"));
            contact.setZipCode(properties.get("zip"));
            // Add to list and persist to file.
            wrapper.getContactList().add(contact);
            writeToFile();
            return contact; // Return new contact instance.
        }
        return null;
    }

    @Override
    public IContact read(long id) {
        for (var contact : wrapper.getContactList()) {
            if (contact.getContactId() == id) {
                return contact;
            }
        }
        return null;
    }

    @Override
    public boolean update(IContact contact) {
        if (contact != null && wrapper.getContactList().contains(contact)) {
            return writeToFile();
        }
        return false;
    }

    @Override
    public boolean update(long id, Map<String, String> properties) {
        var dBContact = read(id);
        if (dBContact == null) {
            // not found
            return false;
        }
        // found -> update
        dBContact.updateFromMap(properties);
        return writeToFile();
    }

    /**
     * @see IContactDatabase#delete(long)
     */
    @Override
    public boolean delete(long id) {
        // find contact
        var contact = read(id);
        if (contact != null) {
            // found
            wrapper.getContactList().remove(contact);
            writeToFile();
            return true;
        }
        return false;
    }

    /**
     * Persist data to json file.
     */
    private boolean writeToFile() {
        try (FileWriter fileWriter = new FileWriter(dataFile)) {
            // write json
            gson.toJson(wrapper, DataWrapper.TYPE, fileWriter);
            return true;
        } catch (Exception e) {
            System.out.println("Write to file failed.");
        }
        return false;
    }

    @Override
    public List<IContact> queryByKeywords(Collection<String> keywords) {
        // result set
        List<IContact> foundContacts = new LinkedList<>();
        // make lowercase
        var keywordsArray = keywords.toArray(new String[keywords.size()]);
        for (int i = 0; i < keywordsArray.length; ++i) {
            keywordsArray[i] = keywordsArray[i].toLowerCase();
        }
        // query all contacts
        for (var contact : wrapper.getContactList()) {
            // for all contacts...
            var haystack = getHaystack(contact);
            int keywordsFound = 0; // keys found
            for (var keyword : keywordsArray) {
                // for all keys...
                for (var propertyValue : haystack) {
                    // for all searchable values in a contact
                    if (propertyValue.contains(keyword)) {
                        // found key match
                        keywordsFound++;
                        break;
                    }
                }
            }
            if (keywordsFound == keywordsArray.length) {
                // all keys found
                foundContacts.add(contact);
            }
        }
        return foundContacts;
    }

    /**
     * Get searchable values as a list of lowercase strings.
     *
     * @param contact Contact
     * @return Collection of strings
     */
    private Collection<String> getHaystack(IContact contact) {
        return Arrays.asList(
                contact.getName().toLowerCase(),
                contact.getSurname().toLowerCase(),
                contact.getAge(),
                contact.getPhoneNumbers(),
                contact.getAddressLine().toLowerCase()
        );
    }

    @Override
    public List<IContact> queryByProperty(String property, String query) {
        // result set
        List<IContact> foundContacts = new LinkedList<>();
        // assure lower case
        var lowercaseQuery = query.toLowerCase();
        // query all contacts
        for (var contact : wrapper.getContactList()) {
            if (property.equals("name") && contact.getName().toLowerCase().contains(lowercaseQuery)) {
                foundContacts.add(contact);
            } else if (property.equals("surname") && contact.getSurname().toLowerCase().contains(lowercaseQuery)) {
                foundContacts.add(contact);
            } else if (property.equals("street") && contact.getStreet().toLowerCase().contains(lowercaseQuery)) {
                foundContacts.add(contact);
            } else if (property.equals("phone") && contact.getPhoneNumbers().contains(lowercaseQuery)) {
                foundContacts.add(contact);
            }
        }
        return foundContacts;
    }
}

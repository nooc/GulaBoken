package yh.gulaboken.filedatabase;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import yh.gulaboken.IContactDatabase;
import yh.gulaboken.models.Contact;

import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 */
class FileContactDatabase implements IContactDatabase {

    /**
     * Wrap data for easier json read/write.
     */
    private class DataWrapper {
        long idCounter; // counter for handling unique contact ids
        List<Contact> contactList; // list of contacts
        DataWrapper() {
            this.idCounter = 100;
            this.contactList = new ArrayList<>();
        }
        long nextId() {
            return idCounter += 1;
        }
    }

    /**
     * File backend
     */
    private final File dataFile;

    private DataWrapper data;

    private final Type dataType;

    /**
     * Constructor
     * Implementation of IContactDatabase with a file backend.
     */
    FileContactDatabase(File dataFile) {
        this.dataFile = dataFile;
        this.dataType = new TypeToken<DataWrapper>(){}.getType();
        try (FileReader fileReader = new FileReader(dataFile)) {
            Gson gson = new Gson();
            data = gson.fromJson(fileReader, dataType);
        } catch (Exception e) {
            this.data = new DataWrapper();
        }
    }

    /**
     * Create database contact.
     * @param newContact
     * @return Contact with new id
     */
    @Override
    public Contact create(Contact newContact) {
        newContact.setContactId(data.nextId());
        data.contactList.add(newContact);
        writeToFile();
        return newContact;
    }

    /**
     * Read contact with id.
     * @param id Contact id
     * @return Contact or null
     */
    @Override
    public Contact read(long id) {
        for(var contact : data.contactList){
            if(contact.getContactId() == id){
                return contact;
            }
        }

        return null;
    }

    /**
     * Update existing contact.
     * @param contact
     * @return True if success, else false.
     */
    @Override
    public boolean update(Contact contact) {
        // find db contact
        var dBContact = read(contact.getContactId());
        if(dBContact == null) {
            // not found
            return false;
        }
        // found -> update
        dBContact.setName(contact.getName());
        dBContact.setSurname(contact.getSurname());
        dBContact.setAge(contact.getAge());
        dBContact.setTelephoneNumber(contact.getTelephoneNumber());
        var dbAddr = dBContact.getAddress();
        var addr = contact.getAddress();
        dbAddr.setStreet(addr.getStreet());
        dbAddr.setCity(addr.getCity());
        dbAddr.setZipCode(addr.getZipCode());
        writeToFile();
        return true;
    }

    /**
     * Delete contact from database.
     * @param id Contact id.
     * @return True if success, else false.
     */
    @Override
    public boolean delete(long id) {
        // find contact
        var contact = read (id);
        if(contact != null) {
            // found
            data.contactList.remove(contact);
            writeToFile();
            return true;
        }
        return false;
    }

    /**
     * Write database to file.
     */
    private void writeToFile() {
        try (FileWriter fileWriter = new FileWriter(dataFile)){
            Gson gson = new Gson();
            gson.toJson(data, dataType, fileWriter);
        } catch (Exception e){
            System.out.println("Error. Didn't write to file :D");
        }
    }


    /**
     * See {@link IContactDatabase}
     */
    @Override
    public List<Contact> query(List<String> keywords) {
        ArrayList<Contact> found = new ArrayList<>(); // result set
        // make lowercase
        String[] lowerKeywords = (String[]) keywords.stream().map(keyword -> keyword.toLowerCase()).toArray();
        // query all contacts

        for(var contact : data.contactList) {
            var haystack = getHaystack(contact);
            for (var entry : haystack) {
                for (var key : lowerKeywords) {
                    if (entry.contains(key)) {
                        found.add(contact);
                    }
                }
            }
        }
        return found;
    }

    /**
     * Get app property values as a list of lowercase strings.
     * @param contact Contact
     * @return List of strings.
     */
    private List<String> getHaystack(Contact contact) {
        return Arrays.asList(
                contact.getName().toLowerCase(),
                contact.getSurname().toLowerCase(),
                contact.getAge(),
                contact.getTelephoneNumber(),
                contact.getAddress().toString().toLowerCase()
        );
    }

    /**
     * Query name, surname, street or phone.
     * @param property property name
     * @param query query string
     * @return List of contacts.
     */
    @Override
    public List<Contact> query(String property, String query) {
        ArrayList<Contact> found = new ArrayList<>(); // result set
        var lowercaseQuery = query.toLowerCase();
        // query all contacts
        for(var contact : data.contactList) {
            if(property.equals("name") && lowercaseQuery.contains(contact.getName().toLowerCase())) {
                found.add(contact);
            } else if (property.equals("surname") && lowercaseQuery.contains(contact.getSurname().toLowerCase())) {
                found.add(contact);
            } else if (property.equals("street") && lowercaseQuery.contains(contact.getAddress().getStreet().toLowerCase())) {
                found.add(contact);
            } else if (property.equals("phone") && lowercaseQuery.contains(contact.getTelephoneNumber())) {
                found.add(contact);
            }
        }
        return found;
    }
}

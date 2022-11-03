package yh.gulaboken.filedatabase;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import yh.gulaboken.IContact;
import yh.gulaboken.IContactDatabase;

import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.*;

/**
 * A contact database stored as a json file.
 */
class FileContactDatabase implements IContactDatabase {

    /**
     * Data wrapper for easy json serialization.
     */
    private class DataWrapper {
        long idCounter; // counter for handling unique contact ids
        List<Contact> contactList; // list of contacts

        /**
         * Constructor
         * Set to initial state.
         */
        DataWrapper() {
            this.idCounter = 100;
            this.contactList = new ArrayList<>();
        }

        /**
         * Get next unique id.
         * @return
         */
        long nextId() {
            return idCounter += 1;
        }
    }

    /**
     * Gson serializer.
     */
    private final Gson gson;
    /**
     * File backend
     */
    private final File dataFile;

    /**
     * Data instance
     */
    private DataWrapper data;

    /**
     * Type for Gson serializer.
     */
    private final Type dataType;

    /**
     * Constructor
     * Try reading data from file, else initialize with new, empty data.
     * @param dataFile json file
     */
    FileContactDatabase(File dataFile) {
        this.dataFile = dataFile;
        // data type of for DataWrapper
        this.dataType = new TypeToken<DataWrapper>(){}.getType();

        // create gson instance with pretty printing.
        this.gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        // read data from json
        try (FileReader fileReader = new FileReader(dataFile)) {
            this.data = gson.fromJson(fileReader, dataType);
        } catch (Exception e) {
            // create new, empty data if read fails
            this.data = new DataWrapper();
        }
    }

    /**
     * Create database contact.
     * @param properties Contact
     * @return Contact with new id
     */
    @Override
    public IContact create(Map<String,String> properties) {
        if(properties.containsKey("name")
                && properties.containsKey("surname")
                && properties.containsKey("phone")) {
            var contact = new Contact();
            contact.setName(properties.get("name"));
            contact.setSurname(properties.get("surname"));
            contact.setAge(properties.get("age"));
            contact.setPhoneNumber(properties.get("phone"));
            contact.setStreet(properties.get("street"));
            contact.setCity(properties.get("city"));
            contact.setZipCode(properties.get("zip"));
            contact.setContactId(data.nextId());
            data.contactList.add(contact);
            writeToFile();
            return contact;
        }
        return null;
    }

    /**
     * Read contact with id.
     * @param id Contact id
     * @return Contact or null
     */
    @Override
    public IContact read(long id) {
        for(var contact : data.contactList){
            if(contact.getContactId() == id){
                return contact;
            }
        }

        return null;
    }

    /**
     * @see IContactDatabase#update(IContact)
     */
    @Override
    public boolean update(IContact contact) {
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
        dBContact.setPhoneNumber(contact.getPhoneNumber());
        dBContact.setStreet(contact.getStreet());
        dBContact.setCity(contact.getCity());
        dBContact.setZipCode(contact.getZipCode());
        writeToFile();
        return true;
    }

    /**
     * @see IContactDatabase#delete(long)
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
     * Persist data to json file.
     */
    private void writeToFile() {
        try (FileWriter fileWriter = new FileWriter(dataFile)) {
            // write json
            gson.toJson(data, dataType, fileWriter);
        } catch (Exception e) {
            System.out.println("Error. Didn't write to file :(");
        }
    }

    /**
     * See {@link IContactDatabase}
     */
    @Override
    public List<IContact> query(List<String> keywords) {
        // result set
        Set<IContact> found = new HashSet<>();
        // make lowercase
        var keywordsArray = keywords.toArray(new String[keywords.size()]);
        for(int i = 0; i<keywordsArray.length; ++i) {
            keywordsArray[i] = keywordsArray[i].toLowerCase();
        }
        // query all contacts
        for(var contact : data.contactList) {
            var haystack = getHaystack(contact);
            for (var entry : haystack) {
                for (var key : keywordsArray) {
                    if (entry.contains(key)) {
                        found.add(contact);
                    }
                }
            }
        }
        return found.stream().toList();
    }

    /**
     * Get searchable values as a list of lowercase strings.
     * @param contact Contact
     * @return Collection of strings
     */
    private Collection<String> getHaystack(IContact contact) {
        return Arrays.asList(
                contact.getName().toLowerCase(),
                contact.getSurname().toLowerCase(),
                contact.getAge(),
                contact.getPhoneNumber(),
                contact.getAddressLine().toLowerCase()
        );
    }

    /**
     * Query name, surname, street or phone.
     * @param property property name
     * @param query query string
     * @return List of contacts.
     */
    @Override
    public List<IContact> query(String property, String query) {
        Set<IContact> found = new HashSet<>(); // result set. guarantees no duplicates
        var lowercaseQuery = query.toLowerCase(); // assure lower case
        // query all contacts
        for(var contact : data.contactList) {
            if(property.equals("name") && contact.getName().toLowerCase().contains(lowercaseQuery)) {
                found.add(contact);
            } else if (property.equals("surname") && contact.getSurname().toLowerCase().contains(lowercaseQuery)) {
                found.add(contact);
            } else if (property.equals("street") && contact.getStreet().toLowerCase().contains(lowercaseQuery)) {
                found.add(contact);
            } else if (property.equals("phone") && contact.getPhoneNumber().contains(lowercaseQuery)) {
                found.add(contact);
            }
        }
        return found.stream().toList();
    }
}

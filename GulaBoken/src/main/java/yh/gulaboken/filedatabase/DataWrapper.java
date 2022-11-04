package yh.gulaboken.filedatabase;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Data wrapper for easy json serialization.
 */
class DataWrapper {
    /**
     * Type for Gson serializer.
     */
    public static final Type TYPE = new TypeToken<DataWrapper>() {
    }.getType();
    private final List<Contact> contactList; // list of contacts
    private long idCounter; // counter for handling unique contact ids

    /**
     * Constructor
     * Set to initial state.
     */
    DataWrapper() {
        this.idCounter = 100;
        this.contactList = new ArrayList<>();
    }

    /**
     * Get list of contacts.
     *
     * @return List of contacts
     */
    public List<Contact> getContactList() {
        return contactList;
    }

    /**
     * Get next database unique id.
     *
     * @return unique id
     */
    long nextId() {
        return idCounter += 1;
    }
}

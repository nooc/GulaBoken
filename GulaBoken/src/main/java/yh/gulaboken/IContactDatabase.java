package yh.gulaboken;

import yh.gulaboken.models.Contact;

import java.util.List;

/**
 *
 */
public interface IContactDatabase {
    /**
     *
     * @param newEntry
     * @return
     */
    Contact create(Contact newEntry);

    /**
     *
     * @param id
     * @return
     */
    Contact read(long id);

    /**
     *
     * @param entry
     * @return
     */
    boolean update(Contact entry);

    /**
     *
     * @param id
     * @return
     */
    boolean delete(long id);

    /**
     * Case-insensitive, free text query over all properties.
     * @param keywords query keywords
     * @return List of matching contacts
     */
    List<Contact> query(List<String> keywords);

    /**
     * Case-insensitive, query over a specific property in contacts.
     * @param property property name
     * @param query query string
     * @return List of matching contacts
     */
    List<Contact> query(String property, String query);
}

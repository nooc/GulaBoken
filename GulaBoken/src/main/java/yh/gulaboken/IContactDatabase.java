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
     * Free text query contacts. Return relevant matches.
     * @param keywords query keywords
     * @return List of contacts
     */
    List<Contact> query(List<String> keywords);

    /**
     * Query property of contacts. Return relevant matches.
     * @param property property name
     * @param query query string
     * @return List of contacts
     */
    List<Contact> query(String property, String query);
}

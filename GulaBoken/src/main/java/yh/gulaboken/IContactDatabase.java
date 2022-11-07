package yh.gulaboken;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Interface for contact databases.
 */
public interface IContactDatabase {
    /**
     * Create a new contact from properties.
     * Contact is persisted to file.
     *
     * @param properties Contact properties.
     * @return A contact or null.
     */
    IContact create(Map<String, String> properties);

    /**
     * Return a contact with matching id.
     *
     * @param id Contact id
     * @return A contact or null.
     */
    IContact read(long id);

    /**
     * Update a contact.
     * Contact is persisted to file.
     *
     * @param contact
     * @return True if updated, else false.
     */
    boolean update(IContact contact);

    /**
     * Update a contact.
     * Contact is persisted to file.
     *
     * @param id         Contact id
     * @param properties Properties to set for contact.
     * @return True if updated, else false.
     */
    boolean update(long id, Map<String, String> properties);

    /**
     * Delete contact with matching id.
     *
     * @param id Contact id
     * @return True if a contact was removed, else false.
     */
    boolean delete(long id);

    /**
     * Case-insensitive, free text query over all properties
     * matching all keywords.
     *
     * @param keywords Query keywords
     * @return List of matching contacts
     */
    List<IContact> queryByKeywords(Collection<String> keywords);

    /**
     * Case-insensitive, query over a specific property in contacts.
     *
     * @param property property name
     * @param query    query string
     * @return List of matching contacts
     */
    List<IContact> queryByProperty(String property, String query);
}

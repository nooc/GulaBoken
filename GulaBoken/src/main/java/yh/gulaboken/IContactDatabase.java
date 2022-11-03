package yh.gulaboken;

import java.util.List;
import java.util.Map;

/**
 *
 */
public interface IContactDatabase {
    /**
     *
     * @param properties Contact properties.
     * @return IContact instance
     */
    IContact create(Map<String,String> properties);

    /**
     *
     * @param id
     * @return
     */
    IContact read(long id);

    /**
     *
     * @param entry
     * @return
     */
    boolean update(IContact entry);

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
    List<IContact> query(List<String> keywords);

    /**
     * Case-insensitive, query over a specific property in contacts.
     * @param property property name
     * @param query query string
     * @return List of matching contacts
     */
    List<IContact> query(String property, String query);
}

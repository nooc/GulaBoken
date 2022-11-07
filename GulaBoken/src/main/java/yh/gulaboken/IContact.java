package yh.gulaboken;

import javax.validation.constraints.NotNull;
import java.util.Map;

/**
 * Interface for contacts.
 */
public interface IContact {
    /**
     * Get contact id
     *
     * @return contact id
     */
    long getContactId();

    /**
     * Get name
     *
     * @return name
     */
    @NotNull
    String getName();

    /**
     * Set name
     */
    void setName(String name);

    /**
     * Get surname
     *
     * @return surname
     */
    @NotNull
    String getSurname();

    /**
     * Set surname
     */
    void setSurname(String surname);

    /**
     * Get age
     *
     * @return age
     */
    @NotNull
    String getAge();

    /**
     * Set age
     */
    void setAge(String age);

    /**
     * Get phone number
     *
     * @return phone number(s)
     */
    @NotNull
    String getPhoneNumbers();

    /**
     * Set phone number
     */
    void setPhoneNumbers(String phoneNumbers);

    /**
     * Get street
     *
     * @return street or empty string
     */
    @NotNull
    String getStreet();

    /**
     * Set street
     */
    void setStreet(String street);

    /**
     * Get city
     *
     * @return city or empty string
     */
    @NotNull
    String getCity();

    /**
     * Set city
     */
    void setCity(String city);

    /**
     * Get zip code
     *
     * @return zip code or empty string
     */
    @NotNull
    String getZipCode();

    /**
     * set zip code
     */
    void setZipCode(String zipCode);

    /**
     * Join address entries into a single comma separated line.
     * Return value depends on set values.
     * Examples:
     * STREET, ZIP CITY
     * STREET, ZIP
     * CITY
     *
     * @return Address line
     */
    @NotNull
    String getAddressLine();

    /**
     * Get contact properties as map.
     *
     * @return Key value map.
     */
    @NotNull
    Map<String, String> asPropertiesMap();

    /**
     * Update properties from map.
     *
     * @param properties Properties source
     */
    void updateFromMap(Map<String, String> properties);
}

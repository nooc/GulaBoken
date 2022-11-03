package yh.gulaboken;

public interface IContact {
    /**
     * Get contact id
     * @return contact id
     */
    long getContactId();
    /**
     * Set contact id
     */
    void setContactId(long contactId);
    /**
     * Get name
     * @return name
     */
    String getName();
    /**
     * Set name
     */
    void setName(String name);
    /**
     * Get surname
     * @return surname
     */
    String getSurname();
    /**
     * Set surname
     */
    void setSurname(String surname);
    /**
     * Get age
     * @return age
     */
    String getAge();
    /**
     * Set age
     */
    void setAge(String age);
    /**
     * Get phone number
     * @return phone number
     */
    String getPhoneNumber();
    /**
     * Set phone number
     */
    void setPhoneNumber(String phoneNumber);
    /**
     * Get street
     * @return street
     */
    String getStreet();
    /**
     * Set street
     */
    void setStreet(String street);
    /**
     * Get city
     * @return city
     */
    String getCity();
    /**
     * Set city
     */
    void setCity(String city);
    /**
     * Get zip code
     * @return zip code
     */
    String getZipCode();
    /**
     * set zip code
     */
    void setZipCode(String zipCode);

    /**
     * Join address entries into a single comma separated line.
     * @return address line
     */
    String getAddressLine();
}

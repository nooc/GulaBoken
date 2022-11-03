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
     * Get surname
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
     * Get
     * @return contact id
     */
    String getPhoneNumber();
    /**
     * Set age
     */
    void setPhoneNumber(String phoneNumber);
    /**
     * Get street
     * @return contact id
     */
    String getStreet();

    void setStreet(String street);

    String getCity();

    void setCity(String city);

    String getZipCode();

    void setZipCode(String zipCode);

    /**
     * Join address entries into a single comma separated line.
     * @return
     */
    String getAddressLine();
}

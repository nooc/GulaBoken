package yh.gulaboken;

public interface IContact {
    long getContactId();

    void setContactId(long contactId);

    String getName();

    void setName(String name);

    String getSurname();

    void setSurname(String surname);

    String getAge();

    void setAge(String age);

    String getTelephoneNumber();

    void setTelephoneNumber(String telephoneNumber);

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

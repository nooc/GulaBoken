package yh.gulaboken.models;

import yh.gulaboken.StringUtil;

import java.util.LinkedList;

public class Address {
    private String street;
    private String city;
    private String zipCode;

    public Address() {
        this.street = "";
        this.city = "";
        this.zipCode = "";
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    /**
     * Return single line address.
     * @return
     */
    @Override
    public String toString() {
        var addressItems = new LinkedList<String>();
        if(!street.isEmpty())  { addressItems.add(street); }
        if(!zipCode.isEmpty())  { addressItems.add(zipCode); }
        if(!city.isEmpty())  { addressItems.add(city); }
        return String.join(", ", addressItems);
    }
}

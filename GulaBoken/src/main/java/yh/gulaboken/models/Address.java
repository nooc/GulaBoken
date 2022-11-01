package yh.gulaboken.models;

import yh.gulaboken.StringUtil;

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
        var builder = new StringBuilder();
        StringUtil.appendToBuilder(builder, street);
        var zipCity = String.join(zipCode, city);
        if(!zipCity.isEmpty()) {
            StringUtil.appendToBuilder(builder, zipCity);
        }
        return builder.toString();
    }
}

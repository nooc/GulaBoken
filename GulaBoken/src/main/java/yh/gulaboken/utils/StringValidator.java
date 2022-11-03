package yh.gulaboken.utils;

import java.util.regex.Pattern;

/**
 * Contains helper functions for validating strings.
 */
public class StringValidator {

    private static Pattern zipCodePattern = Pattern.compile(
            "^[0-9]{5}$"
    );
    private static Pattern phonePattern = Pattern.compile(
            "^[+]?[0-9]{3,11}$"
    );

    /**
     * Check if zipCode is a 5 digit number.
     * @param zipCode zip code string
     * @return True on success, else false
     */
    public static boolean validateZipcode(String zipCode) {
        return zipCodePattern.matcher(zipCode).matches();
    }

    /**
     * Check phone number list.
     * @param phoneNumbers zip code string
     * @return True on success, else false
     */
    public static boolean validatePhoneNumbers(String phoneNumbers) {
        var numbers = phoneNumbers.split(",\\s*");
        for(var number : numbers) {
            if(!phonePattern.matcher(number).matches()) {
                return false;
            }
        }
        return true;
    }

}

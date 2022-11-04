package yh.gulaboken.utils;

import java.util.regex.Pattern;

/**
 * Contains helper functions for validating strings.
 */
public class StringValidator {
    private static final String SPLIT_PATTERN = ",\\s+";
    private static final int MIN_AGE = 0;
    private static final int MAX_AGE = 130;

    // Zip code pattern: 5 digits.
    private static final Pattern zipCodePattern = Pattern.compile(
            "^\\s*[0-9]{5}\\s*$"
    );

    // Phone pattern: 3-11 digits with optional '+'-prefix.
    private static final Pattern phonePattern = Pattern.compile(
            "^[+]?[0-9]{3,11}$"
    );

    /**
     * Check if zip code is a 5-digit number.
     *
     * @param zipCode zip code string
     * @return True on success, else false
     */
    public static boolean validateZipcode(String zipCode) {
        return zipCodePattern.matcher(zipCode).matches();
    }

    /**
     * Check format of phone number(s).
     *
     * @param phoneNumbers zip code string
     * @return True on success, else false
     */
    public static boolean validatePhoneNumbers(String phoneNumbers) {
        var numbers = phoneNumbers.split(SPLIT_PATTERN);
        for (var number : numbers) {
            if (!phonePattern.matcher(number).matches()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Check age. Must be 0..130.
     *
     * @param stringAge age string
     * @return True on success, else false
     */
    public static boolean validateAge(String stringAge) {
        try {
            var age = Integer.parseInt(stringAge);
            return age >= MIN_AGE && age <= MAX_AGE;
        } catch (Exception ex) {
        }
        return false;
    }
}

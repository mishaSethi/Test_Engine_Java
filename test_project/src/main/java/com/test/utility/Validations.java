package com.test.utility;

public class Validations {

    public static boolean isEmpty(String value) {
        if (value != null) {
            return value.trim().equals("");
        }
        return false;
    }

    public static boolean isNumeric(String value) {
        try {
            if (value != null) {
                Integer.parseInt(value.trim());
                return true;
            }
        } catch (NumberFormatException ex) {
        }
        return false;
    }

    public static boolean onlyCharacter(String value) {
        if (value != null) {
            return value.matches("^([a-zA-z.'\\s]{2,30})$");
        }
        return false;
    }

    public static boolean isEmail(String value) {
        if (value != null) {
            return value.matches("\\w+([-+.']\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
        }
        return false;
    }

    public static boolean isSmall(String value) {
        if (value.length() < 8) {
            return true;
        }
        return false;
    }
}

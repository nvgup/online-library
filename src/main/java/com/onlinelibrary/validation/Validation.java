package com.onlinelibrary.validation;

public class Validation {
    public static boolean isPositiveInteger(String s) {
        return s != null && s.matches("\\d+");
    }
}

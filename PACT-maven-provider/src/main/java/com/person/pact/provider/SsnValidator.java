package com.person.pact.provider;

public class SsnValidator {
    public static void validate(String ssn) {
        if (ssn == null) {
            throw new ValidationException("SSN cannot be null");
        }

        if (Integer.parseInt(ssn.substring(0,1)) > 7) {
            throw new ValidationException("SSN cannot start with digit larger than 7");
        }

        if (Integer.parseInt(ssn.substring(0,2)) > 71) {
            throw new ValidationException("SSN cannot have days larger than 71");
        }
    }
}

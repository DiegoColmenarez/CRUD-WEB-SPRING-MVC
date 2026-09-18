package org.exercise7.model.service;

import org.springframework.stereotype.Component;
import java.security.SecureRandom;

@Component
public class PasswordResetCodeGenerator {

    private static final SecureRandom secureRandom = new SecureRandom();

    public String generate() {
        int number = 100000 + secureRandom.nextInt(900000);
        return String.valueOf(number);
    }
}
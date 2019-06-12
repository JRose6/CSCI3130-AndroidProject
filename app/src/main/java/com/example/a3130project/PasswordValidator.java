package com.example.a3130project;

import java.util.Scanner;

public class PasswordValidator {

    public static int validPassword(String InputPassword) {
        int rulesPassed = 5;
        int passwordLength = InputPassword.length();

        if (passwordLength < 8) {
            rulesPassed--;
        }

        if (!(InputPassword.matches(".*[0-9]{1,}.*"))) {
            rulesPassed--;
        }
        if (!(InputPassword.matches(".*[!@#$%^&*()]{1,}.*"))) {
            rulesPassed--;
        }
        if (!(InputPassword.matches("(.*[A-Z]){1,}.*"))) {
            rulesPassed--;
        }
        if (InputPassword.equalsIgnoreCase("password")) {
            rulesPassed = 0;
        }
        return rulesPassed;
    }
}
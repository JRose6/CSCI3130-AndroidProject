package com.example.a3130project;

import java.util.Scanner;

public class EmailValidator {
    public static int getEmail(String InputEmail){
        int passedRules = 0;
        if (InputEmail.matches("^(.+)@(.+)$")) {
            passedRules++;
        }
        return passedRules;
    }
}
package com.example.a3130project.Helpers;

public class PasswordValidator
{
	public enum Strength
	{
		Invalid,
		Weak,
		Medium,
		Strong,
		Excellent,
	}


	public static Strength validPassword(String inputPassword)
	{
		int rulesPassed = 0;

		if ( inputPassword.length() < 6 )
		{
			return Strength.Invalid;
		}
		if ( inputPassword.equalsIgnoreCase("password") )
		{
			return Strength.Invalid;
		}
		if ( inputPassword.length() > 8 )
		{
			rulesPassed++;
		}
		if ( inputPassword.matches(".*[0-9]{1,}.*") )
		{
			rulesPassed++;
		}
		if ( inputPassword.matches(".*[!@#$%^&*()]{1,}.*") )
		{
			rulesPassed++;
		}
		if ( inputPassword.matches("(.*[A-Z]){1,}.*") )
		{
			rulesPassed++;
		}

		switch ( rulesPassed )
		{
		case 4:
			return Strength.Excellent;
		case 3:
			return Strength.Strong;
		case 2:
			return Strength.Medium;
		case 1:
			return Strength.Weak;
		case 0:
		default:
			return Strength.Invalid;
		}
	}
}

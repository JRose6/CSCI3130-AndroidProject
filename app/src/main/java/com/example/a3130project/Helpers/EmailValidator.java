package com.example.a3130project.Helpers;

public class EmailValidator
{
	public enum Status
	{
		Valid,
		Invalid,
	}

	/**
	 * Validates if an email has an @ symbol and characters before & after
	 * @param InputEmail The email address to validate
	 * @return enumeration if the email passed
	 */
	public static Status getEmail(String InputEmail)
	{
		if ( InputEmail.matches("^(.+)@(.+)$") )
		{
			return Status.Valid;
		}
		return Status.Invalid;
	}
}

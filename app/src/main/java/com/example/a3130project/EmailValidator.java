package com.example.a3130project;

public class EmailValidator
{
	enum Status
	{
		Valid,
		Invalid,
	}


	public static Status getEmail(String InputEmail)
	{
		if ( InputEmail.matches("^(.+)@(.+)$") )
		{
			return Status.Valid;
		}
		return Status.Invalid;
	}
}

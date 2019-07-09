package com.example.a3130project.model;

import java.io.Serializable;

/**
 * Simple POJO class to hold Profile information in Firestore database
 */
public class Profile implements Serializable
{
	public String firstName;
	public String lastName;
	public String age;
	public String email;
	public boolean employee;


	public Profile()
	{
		this.firstName = "";
		this.lastName = "";
		this.age = "";
		this.email = "";
		this.employee = false;
		// empty constructor (required for FireStore)
	}


	/**
	 * Constructs a Profile Object
	 *
	 * @param firstName - User's first name (2-48 characters)
	 * @param lastName  - User's last  name (2-48 characters)
	 * @param age       - User's age
	 * @param email     - User's email
	 */
	public Profile(String firstName, String lastName, String age, String email)
	{
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.email = email;
		this.employee = false;
	}


	@Override
	public String toString()
	{
		return "Profile{" +
		       "firstName='" + firstName + '\'' +
		       ", lastName='" + lastName + '\'' +
		       ", age='" + age + '\'' +
		       ", email='" + email + '\'' +
		       '}';
	}
}

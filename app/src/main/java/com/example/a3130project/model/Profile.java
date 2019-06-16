package com.example.a3130project.model;

import java.io.Serializable;

/**
 * Simple POJO class to hold Profile information in Firestore database
 */
public class Profile implements Serializable
{

	public String   id;
	public String   firstname;
	public String   lastname;
	public String   age;
	public String   email;
	public String[] medications;
	public String[] allergies;

	public Profile()
	{
	}

	/**
	 * Constructs a Profile Object
	 *
	 * @param id          - Profile id (9-digit number)
	 * @param firstname   - User's first name (2-48 characters)
	 * @param lastname    - User's last  name (2-48 characters)
	 * @param age         - User's age
	 * @param email       - User's email
	 * @param medications - User's medication list
	 * @param allergies   - User's allergy list
	 */
	public Profile(String id,
	               String firstname,
	               String lastname,
	               String age,
	               String email,
	               String[] medications,
	               String[] allergies)
	{
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.age = age;
		this.email = email;
		this.medications = medications;
		this.allergies = allergies;
	}

	@Override
	public String toString()
	{
		return "ID: " + id + " FirstName: " + firstname + " LastName: " + lastname + " Age: " + age
				+ " Email: " + email + " Medications: " + medications + " Allergies: " + allergies;
	}
}

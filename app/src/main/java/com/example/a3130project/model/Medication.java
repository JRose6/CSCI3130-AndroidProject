package com.example.a3130project.model;

import java.io.Serializable;

/**
 * Simple POJO class to hold Medication information in Firestore database
 */
public class Medication implements Serializable
{

	public String id;
	public String name;
	public String info;

	/**
	 * Constructs a Medication Object
	 *
	 * @param id   - Medication number (9-digit number)
	 * @param name - Medication name (2-48 characters)
	 * @param info - Brief information about the medication
	 */
	public Medication(String id, String name, String info)
	{
		this.id = id;
		this.name = name;
		this.info = info;
	}

	@Override
	public String toString()
	{
		return "ID: " + id + " Name: " + name + " Info: " + info;
	}
}

package com.example.a3130project.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Simple POJO class to hold Medication information in Firestore database
 */
public class Medication implements Serializable
{

	public String            id;
	public String            name;
	public String            genName;
	public String            manufacturer;
	public ArrayList<String> sideEffects;
	public ArrayList<String> mainDiseases;


	public Medication()
	{
		// empty constructor needed for FireStore
	}


	/**
	 * Constructs a Medication Object
	 *
	 * @param id   - Medication number (9-digit number)
	 * @param name - Medication name (2-48 characters)
	 */
	public Medication(String id, String name, String genName, String manufacturer, ArrayList<String> sideEffects, ArrayList<String> mainDiseases)
	{
		this.id = id;
		this.name = name;
		this.genName = genName;
		this.manufacturer = manufacturer;
		this.sideEffects = sideEffects;
		this.mainDiseases = mainDiseases;

	}


	public String getId()
	{
		return id;
	}


	public String getName()
	{
		return name;
	}


	public String getGenName()
	{
		return genName;
	}


	public String getManufacturer()
	{
		return manufacturer;
	}


	public ArrayList<String> getSideEffects()
	{
		return sideEffects;
	}


	public ArrayList<String> getMainDiseases()
	{
		return mainDiseases;
	}


	@Override
	public String toString()
	{
		return "Medication{" + "id='" + id + '\'' + ", name='" + name + '\'' + ", genName='"
		       + genName + '\'' + ", manufacturer='" + manufacturer + '\'' + ", sideEffects="
		       + sideEffects + '\'' + "mainDiseases=" + mainDiseases + '\'' + '}';
	}
}

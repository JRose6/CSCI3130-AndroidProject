package com.example.a3130project.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Prescription implements Serializable
{
	public String              id;
	public String              medName;
	public String              medGenName;
	public String              medId;
	public String              notes;
	public String              docNotes;
	public String              dosage;
	public int				   timeOfDay;
	public Map<String, Boolean> weekdays = new HashMap<>();


	public Prescription()
	{
		this.medId = "";
		this.medName = "";
		this.medGenName = "";
		this.notes = "";
		this.docNotes = "";
		this.dosage = "";
		this.timeOfDay = 0;
		this.weekdays.put("Monday", false);
		this.weekdays.put("Tuesday", false);
		this.weekdays.put("Wednesday", false);
		this.weekdays.put("Thursday", false);
		this.weekdays.put("Friday", false);
		this.weekdays.put("Saturday", false);
		this.weekdays.put("Sunday", false);
		// Empty constructor (required for FireStore)
	}


	/**
	 * Constructs a Prescription Object
	 *
	 * @param medId - Medication id (9-digit number)
	 */
	public Prescription(String medId)
	{
		this.medId = medId;
		this.notes = "";
		this.docNotes = "";
		this.dosage = "Dosage not set";
	}


	public String getId()
	{
		return id;
	}


	public void setId(String id)
	{
		this.id = id;
	}


	public String getMedName()
	{
		return medName;
	}


	public void setMedName(String medName)
	{
		this.medName = medName;
	}


	public String getMedGenName()
	{
		return medGenName;
	}


	public void setMedGenName(String medGenName)
	{
		this.medGenName = medGenName;
	}


	public String getMedId()
	{
		return medId;
	}


	public void setMedId(String medId)
	{
		this.medId = medId;
	}


	public String getNotes()
	{
		return notes;
	}


	public void setNotes(String notes)
	{
		this.notes = notes;
	}


	public String getDocNotes()
	{
		return docNotes;
	}


	public void setDocNotes(String docNotes)
	{
		this.docNotes = docNotes;
	}


	public String getDosage()
	{
		return dosage;
	}


	public void setDosage(String dosage)
	{
		this.dosage = dosage;
	}


	@Override
	public String toString()
	{
		return "medId: " +
		       medId +
		       " Notes: " +
		       notes +
		       " Doctor's Notes: " +
		       docNotes +
		       " Dosage: " +
		       dosage;
	}

}

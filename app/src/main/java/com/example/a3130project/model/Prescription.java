package com.example.a3130project.model;

import com.google.firebase.firestore.Exclude;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


public class Prescription implements Serializable
{
	private String               id;
	private String               medName;
	private String               medGenName;
	private String               medId;
	private String               notes;
	private String               docNotes;
	private int                  dosage;
	private int                  timeOfDay;
	private int                  totalMeds;
	private int                  remainingMeds;
	private Map<String, Boolean> weekdays = new HashMap<>();


	public Prescription()
	{
		this.medId = "";
		this.medName = "";
		this.medGenName = "";
		this.notes = "";
		this.docNotes = "";
		this.dosage = 0;
		this.timeOfDay = 0;
		this.totalMeds = 0;
		this.remainingMeds = 0;
		this.weekdays.put("Monday", false);
		this.weekdays.put("Tuesday", false);
		this.weekdays.put("Wednesday", false);
		this.weekdays.put("Thursday", false);
		this.weekdays.put("Friday", false);
		this.weekdays.put("Saturday", false);
		this.weekdays.put("Sunday", false);
		// Default constructor (required for FireStore)
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
		this.dosage = 0;
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


	public int getDosage()
	{
		return dosage;
	}


	public void setDosage(int dosage)
	{
		this.dosage = dosage;
	}


	public void setMonday(Boolean b)
	{
		this.weekdays.put("Monday", b);
	}


	@Exclude
	public boolean getMonday()
	{
		return this.weekdays.get("Monday");
	}


	public void setTuesday(Boolean b)
	{
		this.weekdays.put("Tuesday", b);
	}


	@Exclude
	public boolean getTuesday()
	{
		return this.weekdays.get("Tuesday");
	}


	public void setWednesday(Boolean b)
	{
		this.weekdays.put("Wednesday", b);
	}


	@Exclude
	public boolean getWednesday()
	{
		return this.weekdays.get("Wednesday");
	}


	public void setThursday(Boolean b)
	{
		this.weekdays.put("Thursday", b);
	}


	@Exclude
	public boolean getThursday()
	{
		return this.weekdays.get("Thursday");
	}


	public void setFriday(Boolean b)
	{
		this.weekdays.put("Friday", b);
	}


	@Exclude
	public boolean getFriday()
	{
		return this.weekdays.get("Friday");
	}


	public void setSaturday(Boolean b)
	{
		this.weekdays.put("Saturday", b);
	}


	@Exclude
	public boolean getSaturday()
	{
		return this.weekdays.get("Saturday");
	}


	public void setSunday(Boolean b)
	{
		this.weekdays.put("Sunday", b);
	}


	@Exclude
	public boolean getSunday()
	{
		return this.weekdays.get("Sunday");
	}


	public Map<String, Boolean> getWeekdays()
	{
		return this.weekdays;
	}


	public int getTimeOfDay()
	{
		return timeOfDay;
	}


	public void setTimeOfDay(int timeOfDay)
	{
		this.timeOfDay = timeOfDay;
	}


	public int getTotalMeds()
	{
		return totalMeds;
	}


	public void setTotalMeds(int totalMeds)
	{
		this.totalMeds = totalMeds;
	}


	public int getRemainingMeds()
	{
		return remainingMeds;
	}


	/**
	 * Sets the remaining medication if < 0 set to 0, medication can not be less than 0
	 *
	 * @param remainingMeds The remaining medication
	 */
	public void setRemainingMeds(int remainingMeds)
	{
		if ( remainingMeds <= 0 )
		{
			this.remainingMeds = 0;
		}
		else
		{
			this.remainingMeds = remainingMeds;
		}
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
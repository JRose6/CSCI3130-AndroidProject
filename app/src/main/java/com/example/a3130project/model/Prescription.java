package com.example.a3130project.model;

import com.example.a3130project.R;
import com.google.firebase.firestore.Exclude;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Prescription implements Serializable {
    private String id;
    private String medName;
    private String medGenName;
    private String medId;
    private String notes;
    private String docNotes;
    private int dosage;
    private int timeOfDay;
    private int totalMeds;
    private int remainingMeds;
    private Map<String, Boolean> weekdays = new HashMap<>();


    public Prescription() {
        this.medId = "";
        this.medName = "";
        this.medGenName = "";
        this.notes = "";
        this.docNotes = "";
        this.dosage = 0;
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
    public Prescription(String medId) {
        this.medId = medId;
        this.notes = "";
        this.docNotes = "";
        this.dosage = 0;
    }


    public String getId() {
        return id;
    }


    public void setId(String id) {
        this.id = id;
    }


    public String getMedName() {
        return medName;
    }


    public void setMedName(String medName) {
        this.medName = medName;
    }


    public String getMedGenName() {
        return medGenName;
    }


    public void setMedGenName(String medGenName) {
        this.medGenName = medGenName;
    }


    public String getMedId() {
        return medId;
    }


    public void setMedId(String medId) {
        this.medId = medId;
    }


    public String getNotes() {
        return notes;
    }


    public void setNotes(String notes) {
        this.notes = notes;
    }


    public String getDocNotes() {
        return docNotes;
    }


    public void setDocNotes(String docNotes) {
        this.docNotes = docNotes;
    }


    public int getDosage() {
        return dosage;
    }


    public void setDosage(int dosage) {
        this.dosage = dosage;
    }

    public void setMonday(Boolean monday) {
        this.weekdays.put("Monday", monday);
    }

    @Exclude
    public boolean getMonday() {
        return this.weekdays.get("Monday");
    }

    public void setTuesday(Boolean monday) {
        this.weekdays.put("Tuesday", monday);
    }

    @Exclude
    public boolean getTuesday() {
        return this.weekdays.get("Tuesday");
    }

    public void setWednesday(Boolean monday) {
        this.weekdays.put("Wednesday", monday);
    }

    @Exclude
    public boolean getWednesday() {
        return this.weekdays.get("Wednesday");
    }

    public void setThursday(Boolean monday) {
        this.weekdays.put("Thursday", monday);
    }

    @Exclude
    public boolean getThursday() {
        return this.weekdays.get("Thursday");
    }

    public void setFriday(Boolean monday) {
        this.weekdays.put("Friday", monday);
    }

    @Exclude
    public boolean getFriday() {
        return this.weekdays.get("Friday");
    }

    public void setSaturday(Boolean monday) {
        this.weekdays.put("Saturday", monday);
    }

    @Exclude
    public boolean getSaturday() {
        return this.weekdays.get("Saturday");
    }

    public void setSunday(Boolean monday) {
        this.weekdays.put("Sunday", monday);
    }

    @Exclude
    public boolean getSunday() {
        return this.weekdays.get("Sunday");
    }

    public Map<String, Boolean> getWeekdays() {
        return this.weekdays;
    }

    public int getTimeOfDay() {
        return timeOfDay;
    }

    public void setTimeOfDay(int timeOfDay) {
        this.timeOfDay = timeOfDay;
    }

    public int getTotalMeds() {
        return totalMeds;
    }

    public void setTotalMeds(int totalMeds) {
        this.totalMeds = totalMeds;
    }

    public int getRemainingMeds() {
        return remainingMeds;
    }

    public void setRemainingMeds(int remainingMeds) {
        if (remainingMeds<=0){
            this.remainingMeds=0;
        }
        else{
            this.remainingMeds = remainingMeds;
        }
    }

    @Override
    public String toString() {
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

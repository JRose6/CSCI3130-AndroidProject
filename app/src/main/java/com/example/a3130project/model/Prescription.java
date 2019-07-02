package com.example.a3130project.model;

import java.io.Serializable;

public class Prescription implements Serializable
{

    public String medId;
    public String notes;
    public String docNotes;
    public String dosage;

    /**
     * Constructs a Prescription Object
     *
     * @param medId   - Medication id (9-digit number)
     *
     */
    public Prescription(String medId)
    {
        this.medId = medId;
        String notes ="";
        String docNotes = "";
        String dosage = "Dosage not set";
    }

    public void setDocNotes(String docNotes) {
        this.docNotes = docNotes;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public void setMedId(String medId) {
        this.medId = medId;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getMedId() {
        return medId;
    }

    public String getDocNotes() {
        return docNotes;
    }

    public String getDosage() {
        return dosage;
    }

    public String getNotes() {
        return notes;
    }

    @Override
    public String toString()
    {
        return "medId: " + medId + " Notes: " + notes + " Doctor's Notes: " + docNotes + " Dosage: " + dosage;
    }
}

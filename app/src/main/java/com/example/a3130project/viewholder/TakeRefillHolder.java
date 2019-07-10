package com.example.a3130project.viewholder;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.a3130project.Activities.MedicationDetailsActivity;
import com.example.a3130project.Activities.PrescriptionEditActivity;
import com.example.a3130project.DBHandlers;
import com.example.a3130project.R;
import com.example.a3130project.model.Medication;
import com.example.a3130project.model.Prescription;

public class TakeRefillHolder extends RecyclerView.ViewHolder {
    private TextView name;
    private TextView genName;
    private TextView dosage;
    private TextView quantity;
    private Button btnTake,btnRefill;
    private Prescription prescription;
    private Medication medication;


    public TakeRefillHolder(View view) {
        super(view);
        name = view.findViewById(R.id.viewMedNamePrescription);
        genName = view.findViewById(R.id.viewMedGenNamePrescription);
        dosage = view.findViewById(R.id.viewPrescriptionDosage);
        quantity = view.findViewById(R.id.txtQuantity);
        btnTake = view.findViewById(R.id.btnTakeMed);
        btnRefill = view.findViewById(R.id.btnRefillMed);
        View.OnClickListener clicker = new OnClicker();
        btnTake.setOnClickListener(clicker);
        btnRefill.setOnClickListener(clicker);
    }

    public void setPrescription(Prescription prescription) {

        this.prescription = prescription;
        name.setText(prescription.getMedName());
        genName.setText(prescription.getMedGenName());
        dosage.setText(String.valueOf(prescription.getDosage()));
        quantity.setText(prescription.getRemainingMeds() + "/" +
                            prescription.getTotalMeds());
    }


    class OnClicker implements View.OnClickListener
    {
        @Override
        public void onClick(View v)
        {
            switch ( v.getId() )
            {
                case R.id.btnTakeMed:
                    prescription.setRemainingMeds(prescription.getRemainingMeds()-prescription.getDosage());
                    break;
                case R.id.btnRefillMed:
                    prescription.setRemainingMeds(prescription.getTotalMeds());
                    break;
            }
            DBHandlers.prescriptionInsertUpdate(prescription);
        }

    }

    public void setMedication(Medication medication) {
        this.medication = medication;
    }
}

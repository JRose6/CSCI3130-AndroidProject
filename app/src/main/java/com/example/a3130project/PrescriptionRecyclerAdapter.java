package com.example.a3130project;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.example.a3130project.model.Prescription;
import com.example.a3130project.viewholder.PrescriptionHolder;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class PrescriptionRecyclerAdapter extends FirestoreRecyclerAdapter<Prescription, PrescriptionHolder>
{
	public PrescriptionRecyclerAdapter(@NonNull FirestoreRecyclerOptions<Prescription> options)
	{
		super(options);
	}

	@Override
	protected void onBindViewHolder(@NonNull PrescriptionHolder prescriptionHolder,
	                                int i,
	                                @NonNull Prescription prescription)
	{
		prescriptionHolder.setPrescription(prescription);
		prescriptionHolder.name.setText(prescription.getMedName());
		prescriptionHolder.genName.setText(prescription.getMedGenName());
		prescriptionHolder.dosage.setText(prescription.getDosage());
	}


	@NonNull
	@Override
	public PrescriptionHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
	{
		View v = LayoutInflater.from(parent.getContext())
		                       .inflate(R.layout.prescription_card, parent, false);
		return new PrescriptionHolder(v);
	}
}

package com.example.a3130project.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.example.a3130project.R;
import com.example.a3130project.model.Prescription;
import com.example.a3130project.viewholder.PrescriptionHolder;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class PrescriptionAdapter extends FirestoreRecyclerAdapter<Prescription, PrescriptionHolder>
{
	private static boolean editable = true;

	public PrescriptionAdapter(@NonNull FirestoreRecyclerOptions<Prescription> options)
	{
		super(options);
	}


	@Override
	protected void onBindViewHolder(@NonNull PrescriptionHolder prescriptionHolder,
	                                int i,
	                                @NonNull Prescription prescription)
	{
		prescriptionHolder.setEditable(editable);
		prescriptionHolder.setPrescription(prescription);
	}


	@NonNull
	@Override
	public PrescriptionHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
	{
		View v = LayoutInflater.from(parent.getContext())
		                       .inflate(R.layout.prescription_card, parent, false);
		return new PrescriptionHolder(v);
	}

	public static void setEditable(boolean edit)
	{
		editable = edit;
	}
}

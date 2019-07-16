package com.example.a3130project.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.example.a3130project.R;
import com.example.a3130project.model.Medication;
import com.example.a3130project.model.Prescription;
import com.example.a3130project.viewholder.PrescriptionHolder;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

public class PrescriptionRecyclerAdapter extends FirestoreRecyclerAdapter<Prescription, PrescriptionHolder>
{
	public PrescriptionRecyclerAdapter(@NonNull FirestoreRecyclerOptions<Prescription> options)
	{
		super(options);
	}


	@Override
	protected void onBindViewHolder(@NonNull final PrescriptionHolder prescriptionHolder,
	                                int i,
	                                @NonNull Prescription prescription)
	{
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
}

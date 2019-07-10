package com.example.a3130project.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.example.a3130project.R;
import com.example.a3130project.model.Prescription;
import com.example.a3130project.viewholder.PrescriptionHolder;
import com.example.a3130project.viewholder.TakeRefillHolder;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class TakeRefillAdapter extends FirestoreRecyclerAdapter<Prescription, TakeRefillHolder>
{
	public TakeRefillAdapter(@NonNull FirestoreRecyclerOptions<Prescription> options)
	{
		super(options);
	}

	@Override
	protected void onBindViewHolder(@NonNull TakeRefillHolder prescriptionHolder,
	                                int i,
	                                @NonNull Prescription prescription)
	{
		prescriptionHolder.setPrescription(prescription);
	}


	@NonNull
	@Override
	public TakeRefillHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
	{
		View v = LayoutInflater.from(parent.getContext())
		                       .inflate(R.layout.take_refill_card, parent, false);
		return new TakeRefillHolder(v);
	}
}

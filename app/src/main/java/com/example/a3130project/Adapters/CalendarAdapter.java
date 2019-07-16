package com.example.a3130project.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.example.a3130project.R;
import com.example.a3130project.model.Prescription;
import com.example.a3130project.viewholder.CalendarHolder;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class CalendarAdapter extends FirestoreRecyclerAdapter<Prescription, CalendarHolder>
{


	public CalendarAdapter(@NonNull
			                       FirestoreRecyclerOptions<Prescription> options)
	{
		super(options);
	}

	@Override
	protected void onBindViewHolder(
			@NonNull CalendarHolder CalendarHolder, int i,
			@NonNull Prescription prescription)
	{
		CalendarHolder.setPrescription(prescription);

		CalendarHolder.textViewMedicationName.setText(prescription.getMedName());
		CalendarHolder.textViewUserInfo.setText(prescription.getNotes());



		CalendarHolder.textViewDosageInfo.setText(String.valueOf(prescription.getDosage()));
	}
	@NonNull
	@Override
	public CalendarHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
	{
		View v = LayoutInflater.from(parent.getContext())
		                       .inflate(R.layout.prescription_item, parent, false);
		return new CalendarHolder(v);
	}


}

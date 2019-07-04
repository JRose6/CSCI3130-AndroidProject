package com.example.a3130project;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a3130project.model.Prescription;
import com.example.a3130project.viewholder.CalendarHolder;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import org.w3c.dom.Text;

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
		CalendarHolder.textViewDoctorInfo.setText(prescription.getDocNotes());
		CalendarHolder.textViewDosageInfo.setText(prescription.getDosage());
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

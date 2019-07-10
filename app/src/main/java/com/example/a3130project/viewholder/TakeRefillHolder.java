package com.example.a3130project.viewholder;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.a3130project.R;
import com.example.a3130project.model.Prescription;

public class CalendarHolder extends RecyclerView.ViewHolder {

	public TextView textViewMedicationName;
	public TextView textViewDosageInfo;
	public TextView textViewDoctorInfo;
	private Prescription prescription;

	public CalendarHolder(View view)
	{
		super(view);

		textViewMedicationName = itemView.findViewById(R.id.MedicationView);
		textViewDosageInfo = itemView.findViewById(R.id.DosageView);
		textViewDoctorInfo = itemView.findViewById(R.id.DoctorInfoView);
	}

	public void setPrescription(Prescription prescription)
	{
		this.prescription = prescription;
	}
}

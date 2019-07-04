package com.example.a3130project.viewholder;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.a3130project.R;

public class CalendarHolder extends RecyclerView.ViewHolder {

	TextView textViewMedicationName;
	TextView textViewDosageInfo;
	TextView textViewDoctorInfo;

	public CalendarHolder(View view)
	{
		super(view);

		textViewMedicationName = itemView.findViewById(R.id.MedicationView);
		textViewDosageInfo = itemView.findViewById(R.id.DosageView);
		textViewDoctorInfo = itemView.findViewById(R.id.DoctorInfoView);
	}
}

package com.example.a3130project.viewholder;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.a3130project.R;
import com.example.a3130project.model.Prescription;

public class CalendarHolder extends RecyclerView.ViewHolder
{
	private Prescription prescription;

	public TextView textViewMedicationName;
	public TextView textViewDosageInfo;
	public TextView textViewUserInfo;
	public TextView textViewTOD;


	public CalendarHolder(View view)
	{
		super(view);

		textViewMedicationName = itemView.findViewById(R.id.MedicationView);
		textViewDosageInfo = itemView.findViewById(R.id.DosageView);
		textViewUserInfo = itemView.findViewById(R.id.UserInfoView);
		textViewTOD = itemView.findViewById(R.id.TODInfo);
	}


	public void setPrescription(Prescription prescription)
	{
		this.prescription = prescription;
	}
}

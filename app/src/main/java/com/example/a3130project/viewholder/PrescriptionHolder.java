package com.example.a3130project.viewholder;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.a3130project.R;

public class PrescriptionHolder extends RecyclerView.ViewHolder
{
	public TextView name;
	public TextView genName;
	public TextView dosage;
	public Button   buttonEditPrescription;


	public PrescriptionHolder(View view)
	{
		super(view);
		name = view.findViewById(R.id.viewMedNamePrescription);
		genName = view.findViewById(R.id.viewMedGenNamePrescription);
		dosage = view.findViewById(R.id.viewPrescriptionDosage);
		buttonEditPrescription = view.findViewById(R.id.buttonEditPrescription);
	}
}

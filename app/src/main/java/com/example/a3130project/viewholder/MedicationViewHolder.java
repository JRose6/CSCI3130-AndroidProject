package com.example.a3130project.viewholder;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.a3130project.R;

public class MedicationViewHolder extends RecyclerView.ViewHolder
{
	public TextView name;
	public TextView genName;
	public Button   buttonDetails;
	public Button   buttonAddPrescription;

	public MedicationViewHolder(View view)
	{
		super(view);
		name = view.findViewById(R.id.viewMedName);
		genName = view.findViewById(R.id.viewMedGenName);
		buttonDetails = view.findViewById(R.id.buttonDetails);
		buttonAddPrescription = view.findViewById(R.id.buttonAddPrescription);
	}
}

package com.example.a3130project.viewholder;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.a3130project.Activities.MedicationDetailsActivity;
import com.example.a3130project.Activities.PrescriptionEditActivity;
import com.example.a3130project.R;
import com.example.a3130project.model.Medication;
import com.example.a3130project.model.Prescription;

public class MedicationHolder extends RecyclerView.ViewHolder
{
	public  TextView   name;
	public  TextView   genName;
	private Button     buttonAddPrescription;
	private Medication medication;
	private Button     buttonMedicationDetails;


	public MedicationHolder(View view)
	{
		super(view);
		name = view.findViewById(R.id.viewMedName);
		genName = view.findViewById(R.id.viewMedGenName);
		buttonAddPrescription = view.findViewById(R.id.buttonAddPrescription);
		buttonMedicationDetails = view.findViewById(R.id.buttonMedicationDetails);
		buttonAddPrescription.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				Intent intent = new Intent(v.getContext(), PrescriptionEditActivity.class);
				intent.putExtra("medication", medication);
				intent.putExtra("prescription", new Prescription());
				v.getContext().startActivity(intent);
			}
		});
		buttonMedicationDetails.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				Intent intent = new Intent(v.getContext(), MedicationDetailsActivity.class);
				intent.putExtra("medication", medication);
				v.getContext().startActivity(intent);
			}
		});
	}


	public void setMedication(Medication medication)
	{
		this.medication = medication;
	}
}

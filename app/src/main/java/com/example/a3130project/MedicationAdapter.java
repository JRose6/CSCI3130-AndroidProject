package com.example.a3130project;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.example.a3130project.model.Medication;
import com.example.a3130project.viewholder.MedicationHolder;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class MedicationAdapter extends FirestoreRecyclerAdapter<Medication, MedicationHolder>
{
	public MedicationAdapter(@NonNull FirestoreRecyclerOptions<Medication> options)
	{
		super(options);
	}


	@Override
	protected void onBindViewHolder(@NonNull MedicationHolder medicationHolder,
	                                int i,
	                                @NonNull Medication medication)
	{
		medicationHolder.genName.setText(medication.genName);
		medicationHolder.name.setText(medication.name);

		medicationHolder.buttonAddPrescription.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				/*Intent intent = new Intent(AllMedications.this, PrescriptionDetails.class);
				intent.putExtra("medication", model);
				intent.putExtra("actionType", "add");
				startActivity(intent);*/
			}
		});
	}


	@NonNull
	@Override
	public MedicationHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
	{
		View v = LayoutInflater.from(parent.getContext())
		                       .inflate(R.layout.medication_card, parent, false);
		return new MedicationHolder(v);
	}
}

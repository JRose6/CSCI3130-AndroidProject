package com.example.a3130project;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.example.a3130project.model.Medication;
import com.example.a3130project.viewholder.MedicationViewHolder;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class MedicationAdapter extends FirestoreRecyclerAdapter<Medication, MedicationViewHolder>
{
	public MedicationAdapter(@NonNull FirestoreRecyclerOptions options)
	{
		super(options);
	}

	@Override
	protected void onBindViewHolder(@NonNull MedicationViewHolder medicationHolder,
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
				/*Intent intent = new Intent(AllMeds.this, PrescriptionDetails.class);
				intent.putExtra("medication", model);
				intent.putExtra("actionType", "add");
				startActivity(intent);*/
			}
		});
	}

	@NonNull
	@Override
	public MedicationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
	{
		View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.medication_card, parent, false);
		return new MedicationViewHolder(v);
	}
}

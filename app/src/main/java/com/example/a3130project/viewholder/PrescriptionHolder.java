package com.example.a3130project.viewholder;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.a3130project.PrescriptionEdit;
import com.example.a3130project.R;
import com.example.a3130project.model.Medication;
import com.example.a3130project.model.Prescription;

public class PrescriptionHolder extends RecyclerView.ViewHolder
{
	public  TextView     name;
	public  TextView     genName;
	public  TextView     dosage;
	public  Button       buttonEditPrescription;
	private Prescription prescription;
	private Medication   medication;


	public PrescriptionHolder(View view)
	{
		super(view);
		name = view.findViewById(R.id.viewMedNamePrescription);
		genName = view.findViewById(R.id.viewMedGenNamePrescription);
		dosage = view.findViewById(R.id.viewPrescriptionDosage);
		buttonEditPrescription = view.findViewById(R.id.buttonEditPrescription);

		buttonEditPrescription.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				Intent intent = new Intent(v.getContext(), PrescriptionEdit.class);
				intent.putExtra("medication", medication);
				intent.putExtra("prescription", prescription);
				v.getContext().startActivity(intent);
			}
		});
	}


	public void setPrescription(Prescription prescription)
	{
		this.prescription = prescription;
	}


	public void setMedication(Medication medication)
	{
		this.medication = medication;
	}
}

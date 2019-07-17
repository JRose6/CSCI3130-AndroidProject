package com.example.a3130project.viewholder;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.a3130project.Activities.PrescriptionEditActivity;
import com.example.a3130project.Helpers.DBHandlers;
import com.example.a3130project.Helpers.PrescriptionHelper;
import com.example.a3130project.R;
import com.example.a3130project.model.Medication;
import com.example.a3130project.model.Prescription;

public class PrescriptionHolder extends RecyclerView.ViewHolder
{
	private static boolean editable = false;

	private TextView     name;
	private TextView     genName;
	private TextView     dosage;
	private TextView     quantity;
	private Button       buttonTake;
	private Button       buttonRefill;
	private Button       buttonEdit;
	private Prescription prescription;
	private Medication   medication;
	private Context      context;


	public PrescriptionHolder(View view)
	{
		super(view);
		context = view.getContext();
		name = view.findViewById(R.id.viewMedNamePrescription);
		genName = view.findViewById(R.id.viewMedGenNamePrescription);
		dosage = view.findViewById(R.id.viewPrescriptionDosage);
		quantity = view.findViewById(R.id.viewQuantity);
		buttonTake = view.findViewById(R.id.buttonTakeMed);
		buttonRefill = view.findViewById(R.id.buttonRefillMed);
		buttonEdit = view.findViewById(R.id.buttonEditPrescription);
		View.OnClickListener clicker = new OnClicker();
		buttonTake.setOnClickListener(clicker);
		buttonRefill.setOnClickListener(clicker);
		buttonEdit.setVisibility(( editable ? View.VISIBLE : View.INVISIBLE ));
	}


	public void setPrescription(Prescription prescription)
	{
		this.prescription = prescription;
		name.setText(prescription.getMedName());
		genName.setText(prescription.getMedGenName());
		dosage.setText(String.valueOf(prescription.getDosage()));
		quantity.setText(prescription.getRemainingMeds() + "/" +
		                 prescription.getTotalMeds());
	}


	class OnClicker implements View.OnClickListener
	{
		@Override
		public void onClick(View v)
		{
			switch ( v.getId() )
			{
			case R.id.buttonTakeMed:
				PrescriptionHelper.takeDosage(prescription, context);
				break;
			case R.id.buttonRefillMed:
				PrescriptionHelper.refill(prescription);
				break;
			case R.id.buttonEditPrescription:
				Intent intent = new Intent(v.getContext(), PrescriptionEditActivity.class);
				intent.putExtra("medication", medication);
				intent.putExtra("prescription", new Prescription());
				v.getContext().startActivity(intent);
				break;
			}
			DBHandlers.prescriptionInsertUpdate(prescription);
		}
	}


	public void setMedication(Medication medication)
	{
		this.medication = medication;
	}


	public void setEditable(Boolean allowEdit)
	{
		editable = allowEdit;
		if ( allowEdit )
			buttonEdit.setVisibility(View.VISIBLE);
		else
			buttonEdit.setVisibility(View.INVISIBLE);
	}
}

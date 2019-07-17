package com.example.a3130project.viewholder;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
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
	private TextView     txtM,txtT,txtW,txtR,txtF,txtS1,txtS2;
	private TextView     txtTime;
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
		txtM = view.findViewById(R.id.txtM);
		txtT = view.findViewById(R.id.txtT);
		txtW = view.findViewById(R.id.txtW);
		txtR = view.findViewById(R.id.txtR2);
		txtF = view.findViewById(R.id.txtF2);
		txtS1 = view.findViewById(R.id.txtS1);
		txtS2 = view.findViewById(R.id.txtS2);
		txtTime = view.findViewById(R.id.txtMedTime);
		buttonTake = view.findViewById(R.id.buttonTakeMed);
		buttonRefill = view.findViewById(R.id.buttonRefillMed);
		buttonEdit = view.findViewById(R.id.buttonEditPrescription);

		View.OnClickListener clicker = new OnClicker();
		buttonTake.setOnClickListener(clicker);
		buttonRefill.setOnClickListener(clicker);
		buttonEdit.setOnClickListener(clicker);
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
		Typeface boldTypeface = Typeface.defaultFromStyle(Typeface.BOLD);
		if (prescription.getMonday()){
			txtM.setTypeface(boldTypeface);
		}

		if (prescription.getTuesday()){
			txtT.setTypeface(boldTypeface);
		}

		if (prescription.getWednesday()){
			txtW.setTypeface(boldTypeface);
		}

		if (prescription.getThursday()){
			txtR.setTypeface(boldTypeface);
		}

		if (prescription.getFriday()){
			txtF.setTypeface(boldTypeface);
		}

		if (prescription.getSaturday()){
			txtS1.setTypeface(boldTypeface);
		}

		if (prescription.getSunday()){
			txtS2.setTypeface(boldTypeface);
		}
		int    time    = prescription.getTimeOfDay() / ( 60 * 1000 );
		String timeStr = ( (int) Math.floor(time / 60) ) + ":" + ( (int) ( time % 60 ) );
		txtTime.setText(timeStr);
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
				DBHandlers.prescriptionInsertUpdate(prescription);
				break;
			case R.id.buttonRefillMed:
				PrescriptionHelper.refill(prescription);
				DBHandlers.prescriptionInsertUpdate(prescription);
				break;
			case R.id.buttonEditPrescription:
				Intent intent = new Intent(v.getContext(), PrescriptionEditActivity.class);
				intent.putExtra("medication", medication);
				intent.putExtra("prescription", prescription);
				v.getContext().startActivity(intent);
				break;
			}
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

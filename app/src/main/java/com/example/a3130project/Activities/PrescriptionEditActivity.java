package com.example.a3130project.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a3130project.DBHandlers;
import com.example.a3130project.R;
import com.example.a3130project.Helpers.ToolBarCreator;
import com.example.a3130project.model.Medication;
import com.example.a3130project.model.Prescription;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class PrescriptionEditActivity extends AppCompatActivity
{
	private Intent            intent;
	private Prescription      prescription;
	private Medication        medication;

	private TextView viewMedName;
	private EditText editDosage;
	private EditText editUserNotes;
	private EditText editDocNotes;
	private EditText editTimeOfDay;
	private EditText editInitialQuantity;
	private Button   buttonSaveChanges;
	private Button   buttonCancel;
	private CheckBox chkMon, chkTue, chkWed, chkThu, chkFri, chkSat, chkSun;


	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_prescription_edit);
		chkMon = findViewById(R.id.chkMonday);
		chkTue = findViewById(R.id.chkTuesday);
		chkWed = findViewById(R.id.chkWednesday);
		chkThu = findViewById(R.id.chkThursday);
		chkFri = findViewById(R.id.chkFriday);
		chkSat = findViewById(R.id.chkSaturday);
		chkSun = findViewById(R.id.chkSunday);

		viewMedName = findViewById(R.id.viewMedName);
		editDosage = findViewById(R.id.editDosage);
		editUserNotes = findViewById(R.id.editUserNotes);
		editDocNotes = findViewById(R.id.editDocNotes);

		editTimeOfDay = findViewById(R.id.editTimeOfDay);
		editInitialQuantity = findViewById(R.id.editInitialAmount);
		ToolBarCreator.createToolbar(this, true, true);
		ToolBarCreator.createBottomNav(this);
		// TODO: Doctors & pharmacists should be able to edit the 'dr.notes' field.
		// if ( user.type != Profile.Type.Doctor && user.type != Profile.Type.Pharmacist )
		editDocNotes.setEnabled(false);

		buttonSaveChanges = findViewById(R.id.buttonSavePrescriptionChanges);
		buttonCancel = findViewById(R.id.buttonCancelPrescriptionEdit);

		buttonSaveChanges.setOnClickListener(new OnClicker());
		buttonCancel.setOnClickListener(new OnClicker());
	}


	@Override
	protected void onStart()
	{
		super.onStart();

		intent = getIntent();
		if ( intent == null ) // Must be called with an Intent()
		{
			finish();
			return;
		}

		prescription = (Prescription) intent.getSerializableExtra("prescription");
		if ( prescription == null ) // Must have a valid prescription object
		{
			finish();
			return;
		}

		// Check for a medication object... if none, check for 'medication_name'
		medication = (Medication) intent.getSerializableExtra("medication");
		if ( medication != null ) // Must have a valid medication object
		{
			viewMedName.setText("No medication given... error");
		}
		else
		{
			finish();
			return;
		}

		editDosage.setText(Integer.toString(prescription.getDosage()));
		editUserNotes.setText(prescription.getNotes());
		editDocNotes.setText(prescription.getDocNotes());
		editInitialQuantity.setText(Integer.toString(prescription.getTotalMeds()));
		if ( prescription.getTimeOfDay() != 0 )
		{
			int    time    = prescription.getTimeOfDay() / ( 60 * 1000 );
			String timeStr = ( (int) Math.floor(time / 60) ) + ":" + ( (int) ( time % 60 ) );
			editTimeOfDay.setText(timeStr);
		}
		chkMon.setChecked(prescription.getMonday());
		chkTue.setChecked(prescription.getTuesday());
		chkWed.setChecked(prescription.getWednesday());
		chkThu.setChecked(prescription.getThursday());
		chkFri.setChecked(prescription.getFriday());
		chkSat.setChecked(prescription.getSaturday());
		chkSun.setChecked(prescription.getSunday());
	}


	public class OnClicker implements View.OnClickListener
	{
		@Override
		public void onClick(View v)
		{
			switch ( v.getId() )
			{
			case R.id.buttonSavePrescriptionChanges:
				updateDatabaseEntry();
				finish();
				break;
			case R.id.buttonCancelPrescriptionEdit:
				finish();
				break;
			}
		}
	}


	private void updateDatabaseEntry()
	{
		prescription.setDosage(Integer.parseInt(editDosage.getText().toString()));
		prescription.setNotes(editUserNotes.getText().toString());
		prescription.setRemainingMeds(Integer.parseInt(editInitialQuantity.getText().toString()));
		prescription.setTotalMeds(Integer.parseInt(editInitialQuantity.getText().toString()));
		String   timeOfDay = editTimeOfDay.getText().toString();
		String[] timeSplit = timeOfDay.split("[:]");
		int      time      = Integer.parseInt(timeSplit[0]) * 60 + Integer.parseInt(timeSplit[1]);
		time = time * 60 * 1000; // Milliseconds
		prescription.setTimeOfDay(time);
		prescription.setMonday(chkMon.isChecked());
		prescription.setTuesday(chkTue.isChecked());
		prescription.setWednesday(chkWed.isChecked());
		prescription.setThursday(chkThu.isChecked());
		prescription.setFriday(chkFri.isChecked());
		prescription.setSaturday(chkSat.isChecked());
		prescription.setSunday(chkSun.isChecked());
		if ( medication != null )
		{
			prescription.setMedId(medication.id);
			prescription.setMedName(medication.name);
			prescription.setMedGenName(medication.genName);
		}
		DBHandlers.prescriptionInsertUpdate(prescription);
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		return ToolBarCreator.createMenu(this, menu, true);
	}
}

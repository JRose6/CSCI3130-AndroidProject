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
	private FirebaseFirestore database = FirebaseFirestore.getInstance();
	private Intent            intent;
	private Prescription      prescription;
	private Medication        medication;

	private TextView viewMedName;
	private EditText editDosage;
	private EditText editUserNotes;
	private EditText editDocNotes;
	private EditText editTimeOfDay;
	private Button   buttonSaveChanges;
	private Button   buttonMedDetails;
	private Button   buttonCancel;
	private CheckBox chkMon, chkTue,chkWed,chkThu, chkFri,chkSat,chkSun;

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
		ToolBarCreator.createToolbar(this,true,true);
		ToolBarCreator.createBottomNav(this);
		// TODO: Doctors & pharmacists should be able to edit the 'dr.notes' field.
		// if ( user.type != Profile.Type.Doctor && user.type != Profile.Type.Pharmacist )
		editDocNotes.setEnabled(false);

		buttonSaveChanges = findViewById(R.id.buttonSavePrescriptionChanges);
		buttonMedDetails = findViewById(R.id.buttonMedicationDetails);
		buttonCancel = findViewById(R.id.buttonCancelPrescriptionEdit);

		buttonSaveChanges.setOnClickListener(new OnClicker());
		buttonMedDetails.setOnClickListener(new OnClicker());
		buttonCancel.setOnClickListener(new OnClicker());
	}


	@Override
	protected void onStart()
	{
		super.onStart();

		intent = getIntent();
		if ( intent == null ) // Must be called with an Intent()
		{
			toastSh("No Intent?");
			finish();
			return;
		}

		prescription = (Prescription) intent.getSerializableExtra("prescription");
		if ( prescription == null ) // Must have a valid prescription object
		{
			toastSh("Didn't pass a prescription");
			finish();
			return;
		}

		// Check for a medication object... if none, check for 'medication_name'
		medication = (Medication) intent.getSerializableExtra("medication");
		String medication_name = (String) intent.getSerializableExtra("medication_name");
		if ( medication != null ) // Must have a valid medication object
		{
			viewMedName.setText(medication.name);
		}
		else if ( medication_name != null )
		{
			viewMedName.setText(medication_name);
		}
		else
		{
			toastSh("Didn't pass a medication");
			finish();
			return;
		}

		editDosage.setText(prescription.dosage);
		editUserNotes.setText(prescription.notes);
		editDocNotes.setText(prescription.docNotes);
		if (prescription.timeOfDay!=0){
			int time = prescription.timeOfDay/(60*1000);
			String timeStr = ((int)Math.floor(time / 60))+":"+((int)(Math.floor(time / 60) % 60));
			editTimeOfDay.setText(timeStr);
		}
		chkMon.setChecked(prescription.weekdays.get("Monday"));
		chkTue.setChecked(prescription.weekdays.get("Tuesday"));
		chkWed.setChecked(prescription.weekdays.get("Wednesday"));
		chkThu.setChecked(prescription.weekdays.get("Thursday"));
		chkFri.setChecked(prescription.weekdays.get("Friday"));
		chkSat.setChecked(prescription.weekdays.get("Saturday"));
		chkSun.setChecked(prescription.weekdays.get("Sunday"));
	}


	public class OnClicker implements View.OnClickListener
	{
		@Override
		public void onClick(View v)
		{
			switch ( v.getId() )
			{
			case R.id.buttonSavePrescriptionChanges:
				toastSh("Save");
				updateDatabaseEntry();
				finish();
				break;
			case R.id.buttonMedicationDetails:
				Intent intent = new Intent(PrescriptionEditActivity.this, MedicationDetailsActivity.class);
				intent.putExtra("medication", medication);
				startActivity(intent);
				break;
			case R.id.buttonCancelPrescriptionEdit:
				finish();
				break;
			}
		}
	}


	private void preparePrescriptionForEntryIntoDatabase()
	{
		prescription.dosage = editDosage.getText().toString();
		prescription.notes = editUserNotes.getText().toString();
		String timeOfDay = editTimeOfDay.getText().toString();
		String[] timeSplit = timeOfDay.split("[:]");
		int time = Integer.parseInt(timeSplit[0])*60 + Integer.parseInt(timeSplit[1]);
		time = time*60*1000;//Milliseconds
		prescription.timeOfDay = time;
		prescription.weekdays.put("Monday",chkMon.isChecked());
		prescription.weekdays.put("Tuesday",chkTue.isChecked());
		prescription.weekdays.put("Wednesday",chkWed.isChecked());
		prescription.weekdays.put("Thursday",chkThu.isChecked());
		prescription.weekdays.put("Friday",chkFri.isChecked());
		prescription.weekdays.put("Saturday",chkSat.isChecked());
		prescription.weekdays.put("Sunday",chkSun.isChecked());
		if ( medication != null ) {
			prescription.medId = medication.id;
			prescription.medName = medication.name;
			prescription.medGenName = medication.genName;
		}
	}


	private void updateDatabaseEntry()
	{
		String              profileId         = FirebaseAuth.getInstance().getUid();
		String              prescriptionsPath = "profiles/" + profileId + "/prescriptions";
		CollectionReference prescriptionsRef  = database.collection(prescriptionsPath);
		DocumentReference   docRef;

		if ( prescription.id == null || prescription.id.equals("null") )
		{
			docRef = prescriptionsRef.document();
			prescription.id = docRef.getId();
		}
		else
		{
			docRef = prescriptionsRef.document(prescription.id);
		}

		preparePrescriptionForEntryIntoDatabase();

		docRef.set(prescription).addOnSuccessListener(new OnSuccessListener<Void>()
		{
			@Override
			public void onSuccess(Void aVoid)
			{
				Log.w("updateDatabaseEntry().onSuccessListener().onSuccess()",
				      "oh fuck yea that's goooood shit.");
			}
		}).addOnFailureListener(new OnFailureListener()
		{
			@Override
			public void onFailure(Exception e)
			{
				Log.w("updateDatabaseEntry().onFailureListener().onFailure()",
				      "Error updating document...", e);
				toastSh("There was a problem, Try Again" + e);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		return ToolBarCreator.createMenu(this, menu,true);
	}

	/**
	 * Generates a short toast message
	 *
	 * @param message - The message to display in the toast
	 */
	private void toastSh(String message)
	{
		Toast.makeText(PrescriptionEditActivity.this, message, Toast.LENGTH_SHORT).show();
	}
}

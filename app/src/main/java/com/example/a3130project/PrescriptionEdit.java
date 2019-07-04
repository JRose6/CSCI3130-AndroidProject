package com.example.a3130project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a3130project.model.Medication;
import com.example.a3130project.model.Prescription;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class PrescriptionEdit extends AppCompatActivity
{
	private FirebaseFirestore database = FirebaseFirestore.getInstance();
	private Intent            intent;
	private Prescription      prescription;
	private Medication        medication;

	private TextView viewMedName;
	private EditText editDosage;
	private EditText editUserNotes;
	private EditText editDocNotes;
	private Button   buttonSaveChanges;
	private Button   buttonMedDetails;
	private Button   buttonCancel;


	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_prescription_edit);

		viewMedName = findViewById(R.id.viewMedName);
		editDosage = findViewById(R.id.editDosage);
		editUserNotes = findViewById(R.id.editUserNotes);
		editDocNotes = findViewById(R.id.editDocNotes);

		buttonSaveChanges = findViewById(R.id.buttonSavePrescriptionChanges);
		buttonMedDetails = findViewById(R.id.buttonMedicationDetails);
		buttonCancel = findViewById(R.id.buttonCancelPrescriptionEdit);

//		buttonSaveChanges.setOnClickListener(new View.OnClickListener()
//		{
//			@Override
//			public void onClick(View v)
//			{
//				toastSh("Save");
//				updateDatabase();
//			}
//		});
//		buttonMedDetails.setOnClickListener(new View.OnClickListener()
//		{
//			@Override
//			public void onClick(View v)
//			{
//				Intent intent = new Intent(PrescriptionEdit.this, MedicationDetails.class);
//				intent.putExtra("medication", medication);
//				startActivity(intent);
//			}
//		});
//		buttonCancel.setOnClickListener(new View.OnClickListener()
//		{
//			@Override
//			public void onClick(View v)
//			{
//				finish();
//			}
//		});
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
		}

		prescription = (Prescription) intent.getSerializableExtra("prescription");
		if ( prescription == null ) // Must have a valid prescription object
		{
			toastSh("Didn't pass a prescription");
			finish();
		}

		medication = (Medication) intent.getSerializableExtra("medication");
		if ( medication == null ) // Must have a valid medication object
		{
			toastSh("Didn't pass a medication");
			finish();
		}

		viewMedName.setText(medication.name);
		editDosage.setText(prescription.dosage);
		editUserNotes.setText(prescription.notes);
		editDocNotes.setText(prescription.docNotes);
	}


	private void updateDatabase()
	{
		String              profileId         = FirebaseAuth.getInstance().getUid();
		String              prescriptionsPath = "profiles/" + profileId + "/prescriptions";
		CollectionReference prescriptionsRef  = database.collection(prescriptionsPath);
		DocumentReference   docRef;

		if ( prescription.id == "null" )
		{
			docRef = prescriptionsRef.document();
			prescription.id = docRef.getId();
		}
		else
		{
			docRef = prescriptionsRef.document(prescription.id);
		}

		prescription.dosage = editDosage.getText().toString();
		prescription.notes = editUserNotes.getText().toString();

		docRef.set(prescription).addOnFailureListener(new OnFailureListener()
		{
			@Override
			public void onFailure(Exception e)
			{
				toastSh("error adding to database " + e);
			}
		});
	}


	/**
	 * Generates a short toast message
	 *
	 * @param message - The message to display in the toast
	 */
	private void toastSh(String message)
	{
		Toast.makeText(PrescriptionEdit.this, message, Toast.LENGTH_SHORT).show();
	}
}

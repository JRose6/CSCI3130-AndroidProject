package com.example.a3130project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a3130project.model.Medication;
import com.example.a3130project.model.Prescription;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class PrescriptionDetails extends AppCompatActivity
{

	private Intent            intent;
	private Medication        med;
	private String            action;
	private TextView          medName;
	private TextView          dosage;
	private TextView          notes;
	private TextView          docNotes;
	private Prescription      prescription;
	private FirebaseFirestore database;


	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_prescription_details);

		intent = getIntent();
		med = (Medication) intent.getSerializableExtra("medication");
		action = (String) intent.getSerializableExtra("actionType");

		medName = findViewById(R.id.medName);
		dosage = findViewById(R.id.dosage);
		notes = findViewById(R.id.notes);
		docNotes = findViewById(R.id.docNotes);

		database = FirebaseFirestore.getInstance();

		switch ( action )
		{
		case "add":
			// TODO: Add prescription object
			medName.setText(med.name);
			dosage.setText("@");
			notes.setText("@");
			docNotes.setText("@");
			break;
		case "edit":
			prescription = (Prescription) intent.getSerializableExtra("prescription");
			medName.setText(med.name);
			dosage.setText(prescription.dosage);
			notes.setText(prescription.notes);
			docNotes.setText(prescription.docNotes);
			break;
		}
	}


	public class OnClicker implements View.OnClickListener
	{
		@Override
		public void onClick(View v)
		{
			if ( v.getId() == R.id.buttonAddEditPresciption )
			{
				updateDatabase();
			}
			else if ( v.getId() == R.id.buttonCancel )
			{
				finish();
			}
			else if ( v.getId() == R.id.buttonDetails )
			{
				Intent intent = new Intent(PrescriptionDetails.this, MedicationDetails.class);
				intent.putExtra("medication", med);
				startActivity(intent);
			}
		}
	}


	private void updateDatabase()
	{
		DocumentReference   ref;
		CollectionReference collection = database.collection("prescriptions");
		if ( action == "add" )
		{
			ref = collection.document();
			prescription.id = ref.getId();
		}
		else // action == "edit"
		{
			ref = collection.document(prescription.id);
		}

		prescription.dosage = dosage.getText().toString();
		prescription.notes = notes.getText().toString();

		if ( prescription.id != null )
		{
			ref.set(prescription).addOnFailureListener(new OnFailureListener()
			{
				@Override
				public void onFailure(Exception e)
				{
					toastSh("error adding to database " + e);
				}
			});
			String              docthing           = "prescriptions." + prescription.id;
			FirebaseAuth        mAuth              = FirebaseAuth.getInstance();
			CollectionReference collectionProfiles = database.collection("profiles");
			DocumentReference   profileRef         = collectionProfiles.document(mAuth.getUid());
			profileRef.update(docthing, "").addOnFailureListener(new OnFailureListener()
			{
				@Override
				public void onFailure(@NonNull Exception e)
				{
					toastSh("error adding to profile in database " + e);
				}
			});
		}
	}


	/**
	 * Generates a short toast message
	 *
	 * @param message - The message to display in the toast
	 */
	private void toastSh(String message)
	{
		Toast.makeText(PrescriptionDetails.this, message, Toast.LENGTH_SHORT).show();
	}
}

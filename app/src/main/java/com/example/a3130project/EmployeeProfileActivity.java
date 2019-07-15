package com.example.a3130project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.a3130project.model.Medication;
import com.example.a3130project.model.Profile;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Arrays;

public class EmployeeProfileActivity extends AppCompatActivity
{
	private EditText genName, name, mainDiseases, manufact, sideEff;
	private Button addMedi;


	private FirebaseFirestore database;
	private Intent            intent;
	private Profile           profile;
	private Medication        medication;
	private DocumentReference profileRef;
	private FirebaseAuth      mAuth;


	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_employee_profile);

		genName = findViewById(R.id.genNameEmp);
		name = findViewById(R.id.nameEmp);
		mainDiseases = findViewById(R.id.mainDiseasesEmp);
		manufact = findViewById(R.id.manuEmp);
		sideEff = findViewById(R.id.sideEffectsEmp);
		addMedi = findViewById(R.id.addMedEmp);

		database = FirebaseFirestore.getInstance();

		mAuth = FirebaseAuth.getInstance();

		intent = getIntent();
		profile = (Profile) intent.getSerializableExtra("profile");

		addMedi.setOnClickListener(new OnClicker());

	}


	public class OnClicker implements View.OnClickListener
	{
		@Override
		public void onClick(View v)
		{
			//TODO: Fix spaces in array storing of variables
			String mainDiseases1 = mainDiseases.getText().toString();
			mainDiseases1 = mainDiseases1.replaceAll("\\s", "");
			String diseases[] = mainDiseases1.split(",");

			ArrayList<String> DIS = new ArrayList<String>(Arrays.asList(diseases));

			String Side_Eff = sideEff.getText().toString();
			Side_Eff = Side_Eff.replaceAll("\\s", "");
			String side_Eff[] = Side_Eff.split(",");

			ArrayList<String> SIDE = new ArrayList<String>(Arrays.asList(side_Eff));

			DocumentReference ref = database.collection("medications").document();
			String            id  = ref.getId();

			medication = new Medication(id,
			                            name.getText().toString(),
			                            genName.getText().toString(),
			                            manufact.getText().toString(),
			                            SIDE,
			                            DIS);
			ref.set(medication);
		}
	}
}

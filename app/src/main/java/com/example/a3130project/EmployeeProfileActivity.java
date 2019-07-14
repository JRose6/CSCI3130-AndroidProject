package com.example.a3130project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.a3130project.Helpers.ToolBarCreator;
import com.example.a3130project.model.Profile;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;


public class EmployeeProfileActivity extends AppCompatActivity
{
	private EditText medName, normName, mainDiseases, manufact, sideEff;
	private Button addMedi;



	private FirebaseFirestore database;
	private Intent intent;
	private Profile profile;
	private DocumentReference profileRef;
	private FirebaseAuth mAuth;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_employee_profile);

		medName = findViewById(R.id.genNameEmp);
		normName = findViewById(R.id.nameEmp);
		mainDiseases = findViewById(R.id.mainDiseasesEmp);
		manufact = findViewById(R.id.manuEmp);
		sideEff = findViewById(R.id.sideEffectsEmp);
		addMedi = findViewById(R.id.addMedEmp);


		database = FirebaseFirestore.getInstance();

		mAuth = FirebaseAuth.getInstance();

		intent = getIntent();
		profile = (Profile) intent.getSerializableExtra("profile");


		//ToolBarCreator.createBottomNav(this);

		//ToolBarCreator.createToolbar(this, true, false);


		Toast.makeText(this, "Hello", Toast.LENGTH_SHORT).show();
	}
}

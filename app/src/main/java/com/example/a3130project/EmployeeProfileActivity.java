package com.example.a3130project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.a3130project.Helpers.ToolBarCreator;


public class EmployeeProfileActivity extends AppCompatActivity
{
	private EditText medName, normName, mainDiseases, manufact, sideEff;
	private Button addMedi;


	private Intent intent;

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

		ToolBarCreator.createBottomNav(this);
		ToolBarCreator.createToolbar(this, true, false);

	}
}

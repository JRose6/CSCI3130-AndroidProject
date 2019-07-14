package com.example.a3130project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


public class EmployeeProfileActivity extends AppCompatActivity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_employee_profile);

		ToolBarCreator.createBottomNav(this);
		ToolBarCreator.createToolbar(this, true, false);
		//'button'
	}
}

package com.example.a3130project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Button;

public class RegistrationActivity extends AppCompatActivity
{
	private EditText firstName, lastName, email;
	private Button register;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registration);
	}
}

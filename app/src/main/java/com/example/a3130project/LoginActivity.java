package com.example.a3130project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.os.Bundle;

public class LoginActivity extends AppCompatActivity
{


	public EditText logEmail, logPassword;
	public Button signIn, newUser;


	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		logEmail = findViewById(R.id.email);
		logPassword = findViewById(R.id.password);
		signIn = findViewById(R.id.signIn);
		newUser = findViewById(R.id.newUser);

		signIn.setOnClickListener(new onClicker());
		newUser.setOnClickListener(new onClicker());


	}





	public class onClicker implements View.OnClickListener
	{
		@Override
		public void onClick(View v)
		{
			switch(v.getId())
			{
			case R.id.newUser:
				launchRegistration();
				break;
			case R.id.signIn:
				profileLoggingIn();
			}

		}
	}

	public void profileLoggingIn()
	{
		Intent intent = new Intent(this, MainProfileLoadActivity.class);
		startActivity(intent);
	}
	public void launchRegistration()
	{

		Intent intent = new Intent(this, RegistrationActivity.class);
		startActivity(intent);
	}

}


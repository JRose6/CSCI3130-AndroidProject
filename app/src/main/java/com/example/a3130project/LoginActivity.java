package com.example.a3130project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.os.Bundle;
import android.widget.Toast;

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


	private Boolean fieldIsEmpty()
	{

		String email = logEmail.getText().toString();
		String password = logPassword.getText().toString();


		if (email.isEmpty() || password.isEmpty())
		{
			return true;
		}

		return false;
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
				if(fieldIsEmpty() == false)
				{
					profileLoggingIn();
				}
				else{
					Toast.makeText(LoginActivity.this, "You are missing email and/or password", Toast.LENGTH_SHORT).show();
				}
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


package com.example.a3130project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class RegistrationActivity extends AppCompatActivity
{
	private EditText firstName, lastName, email, pass;
	private Button register;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registration);
		UIViews();

		register.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				validateCheck();
			}
		});
	}

	private void UIViews()
	{
		firstName = findViewById(R.id.firstName);
		lastName = findViewById(R.id.lastName);
		email = findViewById(R.id.emailInput);
		pass = findViewById(R.id.passwordInput);
		register = findViewById(R.id.register);

	}

	private Boolean validateCheck()
	{
		Boolean result = false;

		String fName      = firstName.getText().toString();
		String lName      = lastName.getText().toString();
		String emailInput = email.getText().toString();
		String password   = pass.getText().toString();

		if (fName.isEmpty() || lName.isEmpty() || emailInput.isEmpty() || password.isEmpty())
		{
			Toast.makeText(this, "You haven't filled in all fields", Toast.LENGTH_SHORT).show();
		}
		else
		{
			result = true;
			Intent intent = new Intent(this, LoginActivity.class);
			Toast.makeText(this, "new user created", Toast.LENGTH_LONG).show();
			startActivity(intent);

		}
		return result;
	}
}

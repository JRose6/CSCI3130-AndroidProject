package com.example.a3130project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;

import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;

import com.example.a3130project.MainActivity;

import com.example.a3130project.model.Profile;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;




public class RegistrationActivity extends AppCompatActivity
{
	private EditText firstName, lastName, email, pass, age, allergies, medication;
	private Button register;

	FirebaseFirestore database;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registration);
		firstName = findViewById(R.id.firstName);
		lastName = findViewById(R.id.lastName);
		email = findViewById(R.id.emailInput);
		pass = findViewById(R.id.passwordInput);
		register = findViewById(R.id.register);

		age = findViewById(R.id.age);
		//allergies = findViewById(R.id.allergies);
		//medication = findViewById(R.id.medication);



		database = FirebaseFirestore.getInstance();

		register.setOnClickListener(new OnClicker());


	}
	public class OnClicker implements View.OnClickListener
	{
		@Override
		public void onClick(View v)
		{

			// TODO: error check same email isn't used twice

			Profile prof = new Profile(firstName.getText().toString(), lastName.getText().toString(), age.getText().toString(),email.getText().toString());

			DocumentReference ref = database.collection("profiles").document(email.getText().toString());

			//creates an auto ID
			prof.id = ref.getId();

			ref.set(prof).addOnFailureListener(new OnFailureListener() {
				@Override
				public void onFailure(@NonNull Exception e) {
					Toast.makeText(RegistrationActivity.this, "Fail.p2", Toast.LENGTH_SHORT)
							.show();
				}
			});

			//Finishes the activity and return to the parent one.
			finish();
		}

	}

	private Boolean validateCheck()
	{
		Boolean result = false;

		String fName      = firstName.getText().toString();
		String lName      = lastName.getText().toString();
		//String emailInput = email.getText().toString();
		//String password   = pass.getText().toString();
		/*
		if (fName.isEmpty() || lName.isEmpty() || age.isEmpty() || email.isEmpty())
		{
			Toast.makeText(this, "You haven't filled in all fields", Toast.LENGTH_SHORT).show();
		}
		else
		{
			result = true;
			Intent intent = new Intent(this, LoginActivity.class);
			Toast.makeText(this, "new user created", Toast.LENGTH_LONG).show();
			startActivity(intent);
		}*/
		return result;
	}
}

package com.example.a3130project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

<<<<<<< HEAD
=======
import android.content.Intent;
import android.graphics.Color;
>>>>>>> branch32_merge_1
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;

import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;

import com.example.a3130project.model.Profile;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;


public class RegistrationActivity extends AppCompatActivity
{
	private EditText firstName, lastName, email, pass, age, allergies, medication;
	private Button       register;
	private FirebaseAuth mAuth;

	private Profile profile;

	FirebaseFirestore database;

	public TextView passValidator, emailValidator;

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

<<<<<<< HEAD
		age = findViewById(R.id.age);
		//allergies = findViewById(R.id.allergies);
		//medication = findViewById(R.id.medication);

=======
		passValidator = findViewById(R.id.passwordValid);
		emailValidator = findViewById(R.id.emailValid);

		firebaseAuth = firebaseAuth.getInstance();

		register.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				String passw = pass.getText().toString();
				String em = email.getText().toString();
				int passRules = PasswordValidator.validPassword(passw);
				int emailRules = EmailValidator.getEmail(em);
				if(passRules==0){
					passValidator.setText("NOT STRONG");
					passValidator.setTextColor(Color.RED);
				}
				if(emailRules==0){
					emailValidator.setText("Invalid email format");
					emailValidator.setTextColor(Color.RED);

				}
				if (passRules < 5 && passRules>0) {

					passValidator.setText("NOT STRONG");
					passValidator.setTextColor(Color.RED);

				}
				if (passRules == 5) {
					passValidator.setText("STRONG");
					passValidator.setTextColor(Color.GREEN);

				}
				if (emailRules==1){
					emailValidator.setText("VALID EMAIL");
					emailValidator.setTextColor(Color.GREEN);

				}
				if (validateCheck())
				{
					String emailInput = email.getText().toString();
					String password   = pass.getText().toString();
>>>>>>> branch32_merge_1

		mAuth = FirebaseAuth.getInstance();
		database = FirebaseFirestore.getInstance();


		register.setOnClickListener(new OnClicker());


	}

	public class OnClicker implements View.OnClickListener
	{
		@Override
		public void onClick(View v)
		{
			mAuth.createUserWithEmailAndPassword(email.getText().toString(), pass.getText()
					.toString())
					.addOnCompleteListener(RegistrationActivity.this, new OnCompleteListener<AuthResult>()
					{
						@Override
						public void onComplete(@NonNull Task<AuthResult> task)
						{
							if (task.isSuccessful())
							{
								FirebaseUser user = mAuth.getCurrentUser();
								createUserProfile(user);
							}
							else
							{
								// If sign in fails, display a message to the user.
								Toast.makeText(RegistrationActivity.this, "Authentication failed."
										+ task.getException(), Toast.LENGTH_SHORT).show();
							}
							// TODO: error check same email isn't used twice
							//Finishes the activity and return to the parent one.
							finish();
						}
					});
		}
	}

	private void createUserProfile(FirebaseUser user)
	{

		profile = new Profile(firstName.getText().toString(), lastName.getText()
				.toString(), age.getText().toString(), email.getText().toString());

		DocumentReference ref = database.collection("profiles").document(user.getUid());

		ref.set(profile).addOnFailureListener(new OnFailureListener()
		{
			@Override
			public void onFailure(@NonNull Exception e)
			{
				Toast.makeText(RegistrationActivity.this, "Fail.p2", Toast.LENGTH_SHORT).show();
			}
		});
	}

	private Boolean validateCheck()
	{
		Boolean result = false;

		String fName = firstName.getText().toString();
		String lName = lastName.getText().toString();
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

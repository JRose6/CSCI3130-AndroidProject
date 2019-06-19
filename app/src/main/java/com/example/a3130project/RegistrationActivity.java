package com.example.a3130project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;

import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import com.example.a3130project.model.Profile;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;


public class RegistrationActivity extends AppCompatActivity
{
	private EditText editFirstName, editLastName, editEmail, editPassword, editAge, allergies,
			medication;
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
		editFirstName = findViewById(R.id.firstName);
		editLastName = findViewById(R.id.lastName);
		editEmail = findViewById(R.id.emailInput);
		editPassword = findViewById(R.id.passwordInput);
		register = findViewById(R.id.register);

		editAge = findViewById(R.id.age);
		//allergies = findViewById(R.id.allergies);
		//medication = findViewById(R.id.medication);

		passValidator = findViewById(R.id.passwordValid);
		emailValidator = findViewById(R.id.emailValid);

		mAuth = FirebaseAuth.getInstance();
		database = FirebaseFirestore.getInstance();

		register.setOnClickListener(new OnClicker());
		editPassword.addTextChangedListener(new TextWatcher()
		{
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after)
			{
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count)
			{
				validatePassword();
			}

			@Override
			public void afterTextChanged(Editable s)
			{
			}
		});

		editEmail.addTextChangedListener(new TextWatcher());
	}

	public class OnClicker implements View.OnClickListener
	{
		@Override
		public void onClick(View v)
		{
			if (validateEmail() && validatePassword())
			{
				createNewUser();
			}

		}
	}

	public class TextWatcher implements android.text.TextWatcher
	{
		@Override
		public void beforeTextChanged(CharSequence s, int start, int count, int after)
		{

		}

		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count)
		{
			validateEmail();
		}

		@Override
		public void afterTextChanged(Editable s)
		{

		}
	}

	/**
	 * Attempts to generate a new user in the FireBase authentication database.
	 * <p>
	 * This function should only be called after locally verifying that the editEmail and password
	 * are valid.
	 */
	private void createNewUser()
	{
		mAuth.createUserWithEmailAndPassword(editEmail.getText().toString(), editPassword.getText()
				.toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>()
		{
			@Override
			public void onSuccess(AuthResult authResult)
			{
				createNewUserProfile(mAuth.getCurrentUser());
			}
		}).addOnFailureListener(new OnFailureListener()
		{
			@Override
			public void onFailure(@NonNull Exception e)
			{
				// If sign in fails, display a message to the user.
				toastSh("Authentication failed. " + e);
			}
		});
	}

	/**
	 * Attempts to generate a new Profile object for a given FireBase user.
	 * <p>
	 * The document ID of the profile will set to the userID of the given user.
	 * This ensures that the profile can be retrieved from FireBase easily at any time.
	 * FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
	 * DocumentRef ref = database.collection("profiles").document(user.getUid());
	 * </p>
	 *
	 * @param user - The 'user' in the FireBase authentication database to associate with the
	 *             new profile object.
	 */
	private void createNewUserProfile(FirebaseUser user)
	{
		profile = new Profile(editFirstName.getText().toString(), editLastName.getText()
				.toString(), editAge.getText().toString(), editEmail.getText().toString());

		DocumentReference ref = database.collection("profiles").document(user.getUid());

		ref.set(profile).addOnSuccessListener(new OnSuccessListener<Void>()
		{
			@Override
			public void onSuccess(Void aVoid)
			{
				toastSh("New profile generated.");
				finish();
			}
		}).addOnFailureListener(new OnFailureListener()
		{
			@Override
			public void onFailure(@NonNull Exception e)
			{
				toastSh("Failed to generate profile for new user.");
			}
		});
	}

	private Boolean validatePassword()
	{
		switch (PasswordValidator.validPassword(editPassword.getText().toString()))
		{
		case Invalid:
			passValidator.setText("Invalid");
			passValidator.setTextColor(Color.rgb(75, 0, 0)); // dark red
			return false;
		case Weak:
			passValidator.setText("Weak");
			passValidator.setTextColor(Color.rgb(175, 75, 0)); // red-orange
			break;
		case Medium:
			passValidator.setText("Okay");
			passValidator.setTextColor(Color.rgb(220, 150, 0)); // light red-orange
			break;
		case Strong:
			passValidator.setText("Strong");
			passValidator.setTextColor(Color.rgb(0, 100, 0)); // dark green
			break;
		case Excellent:
			passValidator.setText("Excellent");
			passValidator.setTextColor(Color.rgb(0, 150, 0)); // light green
			break;
		}
		toastSh(passValidator.getText().toString());
		return true;
	}

	private Boolean validateEmail()
	{
		switch (EmailValidator.getEmail(editEmail.getText().toString()))
		{
		case Valid:
			emailValidator.setText("Valid Email");
			emailValidator.setTextColor(Color.rgb(0, 150, 0)); // light green
			return true;
		case Invalid:
		default:
			emailValidator.setText("Invalid Email format");
			emailValidator.setTextColor(Color.RED);
			return false;
		}
	}

	/**
	 * Generates a short toast with the given message and dumps it to the console log
	 * This is for debugging & development purposes.
	 */
	private void toastSh(String message)
	{
		Toast.makeText(RegistrationActivity.this, message, Toast.LENGTH_SHORT).show();
	}
}

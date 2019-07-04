package com.example.a3130project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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
	private FirebaseAuth      mAuth    = FirebaseAuth.getInstance();
	private FirebaseFirestore database = FirebaseFirestore.getInstance();
	private Profile           profile;

	private EditText editFirstName;
	private EditText editLastName;
	private EditText editEmail;
	private EditText editPassword;
	private EditText editAge;
	private TextView viewPasswordValid;
	private TextView viewEmailValid;
	private Button   buttonRegister;


	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		ToolBarCreator.createToolbar(this,false,true);
		setContentView(R.layout.activity_registration);
		editFirstName = findViewById(R.id.firstName);
		editLastName = findViewById(R.id.lastName);
		editEmail = findViewById(R.id.emailInput);
		editPassword = findViewById(R.id.passwordInput);
		editAge = findViewById(R.id.age);
		viewPasswordValid = findViewById(R.id.passwordValid);
		viewEmailValid = findViewById(R.id.emailValid);
		buttonRegister = findViewById(R.id.register);

		buttonRegister.setOnClickListener(new OnClicker());
		editPassword.addTextChangedListener(new TextWatcher()
		{
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) { }


			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count)
			{
				validatePassword();
			}


			@Override
			public void afterTextChanged(Editable s) {}
		});

		editEmail.addTextChangedListener(new TextWatcher()
		{
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) { }


			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count)
			{
				validateEmail();
			}


			@Override
			public void afterTextChanged(Editable s) {}
		});
	}


	@Override
	protected void onStart()
	{
		super.onStart();
		editFirstName.requestFocus();
	}


	public class OnClicker implements View.OnClickListener
	{
		@Override
		public void onClick(View v)
		{
			if ( validateEmail() && validatePassword() )
			{
				createNewUser();
			}
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
		                                                                                 .toString())
		     .addOnSuccessListener(new OnSuccessListener<AuthResult>()
		     {
			     @Override
			     public void onSuccess(AuthResult authResult)
			     {
				     createNewUserProfile(mAuth.getCurrentUser());
			     }
		     })
		     .addOnFailureListener(new OnFailureListener()
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
	 * The document ID of the profile will set to the userID of the given user. This ensures that
	 * the profile can be retrieved from FireBase easily at any time. FirebaseUser user =
	 * FirebaseAuth.getInstance().getCurrentUser(); DocumentRef ref = database.collection("profiles").document(user.getUid());
	 * </p>
	 *
	 * @param user - The 'user' in the FireBase authentication database to associate with the new
	 *             profile object.
	 */
	private void createNewUserProfile(FirebaseUser user)
	{
		profile = new Profile(editFirstName.getText().toString(),
		                      editLastName.getText()
		                                  .toString(),
		                      editAge.getText().toString(),
		                      editEmail.getText().toString());

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
		boolean valid = true;
		switch ( PasswordValidator.validPassword(editPassword.getText().toString()) )
		{
		case Invalid:
			viewPasswordValid.setText("Invalid");
			viewPasswordValid.setTextColor(getColor(R.color.darkRed)); // dark red
			valid = false;
			break;
		case Weak:
			viewPasswordValid.setText("Weak");
			viewPasswordValid.setTextColor(getColor(R.color.darkOrangeRed)); // red-orange
			break;
		case Medium:
			viewPasswordValid.setText("Okay");
			viewPasswordValid.setTextColor(getColor(R.color.lightOrangeRed)); // light red-orange
			break;
		case Strong:
			viewPasswordValid.setText("Strong");
			viewPasswordValid.setTextColor(getColor(R.color.darkGreen)); // dark green
			break;
		case Excellent:
			viewPasswordValid.setText("Excellent");
			viewPasswordValid.setTextColor(getColor(R.color.lightGreen)); // light green
			break;
		}
		return valid;
	}


	private Boolean validateEmail()
	{
		switch ( EmailValidator.getEmail(editEmail.getText().toString()) )
		{
		case Valid:
			viewEmailValid.setText("Valid Email");
			viewEmailValid.setTextColor(getColor(R.color.lightGreen)); // light green
			return true;
		case Invalid:
		default:
			viewEmailValid.setText("Invalid Email format");
			viewEmailValid.setTextColor(Color.RED);
			return false;
		}
	}


	/**
	 * Generates a short toast with the given message. This is for debugging & development
	 * purposes.
	 */
	private void toastSh(String message)
	{
		Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
	}
}

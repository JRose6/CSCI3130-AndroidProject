package com.example.a3130project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.util.Log;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.a3130project.model.Profile;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import android.os.Bundle;
import android.widget.Toast;


public class LoginActivity extends AppCompatActivity
{
	public EditText editEmail, editPassword;
	public Button buttonSignIn, buttonNewUser;

	private FirebaseAuth mAuth;

	private FirebaseFirestore database;
	private Profile           profile;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		editEmail = findViewById(R.id.email);
		editPassword = findViewById(R.id.password);
		buttonSignIn = findViewById(R.id.signIn);
		buttonNewUser = findViewById(R.id.newUser);

		database = FirebaseFirestore.getInstance();
		mAuth = FirebaseAuth.getInstance();

		buttonSignIn.setOnClickListener(new onClicker());
		buttonNewUser.setOnClickListener(new onClicker());
	}

	@Override
	protected void onStart()
	{
		super.onStart();
		editPassword.setText("");
		editEmail.requestFocus();
	}

	private Boolean fieldIsEmpty()
	{
		String email    = editEmail.getText().toString();
		String password = editPassword.getText().toString();

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
			switch (v.getId())
			{
			case R.id.newUser:
				launchRegistration();
				break;
			case R.id.signIn:
				if (fieldIsEmpty())
				{
					toastSh("You are missing e-mail and/or password");
				}
				else
				{
					signIn();
				}
			}
		}
	}

	/**
	 * Attempts to authenticate with FireBase using the current contents of the editEmail and
	 * editPassword textEdits. If the sign-in was successful, it will switch to the
	 * 'MainProfileLoadActivity'
	 */
	public void signIn()
	{
		String email = editEmail.getText().toString();
		String pass  = editPassword.getText().toString();
		mAuth.signInWithEmailAndPassword(email, pass)
				.addOnSuccessListener(new OnSuccessListener<AuthResult>()
				{
					@Override
					public void onSuccess(AuthResult authResult)
					{
						// If sign in fails, display a message to the user.
						logg("signIn()", "Authentication Succeeded. " + authResult);
						DocumentReference ref = database.collection("profiles")
								.document(mAuth.getUid());
						ref.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>()
						{
							@Override
							public void onComplete(@NonNull Task<DocumentSnapshot> task)
							{
								DocumentSnapshot snap = task.getResult();
								if (snap.exists())
								{
									profile = snap.toObject(Profile.class);
									toastSh(profile.toString());
									logg("signIn()", profile.toString());
									openProfile();
								}
								else
								{
									toastSh("This profile doesn't exist.");
									logg("signIn()", "This profile doesn't fucking exist... "
											+ "You should never see this message");
								}
							}
						});

					}
				})
				.addOnFailureListener(new OnFailureListener()
				{
					@Override
					public void onFailure(@NonNull Exception e)
					{
						// If sign in fails, display a message to the user.
						toastSh("Authentication failed. " + e);
						logg("signIn()", "Authentication failed. " + e);
					}
				});
	}

	public void openProfile()
	{
		Intent intent = new Intent(this, MainProfileLoadActivity.class);
		startActivity(intent);
	}


	public void launchRegistration()
	{
		Intent intent = new Intent(this, RegistrationActivity.class);
		startActivity(intent);
	}


	/**
	 * Dumps the given tag and message to the console log
	 * This is for debugging & development purposes.
	 */
	private void logg(String tag, String message)
	{
		toastSh(message);
		Log.println(5, "-------------------", "-----------------------------");
		Log.println(5, "-------------------", "-----------------------------");
		Log.println(5, tag, message);
		Log.println(5, "-------------------", "-----------------------------");
		Log.println(5, "-------------------", "-----------------------------");
	}

	/**
	 * Generates a short toast message
	 *
	 * @param message - The message to display in the toast
	 */
	private void toastSh(String message)
	{
		Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
	}
} // end class


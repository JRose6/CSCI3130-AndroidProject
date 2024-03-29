package com.example.a3130project.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.util.Log;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.a3130project.R;
import com.example.a3130project.Helpers.ToolBarCreator;
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
import android.widget.TextView;
import android.widget.Toast;


public class LoginActivity extends AppCompatActivity
{
	private static Profile profile;

	private FirebaseFirestore database = FirebaseFirestore.getInstance();
	private FirebaseAuth      mAuth    = FirebaseAuth.getInstance();
	public  EditText          editEmail;
	public  EditText          editPassword;
	public  Button            buttonSignIn;
	public  Button            buttonNewUser;
	private TextView          txtError;


	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		ToolBarCreator.createToolbar(this, false, false);

		setContentView(R.layout.activity_login);
		txtError = findViewById(R.id.txtErrorMessage);
		editEmail = findViewById(R.id.email);
		editPassword = findViewById(R.id.password);
		buttonSignIn = findViewById(R.id.signIn);
		buttonNewUser = findViewById(R.id.newUser);

		buttonSignIn.setOnClickListener(new onClicker());
		buttonNewUser.setOnClickListener(new onClicker());
	}


	@Override
	protected void onResume()
	{
		super.onResume();
		if ( mAuth.getCurrentUser() != null )
		{
			openProfile();
		}
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

		if ( email.isEmpty() || password.isEmpty() )
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
			switch ( v.getId() )
			{
			case R.id.newUser:
				launchRegistration();
				break;
			case R.id.signIn:
				if ( fieldIsEmpty() )
				{
					txtError.setText("You are missing e-mail and/or password");
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
	 * 'ProfileFragment'
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
				     DocumentReference ref = database.collection("profiles")
				                                     .document(mAuth.getUid());
				     ref.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>()
				     {
					     @Override
					     public void onComplete(@NonNull Task<DocumentSnapshot> task)
					     {
						     DocumentSnapshot snap = task.getResult();
						     if ( snap.exists() )
						     {
							     profile = snap.toObject(Profile.class);
							     toastSh("Authentication Successful!");
							     openProfile();
						     }
						     else
						     {
							     txtError.setText("This profile doesn't exist.");
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
				     toastSh("Authentication failed.");
				     txtError.setText("Invalid Username or Password");
			     }
		     });
	}


	/**
	 * Starts the Navigation activity & finishes the current activity.
	 */
	public void openProfile()
	{
		startActivity(new Intent(this, NavigationActivity.class));
		finish();
	}


	/**
	 * Starts the RegistrationActivity so that the user can register an account
	 */
	public void launchRegistration()
	{
		startActivity(new Intent(this, RegistrationActivity.class));
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


package com.example.a3130project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.util.Log;
import android.graphics.Color;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a3130project.model.Profile;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import android.os.Bundle;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity
{

	public EditText logEmail, logPassword;
	public Button signIn, newUser;

	private FirebaseAuth mAuth;

	private FirebaseFirestore        database;
	private FirestoreRecyclerAdapter adapter;
	private Profile                  profile;

	public TextView passValidator, emailValidator;
    
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		logEmail = findViewById(R.id.email);
		logPassword = findViewById(R.id.password);
		signIn = findViewById(R.id.signIn);
		newUser = findViewById(R.id.newUser);
		passValidator = findViewById(R.id.passwordValid);
		emailValidator = findViewById(R.id.emailValid);


		database = FirebaseFirestore.getInstance();
		mAuth = FirebaseAuth.getInstance();

		signIn.setOnClickListener(new onClicker());
		newUser.setOnClickListener(new onClicker());
		signIn.setOnClickListener(new onClicker1());
	}


	public class onClicker1 implements View.OnClickListener{
		public void onClick(View v){
			String pass = logPassword.getText().toString();
			String email = logEmail.getText().toString();
			int passRules = PasswordValidator.validPassword(pass);
			int emailRules = EmailValidator.getEmail(email);
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

			}
	}


	private Boolean fieldIsEmpty()
	{
		String email    = logEmail.getText().toString();
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
			switch (v.getId())
			{
			case R.id.newUser:
				launchRegistration();
				break;
			case R.id.signIn:
				if (fieldIsEmpty())
				{
					Toast.makeText(LoginActivity.this, "You are missing email and/or password", Toast.LENGTH_SHORT)
							.show();
				}
				else
				{
					signIn(); // If this fails... only a toast appears.
				}
			}
		}
	}

	/**
	 * Attempts to authenticate with FireBase using the current contents of the logEmail and
	 * logPassword textEdits. If the sign-in was successful, it will switch to the
	 * 'MainProfileLoadActivity'
	 */
	public void signIn()
	{
		String email = logEmail.getText().toString();
		String pass  = logPassword.getText().toString();
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
									logg("signIn()", profile.toString());
									openProfile();
								}
								else
								{
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
						logg("signIn()", "Authentication failed. " + e);
					}
				});
	}

	public void openProfile()
	{
		Intent intent = new Intent(this, MainProfileLoadActivity.class);
		intent.putExtra("profile", profile);
		startActivity(intent);
	}


	public void launchRegistration()
	{
		Intent intent = new Intent(this, RegistrationActivity.class);
		startActivity(intent);
	}


	/**
	 * Generates a short toast with the given message and dumps it to the console log
	 * This is for debugging & development purposes.
	 */
	private void logg(String tag, String message)
	{
		Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
		Log.println(5, "-------------------", "-----------------------------");
		Log.println(5, "-------------------", "-----------------------------");
		Log.println(5, tag, message);
		Log.println(5, "-------------------", "-----------------------------");
		Log.println(5, "-------------------", "-----------------------------");
	}
} // end class


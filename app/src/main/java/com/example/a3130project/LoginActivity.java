package com.example.a3130project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import android.os.Bundle;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity
{

	public EditText logEmail, logPassword;
	public Button signIn, newUser;

	private FirebaseFirestore database;
	private FirestoreRecyclerAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		logEmail = findViewById(R.id.email);
		logPassword = findViewById(R.id.password);
		signIn = findViewById(R.id.signIn);
		newUser = findViewById(R.id.newUser);

		database = FirebaseFirestore.getInstance();

		Map<String, Object> profileMap = new HashMap<>();


		database.collection("profiles");

		signIn.setOnClickListener(new onClicker());
		newUser.setOnClickListener(new onClicker());
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
					// TODO: validate the email & password with FireBase, and get the userID
					profileLoggingIn();
				}
			}
		}
	}


	public void profileLoggingIn()
	{
		// TODO: Use the userID to get their profile object from FireBase, serialize it and pass
		Intent intent = new Intent(this, MainProfileLoadActivity.class);
		// intent.putExtra("profile", usersProfileObject);
		startActivity(intent);
	}


	public void launchRegistration()
	{
		Intent intent = new Intent(this, RegistrationActivity.class);
		startActivity(intent);
	}
} // end class


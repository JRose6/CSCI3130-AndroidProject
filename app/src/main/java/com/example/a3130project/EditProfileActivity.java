package com.example.a3130project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.PopupMenu;

import com.example.a3130project.model.Profile;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class EditProfileActivity extends AppCompatActivity
{

	private EditText firstName, lastName, age;
	private FirebaseFirestore database;
	private Intent            intent;
	private Profile           profile;

	private DocumentReference profileRef;
	//private FirebaseAuth      mAuth;





	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_profile);


		firstName = findViewById(R.id.firstName);
		lastName = findViewById(R.id.lastName);
		age = findViewById(R.id.age);

		database = FirebaseFirestore.getInstance();



		intent = getIntent();
		profile = (Profile) intent.getSerializableExtra("userProfile");



	}

	public void loadProfile(View view)
	{

	}
}

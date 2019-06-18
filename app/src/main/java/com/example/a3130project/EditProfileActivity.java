package com.example.a3130project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.a3130project.model.Profile;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class EditProfileActivity extends AppCompatActivity
{

	private EditText firstName, lastName, age;
	private FirebaseFirestore database;
	private Intent            intent;
	private Profile           profile;

	private Button update, mainProfile;

	private DocumentReference profileRef;
	private FirebaseAuth      mAuth;





	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_profile);


		firstName = findViewById(R.id.firstName);
		lastName = findViewById(R.id.lastName);
		age = findViewById(R.id.age);
		update = findViewById(R.id.update);
		mainProfile = findViewById(R.id.mainprofile);

		database = FirebaseFirestore.getInstance();

		mAuth = FirebaseAuth.getInstance();




		intent = getIntent();
		profile = (Profile) intent.getSerializableExtra("userProfile");

		firstName.setText(profile.firstName);
		lastName.setText(profile.lastName);
		age.setText(profile.age);

		update.setOnClickListener(new OnClicker());

		mainProfile.setOnClickListener(new OnClicker());


	}

	public class OnClicker implements View.OnClickListener
	{
		@Override
		public void onClick(View v)
		{
			switch(v.getId())
			{
			case R.id.update:
				updateProfile();
				break;
			case R.id.mainprofile:
				backToMainProfile();
				break;
			}

		}
	}

	public void backToMainProfile()
	{
		Intent intent = new Intent(this, MainProfileLoadActivity.class);
		startActivity(intent);
	}

	public void updateProfile()
	{

		profileRef = database.collection("profiles").document(mAuth.getUid());

		profile.firstName = firstName.getText().toString();
		profile.lastName = lastName.getText().toString();
		profile.age = age.getText().toString();

		profileRef.set(profile)
				.addOnFailureListener(new OnFailureListener()
				{
					@Override
					public void onFailure(@NonNull Exception e)
					{
						Toast.makeText(EditProfileActivity.this, "Failed to update fields", Toast.LENGTH_SHORT).show();
					}
				});
	}
}

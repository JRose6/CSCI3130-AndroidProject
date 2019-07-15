package com.example.a3130project.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.a3130project.EmployeeProfileActivity;
import com.example.a3130project.R;
import com.example.a3130project.Helpers.ToolBarCreator;
import com.example.a3130project.model.Profile;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class EditProfileActivity extends AppCompatActivity
{

	private EditText firstName, lastName, age;
	private FirebaseFirestore database;

	private Intent  intent;
	private Profile profile;

	private Button update, TESTING;

	private DocumentReference profileRef;
	private FirebaseAuth      mAuth;


	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_profile);
		ToolBarCreator.createBottomNav(this);
		ToolBarCreator.createToolbar(this, true, true);
		firstName = findViewById(R.id.firstName);
		lastName = findViewById(R.id.lastName);
		age = findViewById(R.id.age);
		update = findViewById(R.id.update);

		//TODO: GET RID OFF TESTING BUTTON
		TESTING = findViewById(R.id.TESTING);

		database = FirebaseFirestore.getInstance();

		mAuth = FirebaseAuth.getInstance();

		intent = getIntent();
		profile = (Profile) intent.getSerializableExtra("profile");

		firstName.setText(profile.firstName);
		lastName.setText(profile.lastName);
		age.setText(profile.age);

		update.setOnClickListener(new OnClicker());

		//TODO: GET RID OF THIS FUNCTION IN ONCLICKER
		TESTING.setOnClickListener(new OnClicker());

	}


	public class OnClicker implements View.OnClickListener
	{
		@Override
		public void onClick(View v)
		{
			switch ( v.getId() )
			{
			case R.id.update:
				updateProfile();
				break;
			case R.id.TESTING:
				employeePAGE();
				break;
			}
		}
	}


	//TODO: get rid of
	public void employeePAGE()
	{
		Intent intent = new Intent(this, EmployeeProfileActivity.class);
		startActivity(intent);
	}


	public void updateProfile()
	{

		profileRef = database.collection("profiles").document(mAuth.getUid());

		profile.firstName = firstName.getText().toString();
		profile.lastName = lastName.getText().toString();
		profile.age = age.getText().toString();

		profileRef.set(profile).addOnFailureListener(new OnFailureListener()
		{
			@Override
			public void onFailure(@NonNull Exception e)
			{
				Toast.makeText(EditProfileActivity.this,
				               "Failed to update fields",
				               Toast.LENGTH_SHORT)
				     .show();
			}
		});
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		return ToolBarCreator.createMenu(this, menu, true);
	}

}

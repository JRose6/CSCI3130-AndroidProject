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

import com.example.a3130project.R;
import com.example.a3130project.Helpers.ToolBarCreator;
import com.example.a3130project.model.Profile;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class EditProfileActivity extends AppCompatActivity
{
	private FirebaseFirestore database = FirebaseFirestore.getInstance();
	private FirebaseAuth      mAuth    = FirebaseAuth.getInstance();

	private Intent   intent;
	private Profile  profile;
	private Button   update;
	private EditText firstName, lastName, age;


	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_profile);
		ToolBarCreator.createToolbar(this, true, true);
		firstName = findViewById(R.id.firstName);
		lastName = findViewById(R.id.lastName);
		age = findViewById(R.id.age);
		update = findViewById(R.id.update);


		intent = getIntent();
		profile = (Profile) intent.getSerializableExtra("profile");

		firstName.setText(profile.firstName);
		lastName.setText(profile.lastName);
		age.setText(profile.age);

		update.setOnClickListener(new OnClicker());
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
			}
		}
	}


	/**
	 * Attempts to update the user's profile in the database with the values currently contained in
	 * each of the edit fields. Will display a short Toast message if it fails to update. If the
	 * update was successful, the activity will finish();
	 */
	public void updateProfile()
	{
		try
		{
			DocumentReference profileRef = database.collection("profiles").document(mAuth.getUid());

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
			}).addOnSuccessListener(new OnSuccessListener<Void>()
			{
				@Override
				public void onSuccess(Void aVoid)
				{
					finish();
				}
			});

		} catch ( Exception e )
		{
			Toast.makeText(EditProfileActivity.this,
			               "Errors on form!",
			               Toast.LENGTH_LONG)
			     .show();
		}
	}


	/**
	 * Creates the options menu for this activity
	 *
	 * @param menu
	 * @return success or failure
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		return ToolBarCreator.createMenu(this, menu, true);
	}
}

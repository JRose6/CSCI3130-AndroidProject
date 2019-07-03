package com.example.a3130project;

import android.content.Intent;
import android.view.Menu;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a3130project.model.Profile;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainProfileLoadActivity extends AppCompatActivity
{
	private TextView          textViewFirstName;
	private TextView          textViewLastName;
	private FirebaseFirestore database = FirebaseFirestore.getInstance();
	
	private Button            buttonEditProfile;
	private Button            calendar;
	private Button            dosage;
	private Button            buttonAddMed;
	private Profile           profile;


	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_profile_load);
		ToolBarCreator.createToolbar(this);
		calendar = findViewById(R.id.calendar);
		buttonEditProfile = findViewById(R.id.buttonEditProfile);
		buttonAddMed = findViewById(R.id.buttonAddPrescription);

		textViewFirstName = findViewById(R.id.textViewFirstName);
		textViewLastName = findViewById(R.id.textViewLastName);

		database = FirebaseFirestore.getInstance();

		calendar.setOnClickListener(new OnClicker());
		buttonEditProfile.setOnClickListener(new OnClicker());

		buttonAddMed.setOnClickListener(new OnClicker());
	}


	@Override
	protected void onStart()
	{
		super.onStart();
		FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
		if ( user != null )
		{
			database.collection("profiles")
			        .document(user.getUid())
			        .get()
			        .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>()
			        {
				        @Override
				        public void onSuccess(DocumentSnapshot documentSnapshot)
				        {
					        profile = documentSnapshot.toObject(Profile.class);
					        textViewFirstName.setText(profile.firstName);
					        textViewLastName.setText(profile.lastName);
				        }
			        })
			        .addOnFailureListener(new OnFailureListener()
			        {
				        @Override
				        public void onFailure(@NonNull Exception e)
				        {
					        toastSh("Failed to update profile fields.");
				        }
			        });
		}
		getDelegate().onStart();
	}


	public class OnClicker implements View.OnClickListener
	{
		@Override
		public void onClick(View v)
		{
			if ( v.getId() == R.id.buttonEditProfile )
			{
				launchEditProfile();
			}
			else if ( v.getId() == R.id.calendar )
			{
				calendarPage();
			}
			else if ( v.getId() == R.id.dosage )
			{
				dosagePage();
			}
			else if ( v.getId() == R.id.buttonAddPrescription )
			{
				medicationPage();
			}
		}
	}


	public void medicationPage()
	{
		Intent intent = new Intent(this, AllMedications.class);
		startActivity(intent);
	}


	public void calendarPage()
	{
		Intent intent = new Intent(this, calendarActivity.class);
		startActivity(intent);
	}


	public void dosagePage()
	{
		Intent intent = new Intent(this, dosageActivity.class);
		startActivity(intent);
	}


	public void launchEditProfile()
	{
		Intent intent = new Intent(this, EditProfileActivity.class);
		intent.putExtra("profile", profile);
		startActivity(intent);
	}

	/*
	// Connect the recycler view to the medication view holder & the FireStore adapter
	private void setUpRecyclerView()
	{
		Query query = medicationsRef.orderBy("name", Query.Direction.DESCENDING);
		FirestoreRecyclerOptions<Medication> options =
				new FirestoreRecyclerOptions.Builder<Medication>()
						.setQuery(query, Medication.class).build();

		adapter = new MedicationAdapter(options);

		RecyclerView recyclerView = findViewById(R.id.medicationRecycler);
		recyclerView.setHasFixedSize(true);
		recyclerView.setLayoutManager(new LinearLayoutManager(this));
		recyclerView.setAdapter(adapter);
	}
*/

	private void toastSh(String message)
	{
		Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		return ToolBarCreator.createMenu(this, menu);
	}

}

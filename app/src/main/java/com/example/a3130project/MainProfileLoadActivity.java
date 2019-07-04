package com.example.a3130project;

import android.content.Intent;
import android.view.Menu;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a3130project.model.Prescription;
import com.example.a3130project.model.Profile;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class MainProfileLoadActivity extends AppCompatActivity
{
	private PrescriptionRecyclerAdapter adapter;
	private FirebaseFirestore           database = FirebaseFirestore.getInstance();
	private FirebaseAuth                mAuth    = FirebaseAuth.getInstance();
	private Profile                     profile;

	private TextView textViewFirstName;
	private TextView textViewLastName;

	private Button buttonEditProfile;
	private Button buttonCalendar;
	private Button buttonDosage;
	private Button buttonAddMed;


	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_profile_load);
		ToolBarCreator.createToolbar(this);
		buttonCalendar = findViewById(R.id.calendar);
		buttonEditProfile = findViewById(R.id.buttonEditProfile);
		buttonAddMed = findViewById(R.id.buttonAddPrescription);

		textViewFirstName = findViewById(R.id.textViewFirstName);
		textViewLastName = findViewById(R.id.textViewLastName);

		database = FirebaseFirestore.getInstance();

		buttonCalendar.setOnClickListener(new OnClicker());
		buttonEditProfile.setOnClickListener(new OnClicker());
		buttonAddMed.setOnClickListener(new OnClicker());

		setUpRecyclerView();
	}


	@Override
	protected void onStart()
	{
		super.onStart();
		if ( mAuth.getCurrentUser() == null )
		{ // A user should never be able to open the profile activity if they aren't signed in
			finish();
		}
		updateProfileFields();
		adapter.startListening();
		getDelegate().onStart();
	}


	@Override
	protected void onStop()
	{
		super.onStop();
		adapter.stopListening();
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


	private void updateProfileFields()
	{
		database.collection("profiles")
		        .document(mAuth.getCurrentUser().getUid())
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


	// Connect the recycler view to the medication view holder & the FireStore adapter
	private void setUpRecyclerView()
	{
		String              profileId         = FirebaseAuth.getInstance().getUid();
		String              prescriptionsPath = profileId + "/prescriptions";
		CollectionReference prescriptRef      = database.collection(prescriptionsPath);
		Query query =
				prescriptRef.orderBy("id", Query.Direction.DESCENDING);
		FirestoreRecyclerOptions<Prescription> options =
				new FirestoreRecyclerOptions.Builder<Prescription>()
						.setQuery(query, Prescription.class).build();

		adapter = new PrescriptionRecyclerAdapter(options);

		RecyclerView recyclerView = findViewById(R.id.medicationRecycler);
		recyclerView.setHasFixedSize(true);
		recyclerView.setLayoutManager(new LinearLayoutManager(this));
		recyclerView.setAdapter(adapter);
	}


	@Override
	public void onBackPressed() { /* Intentionally empty to disable the back button */ }


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

package com.example.a3130project;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a3130project.model.Medication;
import com.example.a3130project.model.Profile;
import com.example.a3130project.viewholder.MedicationViewHolder;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import static com.example.a3130project.MainActivity.logg;

public class MainProfileLoadActivity extends AppCompatActivity
{
	private TextView                 textViewFirstName;
	private TextView                 textViewLastName;
	private RecyclerView             recyclerViewMedication;
	private FirebaseFirestore        database;
	private FirestoreRecyclerAdapter adapter;
	private Button                   buttonEditProfile;
	private Button                   buttonLogout;
	private Button                   calendar;
	private Button                   dosage;
	private Profile                  profile;
	private Intent                   intent;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_profile_load);

		calendar = findViewById(R.id.calendar);
		dosage = findViewById(R.id.dosage);
		buttonEditProfile = findViewById(R.id.editprofile);
		buttonLogout = findViewById(R.id.buttonLogout);


		recyclerViewMedication = findViewById(R.id.medicationList);
		textViewFirstName = findViewById(R.id.textViewFirstName);
		textViewLastName = findViewById(R.id.textViewLastName);

		database = FirebaseFirestore.getInstance();

		adapter = setUpMedicationAdapter(database);
		setUpRecyclerView(recyclerViewMedication, adapter);

		calendar.setOnClickListener(new OnClicker());
		dosage.setOnClickListener(new OnClicker());
		buttonEditProfile.setOnClickListener(new OnClicker());
		buttonLogout.setOnClickListener(new OnClicker());

	}

	@Override
	protected void onStart()
	{
		super.onStart();
		FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
		if (user != null)
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
			if (v.getId() == R.id.editprofile)
			{
				launchEditProfile();
			}
			else if (v.getId() == R.id.buttonLogout)
			{
				FirebaseAuth.getInstance().signOut();
				finish();
			}
			else if(v.getId() == R.id.calendar)
			{
				calendarPage();
			}
			else if(v.getId() == R.id.dosage)
			{
				dosagePage();
			}
		}
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
	private void setUpRecyclerView(RecyclerView rv, FirestoreRecyclerAdapter adapter)
	{
		RecyclerView.LayoutManager manager = new LinearLayoutManager(getApplicationContext());
		rv.setLayoutManager(manager);
		rv.setItemAnimator(new DefaultItemAnimator());
		rv.setAdapter(adapter);
	}


	// Creates a Firestore adapter to populate a Recycler view.
	private FirestoreRecyclerAdapter setUpMedicationAdapter(FirebaseFirestore db)
	{
		// TODO: Query the Database for only the 'medications' that are listed in the user's profile
		Query query = db.collection("medications").orderBy("name").limit(50);
		FirestoreRecyclerOptions<Medication> options
				= new FirestoreRecyclerOptions.Builder<Medication>().setQuery(query, Medication.class)
				.build();

		FirestoreRecyclerAdapter adapter
				= new FirestoreRecyclerAdapter<Medication, MedicationViewHolder>(options)
		{
			// Connect each medication item in the database to the view
			@Override
			public void onBindViewHolder(MedicationViewHolder holder,
			                             int position,
			                             final Medication model)
			{
				holder.name.setText(model.name);
				holder.info.setText(model.info);

				holder.buttonDetails.setOnClickListener(new View.OnClickListener()
				{
					@Override
					public void onClick(View v)
					{
						/* TODO: Make a 'details' activity for the selected medication
						Intent intent = new Intent(MainProfileLoadActivity.this, MedicationDetail.class);
						intent.putExtra("business", model);
						startActivity(intent);*/
					}
				});
			}

			@Override
			public MedicationViewHolder onCreateViewHolder(ViewGroup group, int i)
			{
				View view = LayoutInflater.from(group.getContext())
						.inflate(R.layout.medication_entry, group, false);
				logg("MedicationViewHolder()", "GROUP: " + group);
				return new MedicationViewHolder(view);
			}
		};
		return adapter;
	}

	/**
	 * Generates a short toast message
	 *
	 * @param message - The message to display in the toast
	 */
	private void toastSh(String message)
	{
		Toast.makeText(MainProfileLoadActivity.this, message, Toast.LENGTH_SHORT).show();
	}
}

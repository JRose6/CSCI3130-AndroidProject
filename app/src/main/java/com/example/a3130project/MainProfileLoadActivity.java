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
import android.widget.Toast;

import com.example.a3130project.model.Medication;
import com.example.a3130project.model.Profile;
import com.example.a3130project.viewholder.MedicationViewHolder;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import com.google.firebase.firestore.DocumentReference;

import static com.example.a3130project.MainActivity.logg;

public class MainProfileLoadActivity extends AppCompatActivity
{
	private RecyclerView             recyclerViewMedication;
	private FirebaseFirestore        database;
	private FirestoreRecyclerAdapter adapter;
	private Button                   EditProfile;
	private Profile profile;
	private DocumentReference profileRef;


	private Intent intent;
	private FirebaseAuth mAuth;


	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_profile_load);

		EditProfile = findViewById(R.id.editprofile);

		recyclerViewMedication = findViewById(R.id.medicationList);

		database = FirebaseFirestore.getInstance();

		mAuth = FirebaseAuth.getInstance();

	    profileRef = database.collection("profiles").document(mAuth.getUid());

	    profileRef.get()
	    .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>()
	    {
		    @Override
		    public void onSuccess(DocumentSnapshot documentSnapshot)
		    {
		    	profile = documentSnapshot.toObject(Profile.class);
		    }
	    });







		logg("onCreate()", "Database...");
		adapter = setUpMedicationAdapter(database);
		setUpRecyclerView(recyclerViewMedication, adapter);

		EditProfile.setOnClickListener(new OnClicker());
	}

	public class OnClicker implements View.OnClickListener
	{
		@Override
		public void onClick(View v)
		{
			launchEditProfile();
		}
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
				logg("xxxxxxxxxx MedicationViewHolder()", "GROUP: " + group);
				return new MedicationViewHolder(view);
			}
		};
		return adapter;
	}
}

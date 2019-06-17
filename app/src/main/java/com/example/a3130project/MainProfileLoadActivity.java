package com.example.a3130project;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.a3130project.model.Medication;
import com.example.a3130project.viewholder.MedicationViewHolder;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import static com.example.a3130project.MainActivity.logg;

public class MainProfileLoadActivity extends AppCompatActivity
{
	private RecyclerView             recyclerViewMedication;
	private FirebaseFirestore        database;
	private FirestoreRecyclerAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_profile_load);

		recyclerViewMedication = findViewById(R.id.medicationList);
		database = FirebaseFirestore.getInstance();
		logg("onCreate()", "Database...");
		adapter = setUpMedicationAdapter(database);
		setUpRecyclerView(recyclerViewMedication, adapter);
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

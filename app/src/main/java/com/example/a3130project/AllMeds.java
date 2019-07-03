package com.example.a3130project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a3130project.model.Medication;
import com.example.a3130project.viewholder.MedicationViewHolder;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import static com.example.a3130project.MainActivity.logg;

public class AllMeds extends AppCompatActivity
{

	private FirebaseFirestore        database       = FirebaseFirestore.getInstance();
	private CollectionReference      medicationsRef = database.collection("medications");

	private MedicationAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_all__meds);

		setUpRecyclerView();
	}

	@Override
	protected void onStart()
	{
		super.onStart();
		adapter.startListening();
	}

	@Override
	protected void onStop()
	{
		super.onStop();
		adapter.stopListening();
	}

	// Connect the recycler view to the medication view holder & the FireStore adapter
	private void setUpRecyclerView()
	{
		Query query = medicationsRef.orderBy("name", Query.Direction.DESCENDING);
		FirestoreRecyclerOptions<Medication> options = new FirestoreRecyclerOptions.Builder<Medication>()
		                                     .setQuery(query, Medication.class).build();

		adapter = new MedicationAdapter(options);

		RecyclerView recyclerView = findViewById(R.id.medicationRecycler);
		recyclerView.setHasFixedSize(true);
		recyclerView.setLayoutManager(new LinearLayoutManager(this));
		recyclerView.setAdapter(adapter);
	}

	// Creates a Firestore adapter to populate a Recycler view.
	private FirestoreRecyclerAdapter setUpFirebaseAdapter(FirebaseFirestore db)
	{
		toastSh("RecyclerAdapterSetup");
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
		Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
	}
}

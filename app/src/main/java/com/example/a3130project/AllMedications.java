package com.example.a3130project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.widget.Toast;

import com.example.a3130project.model.Medication;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class AllMedications extends AppCompatActivity
{

	private FirebaseFirestore   database       = FirebaseFirestore.getInstance();
	private CollectionReference medicationsRef = database.collection("medications");
	private MedicationAdapter   adapter;


	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_all_meds);
		ToolBarCreator.createToolbar(this,true,false);
		ToolBarCreator.createBottomNav(this);
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
		FirestoreRecyclerOptions<Medication> options =
				new FirestoreRecyclerOptions.Builder<Medication>()
						.setQuery(query, Medication.class).build();

		adapter = new MedicationAdapter(options);

		RecyclerView recyclerView = findViewById(R.id.medicationRecycler);
		recyclerView.setHasFixedSize(true);
		recyclerView.setLayoutManager(new LinearLayoutManager(this));
		recyclerView.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		return ToolBarCreator.createMenu(this, menu,true);
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

package com.example.a3130project.Fragments;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.a3130project.Adapters.MedicationAdapter;
import com.example.a3130project.R;
import com.example.a3130project.model.Medication;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;


public class AllMedicationFragment extends Fragment
{

	private FirebaseFirestore   database       = FirebaseFirestore.getInstance();
	private CollectionReference medicationsRef = database.collection("medications");
	private MedicationAdapter   adapter;


	@Nullable
	@Override
	public View onCreateView(
			@NonNull LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
	{
		super.onCreateView(inflater, container, savedInstanceState);
		return inflater.inflate(R.layout.fragment_all_meds, container, false);
	}


	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
	{
		super.onViewCreated(view, savedInstanceState);
		setUpRecyclerView();

	}


	@Override
	public void onStart()
	{
		super.onStart();
		adapter.startListening();
	}


	@Override
	public void onStop()
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

		RecyclerView recyclerView = getActivity().findViewById(R.id.medicationRecycler);
		recyclerView.setHasFixedSize(true);
		recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
		recyclerView.setAdapter(adapter);
	}


	/**
	 * Generates a short toast message
	 *
	 * @param message - The message to display in the toast
	 */
	private void toastSh(String message)
	{
		Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
	}
}

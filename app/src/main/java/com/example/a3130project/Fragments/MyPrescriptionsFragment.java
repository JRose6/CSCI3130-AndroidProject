package com.example.a3130project.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a3130project.Adapters.PrescriptionAdapter;
import com.example.a3130project.R;
import com.example.a3130project.model.Prescription;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class MyPrescriptionsFragment extends Fragment
{

	private FirebaseFirestore   database = FirebaseFirestore.getInstance();
	private PrescriptionAdapter adapter;


	@Nullable
	@Override
	public View onCreateView(
			@NonNull LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
	{
		super.onCreateView(inflater, container, savedInstanceState);
		return inflater.inflate(R.layout.fragment_my_prescriptions, container, false);
	}


	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
	{
		super.onViewCreated(view, savedInstanceState);
		setUpRecyclerView();
		adapter.startListening();
	}


	// Connect the recycler view to the medication view holder & the FireStore adapter
	private void setUpRecyclerView()
	{
		String              profileId         = FirebaseAuth.getInstance().getUid();
		String              prescriptionsPath = "profiles/" + profileId + "/prescriptions";
		CollectionReference prescriptRef      = database.collection(prescriptionsPath);
		Query query =
				prescriptRef.orderBy("id", Query.Direction.DESCENDING);
		FirestoreRecyclerOptions<Prescription> options =
				new FirestoreRecyclerOptions.Builder<Prescription>()
						.setQuery(query, Prescription.class).build();

		adapter = new PrescriptionAdapter(options);

		RecyclerView recyclerView = getActivity().findViewById(R.id.myMedicationRecycler);
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

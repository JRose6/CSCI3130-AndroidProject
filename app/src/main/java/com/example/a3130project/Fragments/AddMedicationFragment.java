package com.example.a3130project.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.a3130project.R;
import com.example.a3130project.model.Medication;
import com.example.a3130project.model.Profile;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Arrays;

public class AddMedicationFragment extends Fragment
{
	private EditText genName, name, mainDiseases, manufact, sideEff;
	private Button addMedi;



	private FirebaseFirestore database;
	private Intent            intent;
	private Profile           profile;
	private Medication        medication;
	private DocumentReference profileRef;
	private FirebaseAuth      mAuth;
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState){
		super.onViewCreated(view,savedInstanceState);

		genName = getActivity().findViewById(R.id.genNameEmp);
		name = getActivity().findViewById(R.id.nameEmp);
		mainDiseases = getActivity().findViewById(R.id.mainDiseasesEmp);
		manufact = getActivity().findViewById(R.id.manuEmp);
		sideEff = getActivity().findViewById(R.id.sideEffectsEmp);
		addMedi = getActivity().findViewById(R.id.addMedEmp);

		database = FirebaseFirestore.getInstance();

		mAuth = FirebaseAuth.getInstance();

		intent = getActivity().getIntent();
		profile = (Profile) intent.getSerializableExtra("profile");

		addMedi.setOnClickListener(new OnClicker());
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
		// Defines the xml file for the fragment
		super.onCreateView(inflater,parent,savedInstanceState);
		return inflater.inflate(R.layout.add_medication, parent, false);
	}


	/**
	 * Handles the processing of the created medication
	 */
	public class OnClicker implements View.OnClickListener
	{
		@Override
		public void onClick(View v)
		{
			String mainDiseases1 = mainDiseases.getText().toString();
			mainDiseases1 = mainDiseases1.replaceAll("\\s", "");
			String diseases[] = mainDiseases1.split(",");

			ArrayList<String> DIS = new ArrayList<String>(Arrays.asList(diseases));

			String Side_Eff = sideEff.getText().toString();
			Side_Eff = Side_Eff.replaceAll("\\s", "");
			String side_Eff[] = Side_Eff.split(",");

			ArrayList <String> SIDE = new ArrayList<String>(Arrays.asList(side_Eff));

			DocumentReference ref = database.collection("medications").document();
			String id = ref.getId();

			medication = new Medication(id,name.getText().toString(), genName.getText().toString(), manufact.getText().toString(), SIDE, DIS);
			ref.set(medication);
			Toast.makeText(getActivity(),"Medication Saved Successfully!",Toast.LENGTH_LONG).show();
			clearFields();
		}

	}


	/**
	 * Clears the fields after insert
	 */
	private void clearFields(){
		name.setText("");
		genName.setText("");
		manufact.setText("");
		sideEff.setText("");
		mainDiseases.setText("");
	}
}

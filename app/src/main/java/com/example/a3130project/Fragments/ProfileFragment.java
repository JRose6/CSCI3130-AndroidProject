package com.example.a3130project.Fragments;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a3130project.Activities.EditProfileActivity;
import com.example.a3130project.Activities.LoginActivity;
import com.example.a3130project.R;

import com.example.a3130project.model.Profile;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


public class ProfileFragment extends Fragment
{
	private FirebaseFirestore database = FirebaseFirestore.getInstance();
	private FirebaseAuth      mAuth    = FirebaseAuth.getInstance();
	private Profile           profile;

	private TextView textViewFirstName;
	private TextView textViewLastName;

	private Button buttonEditProfile;


	@Nullable
	@Override
	public View onCreateView(
			@NonNull LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
	{
		super.onCreateView(inflater, container, savedInstanceState);
		return inflater.inflate(R.layout.fragment_profile, container, false);
	}


	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
	{

		super.onViewCreated(view, savedInstanceState);
		buttonEditProfile = getActivity().findViewById(R.id.buttonEditProfile);

		textViewFirstName = getActivity().findViewById(R.id.textViewFirstName);
		textViewLastName = getActivity().findViewById(R.id.textViewLastName);

		database = FirebaseFirestore.getInstance();
		updateProfileFields();
		buttonEditProfile.setOnClickListener(new OnClicker());
	}


	@Override
	public void onStart()
	{
		super.onStart();
		if ( mAuth.getCurrentUser() == null )
		{ // A user should never be able to open the profile activity if they aren't signed in
			getActivity().finish();
			return;
		}
		updateProfileFields();
	}
	@Override
	public void onResume()
	{
		super.onResume();
	}



	@Override
	public void onStop()
	{
		super.onStop();
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
				        try
				        {
					        profile = documentSnapshot.toObject(Profile.class);
					        textViewFirstName.setText(profile.firstName);
					        textViewLastName.setText(profile.lastName);

				        } catch ( Exception e )
				        {
					        mAuth.signOut();
					        Intent intent = new Intent(getActivity(), LoginActivity.class);
					        startActivity(intent);
				        }
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


	public void launchEditProfile()
	{
		Intent intent = new Intent(getActivity(), EditProfileActivity.class);
		intent.putExtra("profile", profile);
		startActivity(intent);
	}


	private void toastSh(String message)
	{
		Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
	}
}

package com.example.a3130project;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.a3130project.model.Profile;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class MainProfileLoadActivity extends AppCompatActivity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_profile_load);
	}

	// Creates a Firestore adapter to be used with a Recycler view.
	// We will see adapter in more details after the midterm
	// More info on this: https://github.com/firebase/FirebaseUI-Android/blob/master/firestore/README.md
	private FirestoreRecyclerAdapter setUpProfileAdapter(FirebaseFirestore db) {
		Query query = db.collection("profiles").orderBy("name").limit(50);
		FirestoreRecyclerOptions<Profile> options = new FirestoreRecyclerOptions.Builder<Profile>()
				.setQuery(query, Profile.class)
				.build();

		FirestoreRecyclerAdapter adapter = new FirestoreRecyclerAdapter<Profile, ProfileViewHolder>(options) {
			//For each item in the database connect it to the view
			@Override
			public void onBindViewHolder(ProfileViewHolder holder, int position, final Profile model) {
				holder.name.setText(model.name);
				holder.email.setText(model.email);

				//Set the on click for the button
				//I find this ugly :) but it is how you will see in most examples
				// You CAN use lambadas for the listeners
				// e.g. setOnClickListener ((View v) -> ....
				holder.detailsButton.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						Intent intent = new Intent(MainActivity.this, ProfileDetail.class);
						intent.putExtra("business", model);
						startActivity(intent);
					}
				});
			}

			@Override
			public ProfileViewHolder onCreateViewHolder(ViewGroup group, int i) {
				View view = LayoutInflater.from(group.getContext())
						.inflate(R.layout.business_entry, group, false);
				return new ProfileViewHolder(view);
			}
		};
		return adapter;
	}
}

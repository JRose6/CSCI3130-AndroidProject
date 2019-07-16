package com.example.a3130project.Activities;

import android.content.Intent;
import android.os.Bundle;

import com.example.a3130project.Fragments.CalendarFragment;
import com.example.a3130project.Fragments.HomeFragment;
import com.example.a3130project.Fragments.MedTabFragment;
import com.example.a3130project.Fragments.ProfileFragment;
import com.example.a3130project.R;
import com.example.a3130project.Helpers.ToolBarCreator;
import com.example.a3130project.model.Profile;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.Menu;
import android.view.MenuItem;

public class NavigationActivity extends AppCompatActivity
{
	FragmentManager fragmentManager = getSupportFragmentManager();
	Fragment        mainFragment, calendarFragment, medicationFragment, profileFragment;
	Fragment active;


	private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
			= new BottomNavigationView.OnNavigationItemSelectedListener()
	{
		@Override
		public boolean onNavigationItemSelected(@NonNull MenuItem item)
		{
			FragmentTransaction ft = fragmentManager.beginTransaction();
			Fragment            newFrag;
			switch ( item.getItemId() )
			{
			case R.id.action_home:
				newFrag = mainFragment;
				break;
			case R.id.action_calendar:
				newFrag = calendarFragment;
				break;
			case R.id.action_medication:
				newFrag = medicationFragment;
				break;
			case R.id.action_profile:
				newFrag = profileFragment;
				break;
			default:
				return false;
			}
			ft.addToBackStack(null);
			ft.hide(active);
			ft.show(newFrag);
			ft.commit();
			active = newFrag;
			return true;
		}
	};


	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_navigation);

		if ( FirebaseAuth.getInstance().getCurrentUser() == null )
		{
			Intent intent = new Intent(this, LoginActivity.class);
			this.startActivity(intent);
			return;
		}
		ToolBarCreator.createToolbar(this, true, false);
		mainFragment = new HomeFragment();
		calendarFragment = new CalendarFragment();
		medicationFragment = new MedTabFragment();
		profileFragment = new ProfileFragment();
		active = mainFragment;
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		fragmentTransaction.add(R.id.fragment_container, mainFragment).show(mainFragment);
		fragmentTransaction.add(R.id.fragment_container, calendarFragment).hide(calendarFragment);
		fragmentTransaction.add(R.id.fragment_container, medicationFragment)
		                   .hide(medicationFragment);
		fragmentTransaction.add(R.id.fragment_container, profileFragment).hide(profileFragment);
		fragmentTransaction.commit();
		BottomNavigationView navView = findViewById(R.id.nav_view);
		MenuItem             item    = navView.getMenu().findItem(R.id.action_add_medication);
		item.setVisible(false);
		setAddMedVisibility(item);
		navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
	}


	private void setAddMedVisibility(final MenuItem item)
	{
		FirebaseAuth mAuth = FirebaseAuth.getInstance();
		FirebaseFirestore.getInstance().collection("profiles")
		                 .document(mAuth.getCurrentUser().getUid())
		                 .get()
		                 .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>()
		                 {
			                 @Override
			                 public void onSuccess(DocumentSnapshot documentSnapshot)
			                 {
				                 Profile profile = documentSnapshot.toObject(Profile.class);
				                 item.setVisible(profile.employee);
			                 }
		                 });
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		return ToolBarCreator.createMenu(this, menu, true);
	}
}

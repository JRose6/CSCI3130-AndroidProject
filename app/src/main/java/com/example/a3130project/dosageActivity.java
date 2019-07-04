package com.example.a3130project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.text.DateFormat;


import android.widget.Button;

import android.os.Bundle;
import android.view.Menu;

import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a3130project.model.Medication;
import com.example.a3130project.model.Prescription;
import com.example.a3130project.model.Profile;
import com.example.a3130project.viewholder.CalendarHolder;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;
import java.util.Calendar;


public class dosageActivity extends AppCompatActivity
{
	private FirebaseAuth                mAuth    = FirebaseAuth.getInstance();
	private CalendarAdapter    adapter;
	private FirebaseFirestore database = FirebaseFirestore.getInstance();
	private ListView          listviewMed;

	private static final String TAG = "dosageActivity";
	private Button back;

	Date DOW1;

	private Intent intent;

	private TextView dow;


	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dosage);
		ToolBarCreator.createToolbar(this,true,calendarActivity.class);
		ToolBarCreator.createBottomNav(this);


		//Toast.makeText(this, dow1, Toast.LENGTH_SHORT).show();
		/*
		switch(dow1) {
			case 1:
				dow.setText("Sunday");
				break;
			case 2:
				dow.setText("Monday");
				break;
			case 3:
				dow.setText("Tuesday");
				break;
			case 4:
				dow.setText("Wednesday");
				break;
			case 5:
				dow.setText("Thursday");
				break;
			case 6:
				dow.setText("Friday");
				break;
			case 7:
				dow.setText("Saturday");
				break;
			default:
				//
				break;
		}*/
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

		adapter = new CalendarAdapter(options);

		RecyclerView recyclerView = findViewById(R.id.CalendarRecycler);
		recyclerView.setHasFixedSize(true);
		recyclerView.setLayoutManager(new LinearLayoutManager(this));
		recyclerView.setAdapter(adapter);
	}
	public class OnClicker implements View.OnClickListener
	{
		@Override
		public void onClick(View v)
		{

			Intent intent = new Intent(dosageActivity.this, calendarActivity.class);
			startActivity(intent);
		}
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		return ToolBarCreator.createMenu(this, menu,true);
	}
}

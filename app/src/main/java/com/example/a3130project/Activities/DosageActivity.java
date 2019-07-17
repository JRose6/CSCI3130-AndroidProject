package com.example.a3130project.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import android.widget.TextView;
import android.widget.Toast;

import com.example.a3130project.Adapters.CalendarAdapter;
import com.example.a3130project.R;
import com.example.a3130project.Helpers.ToolBarCreator;
import com.example.a3130project.model.Prescription;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class DosageActivity extends AppCompatActivity
{
	private CalendarAdapter   adapter;
	private FirebaseFirestore database = FirebaseFirestore.getInstance();

	private Intent   intent;
	private TextView dow;
	private String   newDow;
	private Date     nDate;
	private Calendar calendar;


	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dosage);
		ToolBarCreator.createToolbar(this, true, true);
		dow = findViewById(R.id.dow);
		newDow = "";
		intent = getIntent();

		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		newDow = (String) intent.getSerializableExtra("date");
		try
		{
			nDate = format.parse(newDow);

		} catch ( ParseException e )
		{
			Toast.makeText(this, "FAIL", Toast.LENGTH_SHORT).show();
		}


		calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT-3"));
		calendar.setTime(nDate);
		int numDOW = calendar.get(Calendar.DAY_OF_WEEK);


		switch ( numDOW )
		{
		case 1:
			newDow = "Sunday";
			break;
		case 2:
			newDow = "Monday";
			break;
		case 3:
			newDow = "Tuesday";
			break;
		case 4:
			newDow = "Wednesday";
			break;
		case 5:
			newDow = "Thursday";
			break;
		case 6:
			newDow = "Friday";
			break;
		case 7:
			newDow = "Saturday";
			break;
		default:
			break;
		}
		int DayDOW  = calendar.get(Calendar.DAY_OF_MONTH);
		int YearDOW = calendar.get(Calendar.YEAR);
		dow.setText(newDow + " " + DayDOW + ", " + YearDOW);
		setUpRecyclerView(newDow);
	}


	@Override
	protected void onStart()
	{
		super.onStart();
		adapter.startListening();
	}


	@Override
	protected void onResume()
	{
		super.onResume();
		adapter.startListening();
	}


	@Override
	protected void onStop()
	{
		super.onStop();
		adapter.stopListening();
	}


	private void setUpRecyclerView(String newDow)
	{

		String              profileId         = FirebaseAuth.getInstance().getUid();
		String              prescriptionsPath = "profiles/" + profileId + "/prescriptions";
		CollectionReference prescriptRef      = database.collection(prescriptionsPath);

		Query query = prescriptRef.whereEqualTo("weekdays." + newDow, true);

		FirestoreRecyclerOptions<Prescription> options =
				new FirestoreRecyclerOptions.Builder<Prescription>()
						.setQuery(query, Prescription.class).build();

		adapter = new CalendarAdapter(options);

		RecyclerView recyclerView = findViewById(R.id.CalendarRecycler);
		recyclerView.setHasFixedSize(true);
		recyclerView.setLayoutManager(new LinearLayoutManager(this));
		recyclerView.setAdapter(adapter);
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		return ToolBarCreator.createMenu(this, menu, true);
	}
}

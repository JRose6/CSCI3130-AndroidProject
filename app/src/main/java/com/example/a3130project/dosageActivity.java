package com.example.a3130project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class dosageActivity extends AppCompatActivity
{

	private ListView listviewMed;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dosage);

		listviewMed = findViewById(R.id.listViewMed);

		ArrayList<String> arr = new ArrayList<>();

		arr.add("Advil");
		arr.add("Ibuprofen");
		arr.add("Addrerall");
		arr.add("Lisinopril");
		arr.add("Hydrocodone");
		arr.add("Advil");
		arr.add("Ibuprofen");
		arr.add("Addrerall");
		arr.add("Lisinopril");
		arr.add("Hydrocodone");
		arr.add("Advil");
		arr.add("Ibuprofen");
		arr.add("Addrerall");
		arr.add("Lisinopril");
		arr.add("Hydrocodone");
		arr.add("Advil");
		arr.add("Ibuprofen");
		arr.add("Addrerall");
		arr.add("Lisinopril");
		arr.add("Hydrocodone");


		ArrayAdapter arrAdap = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arr);

		listviewMed.setAdapter(arrAdap);

	}

}

package com.example.a3130project;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

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

		final ArrayList<String> arr = new ArrayList<>();

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


		ArrayAdapter arrAdap = new ArrayAdapter(dosageActivity.this, android.R.layout.simple_list_item_1, arr);

		listviewMed.setAdapter(arrAdap);

		listviewMed.setOnItemClickListener(new AdapterView.OnItemClickListener()
		{
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id)
			{
				AlertDialog.Builder Dosage_Change = new AlertDialog.Builder(dosageActivity.this);



				Dosage_Change.setCancelable(true);
				Dosage_Change.setTitle("Dosage Settings");
				Dosage_Change.setMessage(arr.get(position));

				Dosage_Change.setPositiveButton("OK", new DialogInterface.OnClickListener()
				{
					@Override
					public void onClick(DialogInterface dialog, int which)
					{

					}
				});

				Dosage_Change.show();
			}
		});
	}
}

package com.example.a3130project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;


import com.example.a3130project.model.Medication;

public class PrescriptionAdapter extends ArrayAdapter<Medication>
{
	private static final String TAG = "PrescriptionAdapter";
	private Context mContext;
	int mResource;


	public PrescriptionAdapter(Context context, int resource, ArrayList<Medication> objects)
	{
		super(context, resource, objects);
		mContext = context;
		mResource = resource;
	}

	@NonNull
	@Override
	public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
	{
		String name = getItem(position).getName();
		String info = getItem(position).getInfo();
		String id = getItem(position).getId();

		Medication medication = new Medication(id, name, info);

		LayoutInflater inflater = LayoutInflater.from(mContext);
		convertView = inflater.inflate(mResource, parent, false);

		TextView tvName = convertView.findViewById(R.id.textviewMed);
		TextView tvInfo = convertView.findViewById(R.id.textViewDosage);
		//change this
		//TODO
		TextView tvId = convertView.findViewById(R.id.textViewMeasure);

		tvName.setText(name);
		tvInfo.setText(info);
		//TODO
		tvId.setText(id);

		return convertView;
	}
}

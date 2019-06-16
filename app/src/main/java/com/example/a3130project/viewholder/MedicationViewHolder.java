package com.example.a3130project.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.a3130project.R;

public class MedicationViewHolder extends RecyclerView.ViewHolder  {

	public TextView name;
	public TextView email;
	public Button detailsButton;

	public BusinessViewHolder(View view)
	{
		super(view);
		name          = view.findViewById(R.id.businessName);
		email         = view.findViewById(R.id.businessEmail);
		detailsButton = view.findViewById(R.id.goDetails);
	}
}

package com.example.a3130project;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a3130project.model.Prescription;
import com.example.a3130project.viewholder.PrescriptionHolder;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import org.w3c.dom.Text;

public class CalendarAdapter extends FirestoreRecyclerAdapter<Prescription, PrescriptionHolder>
{
	/**
	 * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
	 * FirestoreRecyclerOptions} for configuration options.
	 *
	 * @param options
	 */
	public CalendarAdapter(@NonNull
			                       FirestoreRecyclerOptions<Prescription> options)
	{
		super(options);
	}


	@Override
	protected void onBindViewHolder(
			@NonNull PrescriptionHolder prescriptionHolder, int i,
			@NonNull Prescription prescription)
	{

	}


	@NonNull
	@Override
	public PrescriptionHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
	{
		return null;
	}


	class CalendarHolder extends RecyclerView.ViewHolder {

		TextView textViewMedicationName;
		TextView textViewDosageInfo;
		TextView textViewDoctorInfo;

		public CalendarHolder(View itemView)
		{
			super(itemView);
			textViewMedicationName = itemView.findViewById(R.id.MedicationView);
			textViewDosageInfo = itemView.findViewById(R.id.DosageView);
			textViewDoctorInfo = itemView.findViewById(R.id.DoctorInfoView);
		}
	}

}

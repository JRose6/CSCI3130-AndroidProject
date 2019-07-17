package com.example.a3130project.Helpers;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.a3130project.DBHandlers;
import com.example.a3130project.R;
import com.example.a3130project.model.Prescription;

public class PrescriptionHelper
{
	/**
	 * Takes one dosage from the remiaining medication
	 * @param p The prescription object
	 * @return The prescription object
	 */
	public static Prescription takeDosage(Prescription p){
		p.setRemainingMeds(p.getRemainingMeds()-p.getDosage());
		DBHandlers.prescriptionInsertUpdate(p);
		return p;
	}


	/**
	 * Takes one dosage from remaining medication and sends a message if below the set threshold
	 * @param p The prescription to take
	 * @param ctx The application context
	 * @return The prescription object
	 */
	public static Prescription takeDosage(Prescription p, Context ctx){
	p.setRemainingMeds(p.getRemainingMeds()-p.getDosage());
	DBHandlers.prescriptionInsertUpdate(p);
	if (belowThreshold(p,ctx)){
		sendThresholdNotification(ctx);
	}
	return p;
	}


	/**
	 * Refills the prescription object to the initial capacity
	 * @param p The prescription object
	 * @return The precription object
	 */
	public static Prescription refill(Prescription p){
		p.setRemainingMeds(p.getTotalMeds());
		DBHandlers.prescriptionInsertUpdate(p);
		return p;
	}
	private static boolean belowThreshold(Prescription p, Context ctx){
		SharedPreferences sharedPref
				= ctx
				          .getSharedPreferences(ctx.getString(R.string.preference_file),
				                                               Context.MODE_PRIVATE);
		return p.getRemainingMeds() < sharedPref.getInt(
				ctx.getString(R.string.saved_refill_threshold),
				-1);
	}

	private static void sendThresholdNotification(Context ctx){
		AlertDialog.Builder Refill_Alarm = new AlertDialog.Builder(ctx);

		Refill_Alarm.setCancelable(true);

		Refill_Alarm.setTitle("Refill Alert");
		Refill_Alarm.setMessage(
				"Your medication is below the threshold! Please refill ASAP!");

		Refill_Alarm.show();
	}
}

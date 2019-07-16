package com.example.a3130project.Helpers;

import android.os.SystemClock;

import com.example.a3130project.model.Prescription;

import java.util.ArrayList;
import java.util.Calendar;

public class PrescriptionSorter
{
	/**
	 * Naive sorting algorithm using insertion sort, all sets will be small so the execution time is
	 * trivial.
	 *
	 * @param prescriptions Unsorted list of prescriptions
	 * @return Sorted list of prescriptions
	 */
	public static ArrayList<Prescription> sort(ArrayList<Prescription> prescriptions)
	{
		Calendar today   = Calendar.getInstance();
		long     sysTime = SystemClock.currentThreadTimeMillis();
		int      index   = 0;
		for ( int i = 0; i < prescriptions.size(); i++ )
		{
			Prescription minPrescription = prescriptions.get(i);
			for ( int j = i; j < prescriptions.size(); j++ )
			{
				if ( compare(prescriptions.get(j), minPrescription, today) < 0 )
				{
					Prescription temp = minPrescription;
					minPrescription = prescriptions.get(j);
					prescriptions.set(i, temp);
					prescriptions.set(j, minPrescription);
				}
			}
		}
		return prescriptions;
	}


	private static int compare(Prescription a, Prescription b, Calendar today)
	{
		return -1;
	}
}

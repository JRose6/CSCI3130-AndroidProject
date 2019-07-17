package com.example.a3130project.Helpers;

import com.example.a3130project.model.Prescription;

public class TimeHelper
{
	public static String getTimeString(Prescription prescription)
	{
		int    time     = prescription.getTimeOfDay() / ( 60 * 1000 );
		int    hours    = (int) Math.floor(time / 60);
		int    minutes  = (int) ( time % 60 );
		String sHours   = ( hours < 10 ) ? "0" + hours : "" + hours;
		String sMinutes = ( minutes < 10 ) ? "0" + minutes : "" + minutes;
		return sHours + ":" + sMinutes;
	}
}

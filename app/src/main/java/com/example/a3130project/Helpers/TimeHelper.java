package com.example.a3130project.Helpers;

import com.example.a3130project.model.Prescription;

public class TimeHelper
{
		public static String TimeSwitch(Prescription prescription)
		{
			int time = prescription.getTimeOfDay()/(60*1000);
			String timeStr = ((int)Math.floor(time / 60))+":"+((int)(time % 60));
			return timeStr;
		}

}

package com.example.a3130project.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import com.example.a3130project.R;
import com.example.a3130project.Helpers.ToolBarCreator;

public class SettingsActivity extends AppCompatActivity
{
	private Switch   switchAllowAlarms;
	private EditText editAlarmDelay, editRefill;
	SharedPreferences sharedPref;


	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		ToolBarCreator.createToolbar(this, true, true);
		sharedPref
				= getApplicationContext().getSharedPreferences(getString(R.string.preference_file),
				                                               Context.MODE_PRIVATE);
		Button btnConfirm = (Button) findViewById(R.id.btnConfirmSettings);
		switchAllowAlarms = (Switch) findViewById(R.id.switchAlarm);
		editAlarmDelay = (EditText) findViewById(R.id.editAlarmDelay);
		editRefill = findViewById(R.id.editRefill);
		switchAllowAlarms.setChecked(sharedPref.getBoolean(getString(R.string.saved_alarms_allowed),
		                                                   false));
		editAlarmDelay.setText(String.valueOf(sharedPref.getInt(getString(R.string.saved_alarm_delay),
		                                                        0)));
		editRefill.setText(String.valueOf(sharedPref.getInt(getString(R.string.saved_refill_threshold),
		                                                    0)));
		btnConfirm.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				SharedPreferences.Editor editor = sharedPref.edit();
				editor.putBoolean(getString(R.string.saved_alarms_allowed),
				                  switchAllowAlarms.isChecked());
				String value = editAlarmDelay.getEditableText().toString();
				if ( value.length() != 0 )
				{
					editor.putInt(getString(R.string.saved_alarm_delay), Integer.parseInt(value));
				}
				else
				{
					editor.putInt(getString(R.string.saved_alarm_delay), 0);
				}
				value = editRefill.getEditableText().toString();
				if ( value.length() != 0 )
				{
					editor.putInt(getString(R.string.saved_refill_threshold),
					              Integer.parseInt(value));
				}
				else
				{
					editor.putInt(getString(R.string.saved_refill_threshold), 0);
				}
				editor.commit();
				finish();
			}
		});
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		return ToolBarCreator.createMenu(this, menu, true);
	}
}

package com.example.a3130project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

public class SettingsActivity extends AppCompatActivity
{
	private Switch   switchAllowAlarms;
	private EditText editAlarmDelay;
	SharedPreferences sharedPref;


	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		ToolBarCreator.createToolbar(this);
		sharedPref
				= getApplicationContext().getSharedPreferences(getString(R.string.preference_file),
				                                               Context.MODE_PRIVATE);
		Button btnConfirm = (Button) findViewById(R.id.btnConfirmSettings);
		switchAllowAlarms = (Switch) findViewById(R.id.switchAlarm);
		editAlarmDelay = (EditText) findViewById(R.id.editAlarmDelay);
		switchAllowAlarms.setChecked(sharedPref.getBoolean(getString(R.string.saved_alarms_allowed),
		                                                   false));
		editAlarmDelay.setText(String.valueOf(sharedPref.getInt(getString(R.string.saved_alarm_delay),
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
				int flag;
				if ( switchAllowAlarms.isChecked() )
				{
					flag = PackageManager.COMPONENT_ENABLED_STATE_ENABLED;
				}
				else
				{
					flag = PackageManager.COMPONENT_ENABLED_STATE_DISABLED;
				}

				editor.commit();
				finish();
			}
		});
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		return ToolBarCreator.createMenu(this, menu);
	}
}

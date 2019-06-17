package com.example.a3130project;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;


//import com.example.a3130project.model.Profile;

public class MainActivity extends AppCompatActivity
{
	private Button welcome;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		welcome = findViewById(R.id.buttonWelcome);
		welcome.setOnClickListener(new onClicker());
	}

	public class onClicker implements View.OnClickListener
	{
		@Override
		public void onClick(View v)
		{
			launchLogin();
		}
	}

	public void launchLogin()
	{
		Intent intent = new Intent(this, LoginActivity.class);
		startActivity(intent);
	}

	/**
	 * This is a simple logging function to dump a tag and message to the log surrounded by
	 * empty lines (so it's easier to find)
	 * @param tag
	 * @param message
	 */
	static void logg(String tag, String message)
	{
		Log.w("", "");
		Log.w("", "");
		Log.w(tag, message);
		Log.w("", "");
		Log.w("", "");
	}
} // end class

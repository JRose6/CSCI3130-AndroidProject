package com.example.a3130project;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.widget.Toolbar;
public class MainActivity extends AppCompatActivity
{
	private Button welcome;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		toolbar.showOverflowMenu();
		setSupportActionBar(toolbar);
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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.mainmenu, menu);
		//U can find item set icon and stuff...
		MenuItem item= menu.findItem(R.id.action_settings);
		item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
				startActivity(intent);
				return false;
			}
		});
		return true;
	}

}

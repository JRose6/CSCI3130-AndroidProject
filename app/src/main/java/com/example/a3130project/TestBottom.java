package com.example.a3130project;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;

import android.view.MenuItem;
import android.widget.TextView;

public class TestBottom extends AppCompatActivity
{
	private TextView mTextMessage;

	private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
			= new BottomNavigationView.OnNavigationItemSelectedListener()
	{

		@Override
		public boolean onNavigationItemSelected(@NonNull MenuItem item)
		{
			/*
			switch ( item.getItemId() )
			{
			case R.id.action_calendar:
				//class a = SettingsActivity.class;
				return true;
			case R.id.navigation_dashboard:
				mTextMessage.setText(R.string.title_dashboard);
				return true;
			case R.id.navigation_notifications:
				mTextMessage.setText(R.string.title_notifications);
				return true;
			}
*/
			return false;
		}
	};


	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test_bottom);
		BottomNavigationView navView = findViewById(R.id.nav_view);
		mTextMessage = findViewById(R.id.message);
		navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
	}

}

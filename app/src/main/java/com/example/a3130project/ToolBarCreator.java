package com.example.a3130project;

import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class ToolBarCreator
{
	public static void createToolbar(final AppCompatActivity activity)
	{
		Toolbar toolbar = (Toolbar) activity.findViewById(R.id.toolbar);
		toolbar.showOverflowMenu();
		activity.setSupportActionBar(toolbar);
	}
	private static void selectBottomNavItem(final AppCompatActivity activity,BottomNavigationView nav){
		Log.println(5, "NAV", activity.getClass().toString());
		String name = activity.getClass().toString();
		String[] nameSplit = name.split("[.]");
		name = nameSplit[nameSplit.length-1];
		Log.println(5, "NAV", name);

		switch (name){
		case "MainActivity":
			nav.setSelectedItemId(R.id.action_profile);
			break;
		default:
			nav.setSelectedItemId(R.id.action_calendar);
			break;
		}

		//nav.setSelectedItemId();
		return;
	}
	public static void createBottomNav(final AppCompatActivity activity){
		BottomNavigationView bottomNavigationView = (BottomNavigationView) activity.findViewById(R.id.nav_view);
		selectBottomNavItem(activity,bottomNavigationView);
		bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
			@Override
			public boolean onNavigationItemSelected(@NonNull MenuItem item) {
				Intent intent;
				switch (item.getItemId()) {
				case R.id.action_profile:
					intent = new Intent(activity, MainProfileLoadActivity.class);
					break;
				case R.id.action_calendar:
					intent = new Intent(activity, calendarActivity.class);
					break;
				default:
					intent = new Intent(activity, MainProfileLoadActivity.class);
				}
				activity.startActivity(intent);

				return true;
			}
		});
	}

	public static boolean createMenu(final AppCompatActivity activity, Menu menu)
	{

		activity.getMenuInflater().inflate(R.menu.mainmenu, menu);
		//U can find item set icon and stuff...
		MenuItem item = menu.findItem(R.id.action_settings);
		item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener()
		{
			@Override
			public boolean onMenuItemClick(MenuItem item)
			{
				Intent intent =
						new Intent(activity.getApplicationContext(), SettingsActivity.class);
				activity.startActivity(intent);
				return false;
			}
		});

		MenuItem item2 = menu.findItem(R.id.action_refill);
		item2.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener()
		{
			@Override
			public boolean onMenuItemClick(MenuItem item)
			{
				Intent intent = new Intent(activity.getApplicationContext(), RefillActivity.class);
				activity.startActivity(intent);
				return false;
			}
		});

		MenuItem item3 = menu.findItem(R.id.logout);
		item3.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener()
		{
			@Override
			public boolean onMenuItemClick(MenuItem item)
			{
				FirebaseAuth.getInstance().signOut();
				Intent intent = new Intent(activity.getApplicationContext(), LoginActivity.class);
				activity.startActivity(intent);
				return false;
			}
		});
		return true;
	}
}

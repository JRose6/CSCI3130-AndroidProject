package com.example.a3130project;

import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;

public class ToolBarCreator
{
	public static void createToolbar(final AppCompatActivity activity){
		Toolbar toolbar = (Toolbar) activity.findViewById(R.id.toolbar);
		toolbar.showOverflowMenu();
		activity.setSupportActionBar(toolbar);

	}
	public static boolean createMenu(final AppCompatActivity activity,Menu menu){

		activity.getMenuInflater().inflate(R.menu.mainmenu, menu);
		//U can find item set icon and stuff...
		MenuItem item = menu.findItem(R.id.action_settings);
		item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener()
		{
			@Override
			public boolean onMenuItemClick(MenuItem item)
			{
				Intent intent = new Intent(activity.getApplicationContext(), SettingsActivity.class);
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

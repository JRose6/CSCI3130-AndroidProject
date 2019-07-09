package com.example.a3130project;

import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Class used for creating and handling the navigation in the application
 */
public class ToolBarCreator
{
	private ToolBarCreator() { }


	/**
	 * Method used to create the toolbar on an activity
	 *
	 * @param activity   Activity object, if calling from activity simply pass this
	 * @param showMenu   Whether or not to show the setting menu
	 * @param backButton Whether or not to display the back button
	 * @return Toolbar object
	 */
	public static Toolbar createToolbar(final AppCompatActivity activity, boolean showMenu, boolean backButton)
	{
		Toolbar toolbar = (Toolbar) activity.findViewById(R.id.toolbar);
		if ( showMenu )
		{
			toolbar.showOverflowMenu();
		}
		activity.setSupportActionBar(toolbar);
		if ( backButton )
		{
			toolbar.setNavigationIcon(activity.getDrawable(R.drawable.ic_back));
			toolbar.setNavigationContentDescription(R.string.back_button_desc);
			toolbar.setNavigationOnClickListener(new View.OnClickListener()
			{
				@Override
				public void onClick(View v)
				{
					activity.finish();
				}
			});

		}
		return toolbar;

	}


	private static void selectBottomNavItem(final AppCompatActivity activity, BottomNavigationView nav)
	{
		Log.println(5, "NAV", activity.getClass().toString());
		String   name      = activity.getClass().toString();
		String[] nameSplit = name.split("[.]");
		name = nameSplit[nameSplit.length - 1];
		Log.println(5, "NAV", name);
		switch ( name )
		{
		case "MainProfileLoadActivity":
			nav.setSelectedItemId(R.id.action_profile);
			break;
		case "EditProfileActivity":
			nav.setSelectedItemId(R.id.action_profile);
			break;
		case "MainActivity":
			nav.setSelectedItemId(R.id.action_home);
			break;
		case "AllMedications":
			nav.setSelectedItemId(R.id.action_medication);
			break;
		case "MedicationDetails":
			nav.setSelectedItemId(R.id.action_medication);
			break;
		case "PrescriptionDetails":
			nav.setSelectedItemId(R.id.action_medication);
			break;

		default:
			nav.setSelectedItemId(R.id.action_calendar);
			break;
		}
	}


	/**
	 * Creates a bottom navigation drawer on the activity
	 *
	 * @param activity Current activity
	 */
	public static void createBottomNav(final AppCompatActivity activity)
	{
		BottomNavigationView bottomNavigationView =
				(BottomNavigationView) activity.findViewById(R.id.nav_view);
		selectBottomNavItem(activity, bottomNavigationView);
		bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener()
		{
			@Override
			public boolean onNavigationItemSelected(@NonNull MenuItem item)
			{
				Intent intent;
				switch ( item.getItemId() )
				{
				case R.id.action_profile:
					intent = new Intent(activity, MainProfileLoadActivity.class);
					break;
				case R.id.action_calendar:
					intent = new Intent(activity, calendarActivity.class);
					break;
				case R.id.action_medication:
					intent = new Intent(activity, AllMedications.class);
					break;
				case R.id.action_home:
					intent = new Intent(activity, MainActivity.class);
					break;
				default:
					intent = new Intent(activity, MainProfileLoadActivity.class);
				}
				activity.startActivity(intent);

				return true;
			}
		});
	}


	/**
	 * Creates a menu on the toolbar, Called from onCreateOptionsMenu in activity
	 *
	 * @param activity The current activity
	 * @param menu     The menu object to attach
	 * @param showMenu Boolean indicating if the menu should be shown
	 * @return Boolean indicating success or failure
	 */
	public static boolean createMenu(final AppCompatActivity activity, Menu menu, boolean showMenu)
	{
		if ( !showMenu )
		{
			return true;
		}

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

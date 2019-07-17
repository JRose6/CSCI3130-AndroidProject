package com.example.a3130project;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.test.InstrumentationRegistry;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.filters.LargeTest;
import androidx.test.runner.AndroidJUnit4;

import com.example.a3130project.Activities.NavigationActivity;
import com.example.a3130project.Activities.SettingsActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.InstrumentationRegistry.getTargetContext;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class NavigationTests
{
	SharedPreferences.Editor preferencesEditor;
	SharedPreferences        sharedPref;
	boolean                  alarms_allowed_old;
	int                      alarms_delay;


	@Before
	public void updateSharedPrefs()
	{
		Context context = InstrumentationRegistry.getTargetContext();
		sharedPref
				= context.getSharedPreferences(context.getString(R.string.preference_file),
				                               Context.MODE_PRIVATE);
		alarms_delay = sharedPref.getInt(context.getString(R.string.saved_alarm_delay),
		                                 0);
		alarms_allowed_old =
				sharedPref.getBoolean(context.getString(R.string.saved_alarms_allowed), false);
		preferencesEditor = sharedPref.edit();
		preferencesEditor.putBoolean(context.getString(R.string.saved_alarms_allowed),
		                             false);
		preferencesEditor.putInt(context.getString(R.string.saved_alarm_delay), 0);
		preferencesEditor.commit();

	}


	@After
	public void restoreSharedPrefs()
	{
		Context context = InstrumentationRegistry.getTargetContext();
		preferencesEditor.putBoolean(context.getString(R.string.saved_alarms_allowed),
		                             alarms_allowed_old);
		preferencesEditor.putInt(context.getString(R.string.saved_alarm_delay), alarms_delay);
		preferencesEditor.commit();
	}


	@Rule
	public IntentsTestRule<NavigationActivity> intentsTestRule =
			new IntentsTestRule<>(NavigationActivity.class);


	@Test
	public void useAppContext()
	{
		// Context of the app under test.
		Context appContext = getTargetContext();
		assertEquals("com.example.a3130project", appContext.getPackageName());
	}


	@Test
	public void perform_settingdrawer_navigation()
	{
		onView(withId(R.id.action_home)).perform(click());
		onView(withId(R.id.buttonTestAlarm)).check(matches(isDisplayed()));
		openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
		onView(withText("Settings")).perform(click());
		intended(hasComponent(SettingsActivity.class.getName()));
	}


	@Test
	public void perform_bottomdrawer_navigation()
	{
		onView(withId(R.id.action_calendar)).perform(click());
		//onView(withId(R.id.calendarView)).check(matches(isDisplayed()));

		onView(withId(R.id.action_profile)).perform(click());
		onView(withId(R.id.buttonEditProfile)).check(matches(isDisplayed()));

		onView(withId(R.id.action_home)).perform(click());
		onView(withId(R.id.buttonTestAlarm)).check(matches(isDisplayed()));

		onView(withId(R.id.action_medication)).perform(click());
		onView(withId(R.id.myMedicationRecycler)).check(matches(isDisplayed()));
		onView(withText(R.string.tab_text_2)).perform(click());
		onView(withId(R.id.medicationRecycler)).check(matches(isDisplayed()));

	}


	@Test
	public void perform_settings_update()
	{
		final boolean ALARMS_ALLOWED = true;
		final String  ALARM_DELAY    = "12";
		onView(withId(R.id.action_home)).perform(click());
		openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
		onView(withText("Settings")).perform(click());
		intended(hasComponent(SettingsActivity.class.getName()));

		onView(withId(R.id.switchAlarm)).perform(click());
		onView(withId(R.id.editAlarmDelay)).perform(replaceText(ALARM_DELAY), closeSoftKeyboard());
		onView(withId(R.id.btnConfirmSettings)).perform(click());
		Context context = InstrumentationRegistry.getTargetContext();
		int delay = sharedPref.getInt(context.getString(R.string.saved_alarm_delay),
		                              0);
		boolean allowed =
				sharedPref.getBoolean(context.getString(R.string.saved_alarms_allowed), false);
		assertEquals(ALARMS_ALLOWED, allowed);
		assertEquals(ALARM_DELAY, String.valueOf(delay));

	}

}

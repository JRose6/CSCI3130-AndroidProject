package com.example.a3130project.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import com.example.a3130project.R;
import com.example.a3130project.Helpers.ToolBarCreator;

public class RefillActivity extends AppCompatActivity
{

	Button   TakeButton;
	EditText Takeinput;
	TextView Success;


	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_refill);
		ToolBarCreator.createToolbar(this, true, false);
		Takeinput = findViewById(R.id.input);
		TakeButton = findViewById(R.id.ButtonTake);
		Success = findViewById(R.id.success);

		TakeButton.setOnClickListener(new onClicker());

	}


	public class onClicker implements View.OnClickListener
	{
		@Override
		public void onClick(View v)
		{
			int take   = Integer.parseInt(Takeinput.getText().toString());
			int amount = 100;
			int count  = amount;
			amount = amount - take;
			if ( amount < 0 )
			{
				Success.setText("You don't have enough medication to take, please refill");
			}
			else if ( amount <= 0.2 * count && ( amount >= 0 ) )
			{

				AlertDialog.Builder Refill_Alarm = new AlertDialog.Builder(RefillActivity.this);

				Refill_Alarm.setCancelable(true);

				Refill_Alarm.setTitle("Refill Alert");
				Refill_Alarm.setMessage(
						"Your medication is below the threshold! Please refill ASAP!");

				Refill_Alarm.setPositiveButton("OK", new DialogInterface.OnClickListener()
				{
					@Override
					public void onClick(DialogInterface dialog, int which)
					{

					}
				});

				Refill_Alarm.show();
				Success.setText("You take " + take + " medication! You have " + amount + " left ");
			}

			else
			{
				Success.setText("You take " + take + " medication! You have " + amount + " left ");
			}
		}
	}
}

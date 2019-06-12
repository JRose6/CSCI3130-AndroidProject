package com.example.a3130project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import android.os.Bundle;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity
{


	public EditText logEmail, logPassword;
	public Button signIn, newUser;
//<<<<<<< Updated upstream


//=======
	public TextView passValidator, emailValidator;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		logEmail = findViewById(R.id.email);
		logPassword = findViewById(R.id.password);
		signIn = findViewById(R.id.signIn);
		newUser = findViewById(R.id.newUser);
		passValidator = findViewById(R.id.passwordValid);
		emailValidator = findViewById(R.id.emailValid);


		newUser.setOnClickListener(new onClicker());
		signIn.setOnClickListener(new onClicker1());


//<<<<<<< Updated upstream
//=======
	}
	public class onClicker1 implements View.OnClickListener{
		public void onClick(View v){
			String pass = logPassword.getText().toString();
			String email = logEmail.getText().toString();
			int passRules = PasswordValidator.validPassword(pass);
			int emailRules = EmailValidator.getEmail(email);
			if(passRules==0){
				passValidator.setText("NOT STRONG");
				passValidator.setTextColor(Color.RED);
			}
			if(emailRules==0){
				emailValidator.setText("Invalid email format");
				emailValidator.setTextColor(Color.RED);

			}
			if (passRules < 5 && passRules>0) {

				passValidator.setText("NOT STRONG");
                passValidator.setTextColor(Color.RED);

            }
			if (passRules == 5) {
				passValidator.setText("STRONG");
				passValidator.setTextColor(Color.GREEN);

			}
			if (emailRules==1){
				emailValidator.setText("VALID EMAIL");
				emailValidator.setTextColor(Color.GREEN);

			}

			}
//>>>>>>> Stashed changes
	}

	public class onClicker implements View.OnClickListener
	{
		@Override
		public void onClick(View v)
		{
			launchRegistration();
		}
	}

	public void launchRegistration()
	{

		Intent intent = new Intent(this, RegistrationActivity.class);
		startActivity(intent);
	}

}


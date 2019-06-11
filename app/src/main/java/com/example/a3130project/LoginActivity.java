package com.example.a3130project;

import androidx.appcompat.app.AppCompatActivity;

import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import android.os.Bundle;

public class LoginActivity extends AppCompatActivity
{

	private static final String       TAG = "Login";
	private              FirebaseAuth mAuth;

	public EditText logEmail, logPassword;
	public Button signIn;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		logEmail = findViewById(R.id.email);
		logPassword = findViewById(R.id.password);
		signIn = findViewById(R.id.button);
		mAuth = FirebaseAuth.getInstance();
	}

	@Override
	public void onStart()
	{

		super.onStart();

		// Check if user is signed in (non-null) and update UI accordingly.
		FirebaseUser currentUser = mAuth.getCurrentUser();
		updateUI(currentUser);

	}

	private void updateUI(FirebaseUser user)
	{
		/*hideProgressDialog();
		if (user != null) {
			mStatusTextView.setText(getString(R.string.emailpassword_status_fmt,
					user.getEmail(), user.isEmailVerified()));
			mDetailTextView.setText(getString(R.string.firebase_status_fmt, user.getUid()));

			findViewById(R.id.emailPasswordButtons).setVisibility(View.GONE);
			findViewById(R.id.emailPasswordFields).setVisibility(View.GONE);
			findViewById(R.id.signedInButtons).setVisibility(View.VISIBLE);

			findViewById(R.id.verifyEmailButton).setEnabled(!user.isEmailVerified());
		} else {
			mStatusTextView.setText(R.string.signed_out);
			mDetailTextView.setText(null);

			findViewById(R.id.emailPasswordButtons).setVisibility(View.VISIBLE);
			findViewById(R.id.emailPasswordFields).setVisibility(View.VISIBLE);
			findViewById(R.id.signedInButtons).setVisibility(View.GONE);
		}*/
	}
}


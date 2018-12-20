package attendance.com.schoolmaster.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import attendance.com.schoolmaster.R;

/**
 * A login screen that offers login via username/password.
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    //UI
    private EditText mEdtUsename;
    private EditText mEdtPassword;
    private Button mBtnLogin;

    private final String DEFAULT_USERNAME = "admin";
    private final String DEFAULT_PASSWORD= "admin";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().hide();

        mEdtUsename = (EditText) findViewById(R.id.edt_usename);
        mEdtPassword = (EditText) findViewById(R.id.edt_password);
        mBtnLogin = (Button) findViewById(R.id.btn_login);
        mBtnLogin.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                performlogin();
                return;
            default:
                return;
        }
    }


    private void performlogin() {

        // Reset errors.
        mEdtUsename.setError(null);
        mEdtPassword.setError(null);

        boolean cancel = false;
        View focusView = null;

        if (TextUtils.isEmpty(mEdtPassword.getText().toString())) {
            mEdtPassword.setError(getString(R.string.er_password_required));
            focusView = mEdtPassword;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(mEdtUsename.getText().toString())) {
            mEdtUsename.setError(getString(R.string.er_usename_required));
            focusView = mEdtUsename;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {

            validateCredentials();
        }
    }

    private void validateCredentials() {

        if((DEFAULT_USERNAME).equals(mEdtUsename.getText().toString()) && (DEFAULT_PASSWORD).equals(mEdtPassword.getText().toString()))
        {
           redirectToMainActivity();
        }
        else
        {
            mEdtPassword.setError(getString(R.string.er_incorrect_usrname_or_password));
            mEdtPassword.requestFocus();
        }
    }

    private void redirectToMainActivity() {
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
    }

}


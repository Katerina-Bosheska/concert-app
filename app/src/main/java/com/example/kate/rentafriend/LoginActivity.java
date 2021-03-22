package com.example.kate.rentafriend;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnLogin;
    EditText input_email, input_password;
    TextView btnSignup;

    RelativeLayout activity_login;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //View
        btnLogin = (Button) findViewById(R.id.login_btn);
        input_email = (EditText) findViewById(R.id.login_email);
        input_password = (EditText) findViewById(R.id.login_password);
        btnSignup = (TextView) findViewById(R.id.login_input_signup);
        activity_login = (RelativeLayout) findViewById(R.id.activity_login);

        btnLogin.setOnClickListener(this);
        btnSignup.setOnClickListener(this);

        //Init Firebase Auth
        auth = FirebaseAuth.getInstance();

        //Check if there's already a session; if ok ->  go to mainactivity
//        if(auth.getCurrentUser() != null){
//            startActivity(new Intent(LoginActivity.this, EventsActivity.class));
//        }
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.login_input_signup){
            startActivity(new Intent(this, SignUpActivity.class));
        }

        else if(v.getId() == R.id.login_btn){
            loginUser(input_email.getText().toString(), input_password.getText().toString());
        }
    }

    private void loginUser(String email, String password) {
        auth.signInWithEmailAndPassword(email, password).
                addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(!task.isSuccessful()){
                            Snackbar snackbar = Snackbar.make(activity_login, "Password or email incorrect", Snackbar.LENGTH_SHORT);
                            snackbar.show();
                        }
                        else {
                            startActivity(new Intent(LoginActivity.this, FilterActivity.class));
                        }
                    }
                });
    }

}

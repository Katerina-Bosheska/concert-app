package com.example.kate.rentafriend;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.kate.rentafriend.Firebase.User;
import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnRegister;
    TextView btnLogin;
    EditText input_email, input_password, input_username;

    RelativeLayout activity_signup;

    private FirebaseAuth auth;
    Snackbar snackbar;

    private FirebaseDatabase database;
    private DatabaseReference dbReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        btnRegister = (Button) findViewById(R.id.register_btn);
        btnLogin = (TextView) findViewById(R.id.signup_input_login);
        input_email = (EditText) findViewById(R.id.signup_email);
        input_password = (EditText) findViewById(R.id.signup_password);
        activity_signup = (RelativeLayout) findViewById(R.id.activity_signup);

        btnRegister.setOnClickListener(this);
        btnLogin.setOnClickListener(this);

        auth = FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.signup_input_login){
            startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
            finish();
        }

        else if(v.getId() == R.id.register_btn){
            registerUser(input_email.getText().toString(), input_password.getText().toString());
            finish();
        }
    }

    private void registerUser(final String email, String password) {
        auth.createUserWithEmailAndPassword(email, password).
                addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(!task.isSuccessful()){
                            snackbar = Snackbar.make(activity_signup, "Error: " + task.getException(), Snackbar.LENGTH_SHORT);
                            snackbar.show();
                        }
                        else {
                            snackbar = Snackbar.make(activity_signup, "Your register was successfull!", Snackbar.LENGTH_SHORT);
                            snackbar.show();
                        }
                    }
                });
    }


}

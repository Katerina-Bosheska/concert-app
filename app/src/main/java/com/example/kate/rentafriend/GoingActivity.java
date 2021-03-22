package com.example.kate.rentafriend;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.kate.rentafriend.Firebase.User;
import com.google.firebase.auth.FirebaseAuth;

public class GoingActivity extends AppCompatActivity {

    EditText username, comment;
    Button btnDone;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_going);

        auth = FirebaseAuth.getInstance();

        Bundle extras = getIntent().getExtras();
        final Intent intent = new Intent(this, ConcertDetailsActivity.class);

        intent.putExtra("title", extras.get("title").toString());
        intent.putExtra("date", extras.get("date").toString());
        intent.putExtra("location",extras.get("location").toString());
        intent.putExtra("id", extras.get("id").toString());

        username = (EditText) findViewById(R.id.userName);
        comment = (EditText) findViewById(R.id.userComment);
        btnDone = (Button) findViewById(R.id.btnDone);

        intent.putExtra("userID", auth.getCurrentUser().getUid());
        intent.putExtra("userEmail", auth.getCurrentUser().getEmail());
        intent.putExtra("userName", username.getText().toString());
        intent.putExtra("comment", comment.getText().toString());

        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });
    }
}

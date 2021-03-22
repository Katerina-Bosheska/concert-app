package com.example.kate.rentafriend;

import android.app.Application;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kate.rentafriend.Firebase.User;
import com.example.kate.rentafriend.Firebase.Users;
import com.example.kate.rentafriend.RetrofitMetroAreaID.MetroAreaIDClient;
import com.example.kate.rentafriend.RetrofitMetroAreaID.MetroAreaIdInterface;
import com.example.kate.rentafriend.RetrofitMetroAreaID.Models.JsonResponse;
import com.firebase.client.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FilterActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnSearch;
    EditText city, dateFrom, dateTo;

    FirebaseDatabase database;
    DatabaseReference dbReference;

    Calendar dateSelected;
    private DatePickerDialog datePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        city = (EditText) findViewById(R.id.city);
        dateTo = (EditText) findViewById(R.id.date_to);
        dateFrom = (EditText) findViewById(R.id.date_from);
        btnSearch = (Button) findViewById(R.id.btnSearch);

        btnSearch.setOnClickListener(this);

        dateFrom.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //To show current date in the datepicker
                Calendar mcurrentDate = Calendar.getInstance();
                int mYear = mcurrentDate.get(Calendar.YEAR);
                int mMonth  = mcurrentDate.get(Calendar.MONTH);
                int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog mDatePicker = new DatePickerDialog(FilterActivity.this, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                        // TODO Auto-generated method stub
                        /*      Your code   to get date and time    */
                        String day, month;
                        if(selectedday < 10)
                            day = "0" + selectedday;
                        else day = selectedday + "";
                        if(selectedmonth < 10)
                            month = "0" + (selectedmonth + 1);
                        else month = "" + (selectedmonth + 1);
                        dateFrom.setText(selectedyear + "-" + month + "-" + day);
                    }
                },mYear, mMonth, mDay);
                mDatePicker.setTitle("Select date");
                mDatePicker.show();  }
        });

        dateTo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //To show current date in the datepicker
                Calendar mcurrentDate = Calendar.getInstance();
                int mYear = mcurrentDate.get(Calendar.YEAR);
                int mMonth  = mcurrentDate.get(Calendar.MONTH);
                int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog mDatePicker = new DatePickerDialog(FilterActivity.this, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                        // TODO Auto-generated method stub
                        /*      Your code   to get date and time    */
                        String day, month;
                        if(selectedday < 10)
                            day = "0" + selectedday;
                        else day = selectedday + "";
                        if(selectedmonth < 10)
                            month = "0" + (selectedmonth + 1);
                        else month = "" + (selectedmonth + 1);
                        dateTo.setText(selectedyear + "-" + month + "-" + day);
                    }
                },mYear, mMonth, mDay);
                mDatePicker.setTitle("Select date");
                mDatePicker.show();  }
        });
    }

    @Override
    public void onClick(View v) {
        final String cityname = city.getText().toString();
        final String date_to = dateTo.getText().toString();
        final String date_from = dateFrom.getText().toString();

        MetroAreaIdInterface service = MetroAreaIDClient.getRetrofitInstance().create(MetroAreaIdInterface.class);

        Call<JsonResponse> call = service.getMetroAreaID(cityname);

        call.enqueue(new Callback<JsonResponse>() {
            @Override
            public void onResponse(Call<JsonResponse> call, Response<JsonResponse> response) {
                JsonResponse id = response.body();
                String metro_area_id = id.getResultsPage().getResults().getLocation().getMetroArea().getId();

                Intent intent = intent = new Intent(FilterActivity.this, EventsActivity.class);
                intent.putExtra("cityname", metro_area_id);
                intent.putExtra("date_from", date_from);
                intent.putExtra("date_to", date_to);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<JsonResponse> call, Throwable t) {
                Toast toast = Toast.makeText(getApplicationContext(),"Neuspeshno",Toast.LENGTH_SHORT);
                toast.show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        if(item.getItemId() == R.id.logoff){
            auth.signOut();
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}

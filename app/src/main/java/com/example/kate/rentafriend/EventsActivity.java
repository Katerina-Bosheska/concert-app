package com.example.kate.rentafriend;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kate.rentafriend.RecyclerView.ViewAdapter;
import com.example.kate.rentafriend.RecyclerView.ViewHolder;
import com.example.kate.rentafriend.RetrofitConcerts.ConcertClient;
import com.example.kate.rentafriend.RetrofitConcerts.ConcertInterface;
import com.example.kate.rentafriend.RetrofitConcerts.Models.Event;
import com.example.kate.rentafriend.RetrofitConcerts.Models.JsonResponse;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventsActivity extends AppCompatActivity{

//    TextView username;
//    private FirebaseAuth auth;
//    RelativeLayout activity_events;

    private RecyclerView recyclerView;

    private ViewAdapter viewAdapter;

    private List<Concert> data;

    private LinearLayoutManager layoutManager;

    private TextView btnDetails;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        String cityname = getIntent().getExtras().getString("cityname");
        String date_from = getIntent().getExtras().getString("date_from");
        String date_to = getIntent().getExtras().getString("date_to");
        btnDetails = (TextView) findViewById(R.id.concertDetails);

        data = new ArrayList<Concert>();

        recyclerView = findViewById(R.id.recyclerView);
        viewAdapter = new ViewAdapter(EventsActivity.this, data);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(viewAdapter);

        LoadData(cityname, date_from, date_to);

//        auth = FirebaseAuth.getInstance();
//
//        activity_events = (RelativeLayout) findViewById(R.id.activity_events);
//
//        username = (TextView) findViewById(R.id.userName);
//        if(auth.getCurrentUser() != null){
//            String name = auth.getCurrentUser().getEmail();
//            username.setText(name);
//        }

    }

    private void LoadData(final String cityname, final String date_from, final String date_to) {

        ConcertInterface service = ConcertClient.getRetrofitInstance().create(ConcertInterface.class);

        Call<JsonResponse> call = service.getConcerts(cityname, date_from, date_to);

        call.enqueue(new Callback<JsonResponse>() {
            @Override
            public void onResponse(Call<JsonResponse> call, Response<JsonResponse> response) {
                JsonResponse jsonResponse = response.body();
                List<Event> events = jsonResponse.getResultsPage().getResults().getEvent();
                for(Event event : events){
                    Concert concert = new Concert(event.getDisplayName(), event.getVenue().getMetroArea().getCity(), event.getStart().getDate(), event.getId());
                    concert.lat = event.getLocation().getLat();
                    concert.lng = event.getLocation().getLng();
                    data.add(concert);
                }
                viewAdapter.updateData(data);
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

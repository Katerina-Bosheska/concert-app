package com.example.kate.rentafriend;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.kate.rentafriend.Firebase.Comment;
import com.example.kate.rentafriend.Firebase.Concerts;
import com.example.kate.rentafriend.Firebase.User;
import com.example.kate.rentafriend.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ConcertDetailsActivity extends AppCompatActivity implements OnMapReadyCallback {

    TextView title, date, location, interestedUsers, commentsByUsers;
    EditText username, comment;
    Button btnGoing;

    FirebaseAuth auth;
    FirebaseDatabase database;
    DatabaseReference dbReference;

    String KEY;

    private GoogleMap googleMap = null;
    private Marker marker;

    String lat, lng;

    ArrayList<User> USERS;
    ArrayList<Comment> COMMENTS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_concert_details);

        auth = FirebaseAuth.getInstance();

        title = (TextView) findViewById(R.id.concert_title);
        date = (TextView) findViewById(R.id.concert_date);
        location = (TextView) findViewById(R.id.concert_location);
        //interestedUsers = (TextView) findViewById(R.id.interested);
        //commentsByUsers = (TextView) findViewById(R.id.comments);
        KEY = null;

        username = (EditText) findViewById(R.id.userName);
        comment = (EditText) findViewById(R.id.userComment);

        btnGoing = (Button) findViewById(R.id.btnGoing);

        //get information from EventsActivity
        final Bundle extras = getIntent().getExtras();

        final String concert_title = extras.get("title").toString();
        final String concert_date = extras.get("date").toString();
        final String concert_location = extras.get("location").toString();
        final String concert_id = extras.get("id").toString();
        lat = extras.get("lat").toString();
        lng = extras.get("lng").toString();

        title.setText(concert_title);
        date.setText(concert_date);
        location.setText(concert_location);

        //GOOGLE MAP
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //Save data to database
        database = FirebaseDatabase.getInstance();
        dbReference = database.getReference("concert");

        dbReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> dataSnapshots = dataSnapshot.getChildren();

                for(DataSnapshot ds : dataSnapshots){
                    Concert concert = ds.getValue(Concert.class);

                    if(concert.getID().equals(concert_id)){
                        KEY = ds.getKey();

                        USERS = concert.getUsers();
                        UserAdapter userAdapter  = new UserAdapter();
                        ListView listView = (ListView) findViewById(R.id.listUsers);
                        listView.setAdapter(userAdapter);
                        //interestedUsers.setText(concert.usersToString());
                        //commentsByUsers.setText(concert.commentsToString());
                        COMMENTS = concert.getComments();
                        CommentAdapter commentAdapter = new CommentAdapter();
                        ListView listViewComment = (ListView) findViewById(R.id.listComments);
                        listViewComment.setAdapter(commentAdapter);

                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        btnGoing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String userName = username.getText().toString();
                String commentText = comment.getText().toString();

                final User user = new User(auth.getCurrentUser().getUid(), auth.getCurrentUser().getEmail(), userName);
                final Comment userComment = new Comment(commentText, user);

                if(KEY == null){
                    dbReference = database.getReference("concert");
                    String key = dbReference.push().getKey();

                    Concert concert = new Concert(concert_title, concert_location, concert_date, concert_id);
                    concert.addUser(user);
                    concert.addComment(userComment);

                    dbReference.child(key).setValue(concert);
                }

                else {
                    dbReference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            Concert concert = dataSnapshot.child(KEY).getValue(Concert.class);
                            concert.addUser(user);
                            concert.addComment(userComment);

                            dbReference.child(KEY).setValue(concert);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }

            }
        });
    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {
        this.googleMap = googleMap;
        setGoogleMap();
    }

    public void setGoogleMap(){
        if(lat != null && lng != null){
            LatLng current = new LatLng(Double.parseDouble(lat), Double.parseDouble(lng));
            final MarkerOptions markerOptions = new MarkerOptions()
                    .position(current)
                    .draggable(false)
                    .title("Location of venue");
            marker = googleMap.addMarker(markerOptions);
            marker.setPosition(new LatLng(Double.parseDouble(lat), Double.parseDouble(lng)));
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(marker.getPosition(), 12f));
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    public class UserAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return USERS.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {

            //view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.user_list_item, viewGroup, false);
            view = getLayoutInflater().inflate(R.layout.user_list_item, null);

            TextView username = (TextView) view.findViewById(R.id.userListName);
            TextView email = (TextView) view.findViewById(R.id.userListMail);

            username.setText(USERS.get(i).getUserName());
            email.setText(USERS.get(i).getUserEmail());

            return view;
        }
    }

    public class CommentAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return COMMENTS.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {

            //view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.user_list_item, viewGroup, false);
            view = getLayoutInflater().inflate(R.layout.comment_list_item, null);

            TextView username = (TextView) view.findViewById(R.id.commentListName);
            final TextView email = (TextView) view.findViewById(R.id.commentListMail);
            TextView comment = (TextView) view.findViewById(R.id.listComment);

            username.setText(COMMENTS.get(i).getUser().getUserName());
            email.setText(COMMENTS.get(i).getUser().getUserEmail());
            comment.setText(COMMENTS.get(i).getComment());

            TextView btnMsg = (TextView) view.findViewById(R.id.btnMsg);
            btnMsg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String[] mails = { email.getText().toString() };
                    Intent sendMailIntent = new Intent(Intent.ACTION_SEND);
                    sendMailIntent.setType("text/plain");
                    sendMailIntent.putExtra(Intent.EXTRA_TITLE, "Concert Date Invite");
                    sendMailIntent.putExtra(Intent.EXTRA_SUBJECT, "Concert Date Invite");
                    sendMailIntent.putExtra(Intent.EXTRA_EMAIL, mails);
                    startActivity(sendMailIntent);
                }
            });

            return view;
        }
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

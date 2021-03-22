package com.example.kate.rentafriend.RecyclerView;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.kate.rentafriend.R;

public class ViewHolder extends RecyclerView.ViewHolder {

    private TextView title, location, date, id;
    private View parent;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        this.title = (TextView) itemView.findViewById(R.id.item_title);
        this.location = (TextView) itemView.findViewById(R.id.item_location);
        this.date = (TextView) itemView.findViewById(R.id.item_date);
        //this.info = (TextView) itemView.findViewById(R.id.item_info);
    }

    public TextView getTitle(){ return title; }
    public TextView getLocation() { return location; }
    public TextView getDate() { return date; }
    public TextView getID() { return id; }

    public void setTitle(TextView title) {
        this.title = title;
    }

    public void setLocation(TextView location) {
        this.location = location;
    }

    public void setDate(TextView date) {
        this.date = date;
    }

    public void setInfo(TextView id) {
        this.id = id;
    }

    public View getParent() {
        return parent;
    }

    public void setParent(View parent) {
        this.parent = parent;
    }
}

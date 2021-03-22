package com.example.kate.rentafriend.RecyclerView;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import com.example.kate.rentafriend.Concert;
import com.example.kate.rentafriend.ConcertDetailsActivity;
import com.example.kate.rentafriend.R;

import java.util.List;
import android.content.Context;
import android.widget.TextView;

public class ViewAdapter extends RecyclerView.Adapter<ViewHolder> {

    private List<Concert> data;
    private Context context;

    public ViewAdapter(Context context, List<Concert> data) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.setParent(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final Concert entity = data.get(i);
        TextView title = viewHolder.getTitle();
        viewHolder.getTitle().setText(entity.getTitle());
        viewHolder.getLocation().setText(entity.getLocation());
        viewHolder.getDate().setText(entity.getDate());
        //viewHolder.getID().setText(entity.getID());

        viewHolder.getParent().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ConcertDetailsActivity.class);
                intent.putExtra("id", entity.getID());
                intent.putExtra("title", entity.getTitle());
                intent.putExtra("location", entity.getLocation());
                intent.putExtra("date", entity.getDate());
                intent.putExtra("lat", entity.lat);
                intent.putExtra("lng", entity.lng);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void updateData(List<Concert> list){
        this.data = list;
        notifyDataSetChanged();
    }
}

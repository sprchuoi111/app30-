package com.example.bt5_list_view;
//using base adapter

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Rooms_adapter extends BaseAdapter {
    private Context context;  // Reference to the application context
    private int layout;       // Layout resource ID for each item in the list
    private List<Rooms> Roomlist;  // List of room objects to be displayed in the ListView

    // Constructor to initialize the adapter with context, layout, and data
    public Rooms_adapter(Context context, int layout, List<Rooms> Roomlist) {
        this.context = context;
        this.layout = layout;
        this.Roomlist = Roomlist;
    }

    @Override
    public int getCount() {
        return Roomlist.size();  // Return the number of items in the list
    }

    @Override
    public Object getItem(int i) {
        return null;  // Not used in this implementation
    }

    @Override
    public long getItemId(int i) {
        return 0;  // Not used in this implementation
    }

    // ViewHolder pattern: Used for efficient recycling of views
    private class ViewHolder {
        TextView txt_img;
        TextView txt_des;
        ImageView image;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;

        if (view == null) {
            // If the view is null, it's the first time this view is being created.
            // We inflate the layout and create a ViewHolder to hold references to its views.

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);

            holder = new ViewHolder();
            holder.txt_img = (TextView) view.findViewById(R.id.txt_img);
            holder.txt_des = (TextView) view.findViewById(R.id.txt_des);
            holder.image = (ImageView) view.findViewById(R.id.image);

            view.setTag(holder);  // Store the ViewHolder in the view's tag for recycling

            Rooms rooms = Roomlist.get(i);
            holder.txt_img.setText(rooms.getName());
            holder.txt_des.setText(rooms.getDescription());
            holder.image.setImageResource(rooms.getImg());
        } else {
            // If the view is not null, it has already been created and can be recycled.
            // We retrieve the ViewHolder from the view's tag.

            holder = (ViewHolder) view.getTag();
        }

        return view;
    }
}


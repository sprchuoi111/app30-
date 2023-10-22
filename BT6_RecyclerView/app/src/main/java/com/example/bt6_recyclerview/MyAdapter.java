package com.example.bt6_recyclerview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder>{
    private  Context context;
    private List<Rooms> rooms ;
    private SelectListener listener;
    private MyAdapter myAdapter;

    public MyAdapter(Context context, List<Rooms> rooms, SelectListener listener) {
        this.context = context;
        this.rooms = rooms;
        this.listener = listener;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.room_layout,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder,int position) {
        holder.room_name.setText(rooms.get(position).getName());
        holder.des.setText((rooms.get(position).getDes()));
        holder.img_room.setImageResource(rooms.get(position).getImg());
        //Bind Holder for frame
        // button using card view set on click listener
        holder.card_rooms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClicked(rooms.get(position));

            }
        });
        // Long click remove
        holder.card_rooms.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                listener.onItemLongClicked(rooms.get(position));
                return false;
            }
        });
        //btn popup menu room
        holder.btn_popup_menu_room.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popup_menu_room;
                popup_menu_room= new PopupMenu(context,holder.btn_popup_menu_room);
                popup_menu_room.inflate(R.menu.popup_menu_room);
                popup_menu_room.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        final int pos = holder.getAdapterPosition();
                        int id = menuItem.getItemId();
                        if(id == R.id.remove_room){
                            rooms.remove(pos);
                            myAdapter.notifyItemRemoved(holder.getAdapterPosition());
                        } else if (id == R.id.adjust_room) {
                            Toast.makeText(context,"Adjust",Toast.LENGTH_SHORT).show();

                        }
                        return false;
                    }
                });
                popup_menu_room.show();

            }
        });
    }
    @Override
    public int getItemCount() {
        return rooms.size();
    }
}

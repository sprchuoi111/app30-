package com.example.final_ex.viewholder;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.final_ex.Interface.SelectListener;
import com.example.final_ex.R;
import com.example.final_ex.object.Room;

import java.util.List;

public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.RoomViewHolder> {
    private Context mContext; // A reference to the application context.
    private SelectListener mOnClickItemListener; // An interface for handling item click events.

    // Create an interface for item clicked events.

    private List<Room> mListRoom; // A list of Room objects to be displayed.

    // Constructor for the RoomAdapter class.
    public RoomAdapter(List<Room> mListRoom, SelectListener mOnClickItemListener) {
        this.mOnClickItemListener = mOnClickItemListener; // Initialize the click listener.
        this.mListRoom = mListRoom; // Initialize the list of rooms.
    }

    // Create a new ViewHolder for a room item.
    @NonNull
    @Override
    public RoomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_room, parent, false);
        return new RoomViewHolder(view); // Inflate the item_room layout and return a new RoomViewHolder.
    }

    // Bind data to a ViewHolder.
    @Override
    public void onBindViewHolder(@NonNull RoomViewHolder holder, int position) {
        // Get the room at the specified position in the list.
        Room room = mListRoom.get(position);
        // Save the item's position.
        int itemPosition = holder.getAdapterPosition();
        // Check if the room is empty.
        if (room == null) {
            return;
        }
        // Set the room name and number of devices in the UI.
        holder.txt_room.setText(room.getName());
        holder.txt_number_device.setText("Device: " + room.getNumber());

        // Set a click listener for the room item.
        holder.card_room.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Trigger the click event by passing the room and position to the onClickItemListener.
                mOnClickItemListener.onClickItemRoom(room, itemPosition);
            }
        });

        holder.card_room.setOnLongClickListener(v -> {
            // Get adapter position of ViewHolder in RecyclerView and assign it to 'currentPosition'.
            int currentPosition = holder.getAdapterPosition();

            // Create a builder for an alert dialog that uses default alert dialog theme.
            new AlertDialog.Builder(v.getContext())
                    .setTitle("Delete Room") // Set title text for dialog.
                    .setMessage("Are you sure you want to delete this Room?") // Set message text for dialog.
                    // Add positive button to dialog with text "OK" and click listener.
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        // This method is called when positive button is clicked.
                        public void onClick(DialogInterface dialog, int which) {
                            //If 'currentPosition' is a valid position

                            if (currentPosition != RecyclerView.NO_POSITION) {
                                // Remove the room at 'currentPosition' from mListRoom.
                                mListRoom.remove(currentPosition);
                                notifyDataSetChanged();
                                // Notify RoomAdapter that underlying data has changed and it should refresh itself.
                            }
                        }
                    })
                    // Add negative button to dialog with text "Cancel" and null click listener.
                    .setNegativeButton(android.R.string.cancel, null)
                    // Set icon for dialog using a drawable resource.
                    .setIcon(android.R.drawable.ic_menu_delete)
                    // Show this dialog, adding it to the screen.
                    .show();
            return true;
        });
    }

    // Return the number of items in the list.
    @Override
    public int getItemCount() {
        return mListRoom.size();
    }

    // The RoomViewHolder class represents individual items in the RecyclerView.
    public class RoomViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout layoutRoom;
        private TextView txt_room;
        private TextView txt_number_device;
        private CardView card_room;

        public RoomViewHolder(@NonNull View itemView) {
            super(itemView);
            layoutRoom = itemView.findViewById(R.id.layoutRoom);
            txt_room = itemView.findViewById(R.id.txt_room);
            txt_number_device = itemView.findViewById(R.id.txt_number_device);
            card_room = itemView.findViewById(R.id.card_room);
        }
    }
}

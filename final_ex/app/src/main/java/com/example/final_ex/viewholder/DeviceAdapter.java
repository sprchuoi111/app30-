package com.example.final_ex.viewholder;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.final_ex.MainActivity;
import com.example.final_ex.R;
import com.example.final_ex.object.Device;
import com.example.final_ex.object.Room;

import java.util.List;

public class DeviceAdapter extends RecyclerView.Adapter<DeviceAdapter.DeviceViewHolder> {
    private Context context;
    private List<Device> list_device;

    public DeviceAdapter(List<Device> list_device) {
        this.list_device = list_device;
    }

    @NonNull
    @Override
    public DeviceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.device_turn_on_of,parent,false);
        return new DeviceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DeviceViewHolder holder, int position) {
        Device device = list_device.get(position);
        if (device == null)
            return;
        holder.txt_led.setText(device.getName());
        holder.img_led.setImageResource(device.setImg(R.drawable.led_off));
        holder.sk_led.setVisibility(View.GONE);
        holder.itemView.setOnLongClickListener(v -> {
            // Get adapter position of ViewHolder in RecyclerView and assign it to 'currentPosition'.
            int currentPosition = holder.getAdapterPosition();

            // Create a builder for an alert dialog that uses default alert dialog theme.
            new AlertDialog.Builder(v.getContext())
                    .setTitle("Delete Device") // Set title text for dialog.
                    .setMessage("Are you sure you want to delete this Device?") // Set message text for dialog.
                    // Add positive button to dialog with text "OK" and click listener.
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        // This method is called when positive button is clicked.
                        public void onClick(DialogInterface dialog, int which) {
                            //If 'currentPosition' is a valid position

                            if (currentPosition != RecyclerView.NO_POSITION) {
                                // Remove the room at 'currentPosition' from mListRoom.
                                list_device.remove(currentPosition);
                                notifyDataSetChanged();
                                // Notify RoomAdapter that underlying data has changed and it should refresh itself.
                                holder.mainActivity.saveRoomList(Room.globalRooms,"listRoom");
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
        holder.sw_led.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(holder.sw_led.isChecked()){
                    holder.img_led.setImageResource(device.setImg(R.drawable.led_on));
                    holder.sk_led.setVisibility(View.VISIBLE);
                }
                else  {
                    holder.img_led.setImageResource(device.setImg(R.drawable.led_off));
                    holder.sk_led.setVisibility(View.GONE);
                }
            }
        });
        holder.sk_led.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // Calculate alpha value in the range 0-255
                int alpha = (int) (progress * 2.55); // 2.55 = 255 / 100
                holder.img_led.setImageAlpha(alpha);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // Do something when tracking starts, if needed
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // Do something when tracking stops, if needed
            }
        });

    }
    // return size of device in list
    @Override
    public int getItemCount() {
        if (list_device != null) {
            return list_device.size();
        }
        return 0;
    }
    // create viewholder for recyclerView of Devices
    public class DeviceViewHolder extends RecyclerView.ViewHolder{

        private TextView txt_led;
        private Switch sw_led;
        private ImageView img_led;
        private SeekBar sk_led;
        private MainActivity mainActivity;
        public DeviceViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_led = itemView.findViewById(R.id.txt_led);
            sw_led = itemView.findViewById(R.id.sw_led);
            img_led = itemView.findViewById(R.id.img_led);
            sk_led = itemView.findViewById(R.id.sk_led);
            mainActivity = (MainActivity) itemView.getContext();
        }
    }
}

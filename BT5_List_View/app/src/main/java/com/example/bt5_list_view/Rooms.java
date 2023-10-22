package com.example.bt5_list_view;

public class Rooms {
    private String name;         // Name of the room
    private String description;  // Description of the room
    private int img;             // Resource ID of the room's image

    // Constructor to create a Rooms object with a name, description, and image
    public Rooms(String name, String description, int img) {
        this.name = name;
        this.description = description;
        this.img = img;
    }

    // Getter method to retrieve the name of the room
    public String getName() {
        return name;
    }

    // Setter method to set the name of the room
    public void setName(String name) {
        this.name = name;
    }

    // Getter method to retrieve the description of the room
    public String getDescription() {
        return description;
    }

    // Setter method to set the description of the room
    public void setDescription(String description) {
        this.description = description;
    }

    // Getter method to retrieve the resource ID of the room's image
    public int getImg() {
        return img;
    }

    // Setter method to set the resource ID of the room's image
    public void setImg(int img) {
        this.img = img;
    }
}


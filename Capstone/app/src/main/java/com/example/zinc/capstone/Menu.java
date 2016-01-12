package com.example.zinc.capstone;

/**
 * Created by zinc on 16. 1. 12..
 */
public class Menu {
    private String date;
    private String time;    //breakfast, lunch, or dinner
    private String cafeteria_type;
    private String menu;
    private String price;

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getCafeteria_type() {
        return cafeteria_type;
    }

    public String getMenu() {
        return menu;
    }

    public String getPrice() {
        return price;
    }
    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setCafeteria_type(String cafeteria_type) {
        this.cafeteria_type = cafeteria_type;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public void setPrice(String price) {
        this.price = price;
    }

}

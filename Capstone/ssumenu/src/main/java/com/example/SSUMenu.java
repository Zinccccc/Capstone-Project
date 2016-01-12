package com.example;

import com.google.gson.Gson;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class SSUMenu {

    public String getMenuJson(){
        String result = "";
        ArrayList<Menu> menuArrayList;
        Gson gson = new Gson();

        menuArrayList = parseHTML();

        for(int i = 0; i < menuArrayList.size(); i++){
            System.out.println(menuArrayList.get(i).getMenu());
        }
        result = gson.toJson(menuArrayList);

        return result;
    }



    //get the date of today
    public String getDate(int tomorrow){
        Date d = new Date();
        if(tomorrow == 1){
            d = new Date (d.getTime()+(long)(1000*60*60*24));
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(d);
    }

    //get the menus of today and tomorrow
    public ArrayList<Menu> parseHTML() {
        String type;
        String price;
        String menuStr;
        String day;
        String mType;
        String[] menuType = {"Breakfast", "Lunch", "Lunch2", "Lunch3", "Dinner"};
        int cnt = 0;
        int day_flag = 0;

        ArrayList<Menu> menuArrayList = new ArrayList<>();

        try {
            //connect with web page
            Document doc = Jsoup.connect("http://m.ssu.ac.kr/html/themes/m/html/etc_menulist.jsp").get();

            do {
                day = getDate(day_flag);
                //select Today's menu
                Elements dayMenu = doc.select(".weeklymenu-" + day);
                System.out.println("day : " + day);
                for (Element timeMenu : dayMenu) {
                    cnt = 0;
                    Elements tmenu = timeMenu.select(".frame-b");
                    for (Element t : tmenu) {
                        mType = menuType[cnt++];
                        System.out.println("title : " + mType);
                        Elements cafeteria_type = t.select(".basic");
                        for (Element e : cafeteria_type) {
                            Menu menu = new Menu();
                            menu.setDate(day);
                            menu.setTime(mType);
                            type = e.select("strong").first().text();
                            menu.setCafeteria_type(type);
                            System.out.println("Cafeteria_type : " + type);

                            price = e.select("strong").last().text();
                            menu.setPrice(price);
                            System.out.println("Price : " + price);

                            // the data format of food court is different from others
                            if (price != null)
                                menuStr = e.text().substring(type.length() + 1, e.text().length() - price.length() - 1);
                            else
                                menuStr = e.text().substring(type.length() + 1, e.text().length());

                            menu.setMenu(menuStr);
                            System.out.println("menu : " + menuStr);
                            menuArrayList.add(menu);
                        }
                    }
                }
                day_flag++;
            }while(day_flag == 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return menuArrayList;
    }
}
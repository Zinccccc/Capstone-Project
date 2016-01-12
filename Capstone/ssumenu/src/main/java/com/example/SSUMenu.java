package com.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class SSUMenu {
    //get the date of today
    public String getDate(int tomorrow){
        Date d = new Date();
        if(tomorrow == 1){
            d = new Date (d.getTime()+(long)(1000*60*60*24));
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(d);
    }

    public String getMenu() {
        String type;
        String price;
        String menuStr;
        String today = getDate(0);
        String tomorrow = getDate(1);
        String[] menuType = {"Breakfast", "Lunch", "Lunch2", "Lunch3", "Dinner"};
        int cnt = 0;

        ArrayList<Menu> menuArrayList = new ArrayList<>();
        Menu menu = new Menu();

        try {
            //connect with web page
            Document doc = Jsoup.connect("http://m.ssu.ac.kr/html/themes/m/html/etc_menulist.jsp").get();
            parseResult(doc);

            //select Today's menu
            Elements todayMenu = doc.select(".weeklymenu-"+today);
            menu.setDate(today);
            System.out.println("today : " + today);
            for(Element timeMenu:todayMenu){
                cnt = 0;
                Elements tmenu = timeMenu.select(".frame-b");
                for(Element t:tmenu) {
                    menu.setTime(menuType[cnt]);
                    System.out.println("title : " + menuType[cnt++]);
                    Elements cafeteria_type = t.select(".basic");
                    for (Element e : cafeteria_type) {
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

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Hello!";
    }
    public void parseResult(Document doc){

    }
}
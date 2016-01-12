package com.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.SimpleDateFormat;
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
        Menu menu = new Menu();
        String today = getDate(0);
        String tomorrow = getDate(1);
        try {
            //connect with web page
            Document doc = Jsoup.connect("http://m.ssu.ac.kr/html/themes/m/html/etc_menulist.jsp").get();
            parseResult(doc);

            //select Today's menu
            Elements todayMenu = doc.select(".weeklymenu-"+today);
            menu.setDate(today);
            System.out.println("today : " + today);
            for(Element timeMenu:todayMenu){
                menu.setTime(timeMenu.select(".title").text());
                System.out.println("title : " + timeMenu.select(".title").text());
                Elements cafeteria_type = timeMenu.select(".basic");
                for(Element e:cafeteria_type){
                    menu.setCafeteria_type(e.select("strong").first().text());
                    System.out.println("Cafeteria_type : " + e.select("strong").first().text());

                    menu.setMenu(e.text());
                    System.out.println("menu : "+e.text());


                    menu.setPrice(e.select("strong").last().text());
                    System.out.println("Price : " + e.select("strong").last().text());
                }
//                e.select("title");
//                System.out.println(e.select(".title"));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Hello!";
    }
    public void parseResult(Document doc){

    }
}
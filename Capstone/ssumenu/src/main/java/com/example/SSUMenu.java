package com.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class SSUMenu {
    public String getMenu() {
        String text = "";
        try {
            Document doc = Jsoup.connect("http://m.ssu.ac.kr/html/themes/m/html/etc_menulist.jsp").get(); //웹에서 내용을 가져온다.
            //			<div class="weeklymenu weeklymenu-2016-01-12" style="display:none;">

//            Elements contents = doc.select("weeklymenu-2016-01-12");
//            Elements contents = doc.select("div.tt_article_useless_p_margin"); //내용 중에서 원하는 부분을 가져온다.
            text = doc.text(); //원하는 부분은 Elements형태로 되어 있으므로 이를 String 형태로 바꾸어 준다.
            System.out.println(text);
        } catch (IOException e) { //Jsoup의 connect 부분에서 IOException 오류가 날 수 있으므로 사용한다.
            e.printStackTrace();
        }
        return "Hello";
    }
}

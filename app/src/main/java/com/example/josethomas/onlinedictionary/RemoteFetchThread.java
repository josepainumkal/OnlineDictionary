package com.example.josethomas.onlinedictionary;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.Locale;

/**
 * Created by JoseThomas on 3/16/2016.
 */
public class RemoteFetchThread {

    public static String getWordMeaning(String word){

        String result = "";

        if(word.isEmpty()){
            return  result;
        }

        try{

            String DICTIONARY_URL ="http://www.bing.com/search?q=meaning+"+word;

            Document doc = Jsoup.connect(DICTIONARY_URL)
                    .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                    .referrer("http://www.bing.com")
                    .get();

            //  Document doc = Jsoup.connect(DICTIONARY_URL).userAgent("Chrome").get();
            Elements text = doc.select("div[class=dcont]");

            Elements content = doc.getElementsByClass("dc_pd");
            for(Element links: content){
                Elements dc_st = links.select("div[class=dc_st]");

                result= result + "<b>"+dc_st.text() + "</b><br>";
                //System.out.println(dc_st.text());

                Elements dc_bld = links.select("span[class=dc_bld]").select("div[class=dc_nml]").select("div[class=dc_pm]");

                for(Element dc_nml: dc_bld){
                    Elements dc_mns = dc_nml.select("div[class=dc_mn]");
                    for(Element dc_mn: dc_mns){
                        //System.out.println(dc_mn.text());
                        result= result + dc_mn.text() + "<br>";
                    }
                }
                result = result + "<br>";

            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return result;
    }
}

package com.example;

import java.io.FileReader;
import javax.print.DocFlavor.STRING;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;


/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        JSONsimple();
        System.out.println("json end");
    }



    static void JSONsimple() {
        // JSONObject js = new JSONObject();
        // parsing file "JSONExample.json"
        try {
            Object ob = new JSONParser().parse(new FileReader("data.json"));
            JSONObject js = (JSONObject) ob;
            JSONObject js1 = (JSONObject) js.get("hits");
            JSONArray js2 = (JSONArray) js1.get("hits");
            for (int i = 0; i < js2.size(); i++) {
                JSONObject js3 = (JSONObject) js2.get(i);
                JSONObject js4 = (JSONObject) js3.get("_source");
                System.out.println(js4.get("ecaNo"));
            } ;
            System.out.println("Toatal eca count : " + js2.size());

        } catch (Exception e) {
            System.out.println("file read error!!!=>                 " + e);
        }
    }
}


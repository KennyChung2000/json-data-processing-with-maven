package com.example;

import java.io.FileReader;
import java.util.ArrayList;
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
        ArrayList<String> dataList1 = EcaJSONsample("data.json", "ecaNo");
        ArrayList<String> dataList2 = EcaJSONsample("data2.json", "ecaNo");
        ArrayList<String> differenceList = Compare(dataList1, dataList2);
        for (String s : differenceList)
            System.out.println(s);
        System.out.println("json end");
    }



    static ArrayList<String> EcaJSONsample(String file, String compareType) {
        // JSONObject js = new JSONObject();
        // parsing file "JSONExample.json"
        ArrayList<String> list = new ArrayList<String>();
        try {
            Object ob = new JSONParser().parse(new FileReader(file));
            JSONObject js = (JSONObject) ob;
            JSONObject js1 = (JSONObject) js.get("hits");
            JSONArray js2 = (JSONArray) js1.get("hits");
            for (int i = 0; i < js2.size(); i++) {
                JSONObject js3 = (JSONObject) js2.get(i);
                JSONObject js4 = (JSONObject) js3.get("_source");
                // System.out.println(js4.get("ecaNo"));
                list.add(js4.get(compareType).toString());
            } ;
            System.out.println(file + "     " + "Toatal eca count : " + js2.size());

        } catch (Exception e) {
            System.out.println("file read error!!!=>                 " + e);
        }
        return list;
    }

    static ArrayList<String> Compare(ArrayList<String> list1, ArrayList<String> list2) {
        ArrayList<String> list = new ArrayList<String>();
        for (String s : list1) {
            if (!list2.contains(s)) {
                list.add(s);
            }
        }
        for (String s : list2) {
            if (!list1.contains(s)) {
                list.add(s);
            }
        }
        return list;
    }
}


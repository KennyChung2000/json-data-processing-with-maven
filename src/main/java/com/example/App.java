package com.example;

import java.io.FileReader;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;


/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        ArrayList<String> dataList_ES = EcJSONsample_ES("ES.json", "ecaNo");
        ArrayList<String> dataList2_DB = EcJSONsample_DB("ER.json", "ecr_no");
        ArrayList<String> differenceList = Compare(dataList2_DB, dataList_ES);
        int different = 0;
        for (String s : differenceList) {
            System.out.println(s);
            different += 1;
        }
        System.out.println("different=" + different);
        // System.out.println("json end");
    }



    static ArrayList<String> EcJSONsample_ES(String file, String compareType) {
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

    static ArrayList<String> EcJSONsample_DB(String file, String compareType) {
        // JSONObject js = new JSONObject();
        // parsing file "JSONExample.json"
        ArrayList<String> list = new ArrayList<String>();
        try {
            Object ob = new JSONParser().parse(new FileReader(file));
            JSONObject js = (JSONObject) ob;
            JSONArray js2 = (JSONArray) js.get("items");
            for (int i = 0; i < js2.size(); i++) {
                JSONObject js3 = (JSONObject) js2.get(i);
                // System.out.println(js3.get("ecr_no"));
                list.add(js3.get("ecr_no").toString());
            } ;
            System.out.println(file + "     " + "Toatal eca count : " + js2.size());

        } catch (Exception e) {
            System.out.println("file read error!!!=>                 " + e);
        }
        return list;
    }

    static ArrayList<String> Compare(ArrayList<String> list_DB, ArrayList<String> list_ES) {
        ArrayList<String> list = new ArrayList<String>();
        for (String Es : list_ES) {
            if (!list_DB.contains(Es)) {
                list.add(Es);
            }
        }
        return list;
    }
}


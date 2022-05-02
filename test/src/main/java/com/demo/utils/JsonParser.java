package com.demo.utils;

import com.demo.test.Player;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class JsonParser
{
    public static HashMap<String,Object> jsonToHashMap(String json)
    {
        HashMap<String,Object> map = new HashMap<>();
        try {
            map = new ObjectMapper().readValue(json, HashMap.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return map;
    }

    public static JSONArray readJsonFile(String filePath) {

        JSONParser parser = new JSONParser();
        JSONArray res = null;
        try {
             res = (JSONArray) parser.parse(new FileReader(filePath + "/JsonFiles/Cache.json"));
        } catch (FileNotFoundException | ParseException e) {
            e.printStackTrace();
        }
        return res != null ? res : null;
    }

    public static void addToJson(Player player,String filePath)
    {
        JSONObject valuesObject = new JSONObject();
        JSONArray list = readJsonFile(filePath);
        valuesObject.put(Constants.ID, player.getId());
        valuesObject.put(Constants.FIRST_NAME, player.getFirstName());
        valuesObject.put(Constants.LAST_NAME, player.getLastName());
        valuesObject.put(Constants.POSITION, player.getPosition());
        valuesObject.put(Constants.TEAM, player.getTeam());
        valuesObject.put(Constants.WEIGHT_POUNDS, player.getWeightPounds());
        list.add(valuesObject);
        FileWriter file = null;
        try {
            file = new FileWriter(filePath + "/JsonFiles/Cache.json");
            file.write(list.toJSONString());
            file.flush();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

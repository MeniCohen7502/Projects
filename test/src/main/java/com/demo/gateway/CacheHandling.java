package com.demo.gateway;

import com.demo.test.Player;
import com.demo.utils.Constants;
import com.demo.utils.JsonParser;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

public class CacheHandling
{
    public static JSONObject retrieveFromCache(String id)
    {
        JSONArray jsonArray = JsonParser.readJsonFile(Constants.filePath);
        for(int i=0;i< jsonArray.size();i++)
        {
            if(id.equals(((JSONObject) (jsonArray).get(i)).get(Constants.ID)))
            {
                return (JSONObject)jsonArray.get(i);
            }
        }
        return null;
    }
    public static void addToCache(Player player)
    {
        JsonParser.addToJson(player,Constants.filePath);
    }
}

package com.demo.gateway;

import com.demo.test.Player;
import com.demo.utils.Constants;
import com.demo.utils.JsonParser;

import java.util.HashMap;


public class BalldontlieImpl
{
    public static void mapResponseDetailsToPlayerDetails(String response,Player player)
    {
        HashMap<String,Object> map = JsonParser.jsonToHashMap(response);
        player.setPosition(map.get(Constants.POSITION).toString());
        player.setWeightPounds(String.valueOf(map.get(Constants.WEIGHT_POUNDS)));

        if(map.get(Constants.TEAM) instanceof String)
        {
            player.setWeightPounds(String.valueOf(map.get(Constants.TEAM_NAME)));
        }
        else
        {
            HashMap<String,Object> teamMap = (HashMap<String, Object>) map.get(Constants.TEAM);
            player.setTeam(teamMap.get(Constants.TEAM_NAME).toString());
        }


    }
}

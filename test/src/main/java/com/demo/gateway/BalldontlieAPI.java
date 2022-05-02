package com.demo.gateway;

import com.demo.test.Player;
import com.demo.utils.Constants;
import net.minidev.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/players")
public class BalldontlieAPI
{

    @RequestMapping("/{ID}")
    public boolean getPlayer(Player player){
        boolean isChanged = false;
        JSONObject playerDetails = CacheHandling.retrieveFromCache(player.getId());
        if(playerDetails != null)
        {
            BalldontlieImpl.mapResponseDetailsToPlayerDetails(playerDetails.toString(),player);
        }
        else
        {
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> result = restTemplate.getForEntity(Constants.BASE_URL + player.getId(), String.class);
            BalldontlieImpl.mapResponseDetailsToPlayerDetails(result.getBody(),player);
            CacheHandling.addToCache(player);
            isChanged = true;
        }
        return isChanged;
    }

}

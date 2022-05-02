package com.demo.test;

import com.demo.gateway.BalldontlieAPI;
import com.demo.utils.CSVParser;
import com.demo.utils.Constants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Controller
public class HomeController
{
    @Autowired
    private SimpMessagingTemplate template;

    private BalldontlieAPI balldontlieAPI = new BalldontlieAPI();
    private ScheduledExecutorService scheduler;

    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public String send(String message) {
        System.out.println("Send message to client");
        return Objects.isNull(message) || message.isEmpty() ? "Message Empty" : message;
    }

    @GetMapping("/player")
    public String getPlayer() {
        scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(new Runnable()
        {
            public void run()
            {
                System.out.println("Start Program " + LocalDateTime.now());
                ArrayList<Player> players = CSVParser.importCSV(Constants.filePath);
                for(Player player:players)
                {
                    if(balldontlieAPI.getPlayer(player))
                    {
                        String message = new SimpleDateFormat("HH:mm").format(new Date());
                        template.convertAndSend("/topic/messages", message);
                    }
                }
               // players.stream().forEach(player -> balldontlieAPI.getPlayer(player));
                try {
                    CSVParser.exportCSV(Constants.filePath,players);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("End Program " + LocalDateTime.now());
            }
        }, 0, 15, TimeUnit.MINUTES);


        return "getplayer";
    }

}

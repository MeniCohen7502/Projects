package com.demo.utils;

import com.demo.test.Player;

import java.io.*;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class CSVParser
{
    public static ArrayList<Player> importCSV(String filePath){
        ArrayList<Player> players = new ArrayList<>();
        String line;
        try (BufferedReader br =
                     new BufferedReader(new FileReader(filePath + "/CSVFiles/players.csv"))) {
            while((line = br.readLine()) != null){
                String[] playerDetails = line.split(" ");
                Player player = new Player(playerDetails[0],playerDetails[1],playerDetails[2]);
                players.add(player);
            }
        } catch (Exception e){
            System.out.println(e);
        }
        return players;
    }

    public static void exportCSV(String filePath, ArrayList<Player> players) throws IOException
    {
        File exists = new File(filePath + "/CSVFiles/playersOutput.csv");
        if(exists.exists())
        {
            exists.delete();
        }
        FileWriter writer = new FileWriter(filePath + "/CSVFiles/playersOutput.csv");
        players.stream().forEach(o -> {
            try {
                ArrayList<String> player = getPlayerDetails(o);
                String collect = player.stream().collect(Collectors.joining(","));
                writer.write(collect);
                writer.write("\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        writer.close();
    }

    public static ArrayList<String> getPlayerDetails(Player player)
    {
        ArrayList<String> playerAttributes = new ArrayList<>();
        playerAttributes.add(player.getId());
        playerAttributes.add(player.getFirstName());
        playerAttributes.add(player.getLastName());
        playerAttributes.add(player.getPosition());
        playerAttributes.add(player.getTeam());
        playerAttributes.add(player.getWeightPounds());
        return playerAttributes;
    }
}

package com.demo.test;

public class Player
{
    String id;
    String firstName;
    String lastName;
    String position;
    String team;
    String weightPounds;

    public Player(String id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Player(String id, String firstName, String lastName, String position, String team, String weightPounds) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.team = team;
        this.weightPounds = weightPounds;
    }

    public String getFirstName() { return firstName; }

    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }

    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getPosition() { return position; }

    public void setPosition(String position) { this.position = position; }

    public String getTeam() { return team; }

    public void setTeam(String team) { this.team = team; }

    public String getWeightPounds() { return weightPounds; }

    public void setWeightPounds(String weightPounds) { this.weightPounds = weightPounds; }

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }





}

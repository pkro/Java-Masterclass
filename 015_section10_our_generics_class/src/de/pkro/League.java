package de.pkro;

import java.util.ArrayList;
import java.util.Collections;

public class League<T extends Team> {
    private String name;
    private ArrayList<T> teams;

    public League(String name) {
        teams = new ArrayList<>();
        this.name = name;
    }

    public boolean addTeam(T team) {
        if(teams.contains(team)) {
            System.out.println("Team already in league");
            return false;
        }
        teams.add(team);
        return true;
    }

    public String getName() {
        return name;
    }

    public void printTeams() {
        System.out.println(name + "\n" + "=".repeat(name.length()));
        Collections.sort(teams, Collections.reverseOrder());
        for(T team: teams) {
            System.out.println(team.ranking() + ": " + team.getName());
        }
    }
}

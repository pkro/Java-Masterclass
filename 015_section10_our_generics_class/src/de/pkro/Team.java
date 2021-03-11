package de.pkro;

import java.util.ArrayList;

public class Team<T extends Player> implements Comparable<Team<T>> {
    private String name;
    int played = 0;
    int won = 0;
    int lost = 0;
    int tied = 0;

    ArrayList<T> members = new ArrayList<>();

    public Team(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean addPlayer(T player) {
        if (!members.contains(player)) {
            System.out.println("Added " + player.getName() + " to team " + name);
            return members.add(player);
        }
        System.out.println("Player already on team");

        return false;
    }

    public int numPlayers() {
        return this.members.size();
    }

    public void matchResult(Team<T> opponent, int ourScore, int theirScore) {
        String message;
        if (ourScore > theirScore) {
            won++;
            message = " beat ";
        } else if (ourScore < theirScore) {
            lost++;
            message = " lost to ";
        } else {
            tied++;
            message = " drew with ";
        }

        if (opponent != null) {
            System.out.println(name + message + opponent.getName());
            opponent.matchResult(null, theirScore, ourScore);
        }

    }

    public int ranking() {
        return won * 2 + tied;
    }

    @Override
    public int compareTo(Team<T> team) {
        return ranking() - team.ranking();
    }
}

package de.pkro;

import com.example.game.ISaveable;
import com.example.game.Monster;
import com.example.game.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        ISaveable p = new Player("Peer", 30, 100);
        System.out.println(p);
        ISaveable m = new Monster("rat", 10, 5);
        System.out.println(m);
        saveObject(p);
        saveObject(m);

        p.read(new ArrayList<>(Arrays.asList("Jon", ""+99, ""+999, "machete")));
        saveObject(p);

    }

    private static void saveObject(ISaveable saveable) {
        List<String> values = saveable.write();
        for(String e: values) {
            System.out.println(e);
        }

    }
}

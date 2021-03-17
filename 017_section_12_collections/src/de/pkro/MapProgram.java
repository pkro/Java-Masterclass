package de.pkro;

import java.util.HashMap;
import java.util.Map;

public class MapProgram {
    public static void main(String[] args) {
        Map<String, String> languages = new HashMap<>();
        languages.put("Java", "a compiled high level oo language");
        languages.put("Python", "interpreted oo high level language with dynamic semantics");
        languages.put("Algol", "an algorithmic language");
        languages.put("BASIC", "Beginners all purpose symbolic instruction code");
        System.out.println(languages.put("Lisp", "Madness")); // null

        System.out.println(languages.get("Java"));
        String previousLang = languages.put("Java", "blah"); // "interpreted high..."
        System.out.println(languages.get("Java"));
        System.out.println(previousLang);
        System.out.println(languages.containsKey("Java"));
        //System.out.println(languages.toString());
        printMap(languages);
        System.out.println("=======================");
        languages.remove("Lisp");
        if(languages.remove("Algol", "an algorithmic language")) {
            System.out.println("Algol removed");
        } else {
            System.out.println("key-val pair not found");
        }
        System.out.println("=======================");

        System.out.println(languages.replace("Java", "blahblubb"));
        printMap(languages);
        languages.replace("Java", "blahblubb", "a compiled high level oo language");
        printMap(languages);
    }

    public static void printMap(Map<String, String> m) {
        for(String key: m.keySet()) {
            System.out.printf("%10s: %-15s\n", key, m.get(key));
            //System.out.println(key + ": " + languages.get(key));
        }
    }

}

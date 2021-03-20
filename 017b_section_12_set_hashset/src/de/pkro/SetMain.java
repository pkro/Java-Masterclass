package de.pkro;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class SetMain {
    public static void main(String[] args) {
        Set<Integer> squares = new HashSet<>();
        Set<Integer> cubes = new HashSet<>();

        for(int i=1; i<=100; i++) {
            squares.add(i*i);
            cubes.add(i * i * i);
        }
        System.out.println("there are " + squares.size() + " squares and " + cubes.size() + " cubes");
        Set<Integer> intersection = new HashSet<>();
        intersection.addAll(squares);
        intersection.retainAll(cubes); // Intersection ([4096, 1, 64, 729])

        for(int i: intersection) {
            System.out.println(i + " is the square of " + Math.sqrt(i) + " and the cube of " + Math.cbrt(i));
        }

        Set<String> words = new HashSet<>();
        String sentence = "one day in the year of the fox";
        String arrayWords[] = sentence.split(" ");
        words.addAll(Arrays.asList(arrayWords));
        System.out.println(words.toString());

        Set<String> nature = new HashSet<>();
        Set<String> divine = new HashSet<>();

        String[] natureWords = "all nature is but art unknown to thee".split(" ");
        nature.addAll(Arrays.asList(natureWords));
        String[] divineWords = "to err is human to forgive divine".split(" ");
        divine.addAll(Arrays.asList(divineWords));

        System.out.println("nature - divine");
        Set<String> diff1 = new HashSet<>(nature);
        diff1.removeAll(divine);
        System.out.println(diff1.toString());

        System.out.println("divine - nature");
        Set<String> diff2 = new HashSet<>(divine);
        diff2.removeAll(nature);
        System.out.println(diff2.toString());

        System.out.println("Symetric difference");
        Set<String> symetricDifference = new HashSet<>(nature);
        symetricDifference.addAll(divine);
        Set<String> intersection2 = new HashSet<>(nature);
        intersection2.retainAll(divine);
        symetricDifference.removeAll(intersection2);
        System.out.println(symetricDifference);


        System.out.println(nature.containsAll(new HashSet<String>(Arrays.asList("nature", "to")))); // true
        System.out.println(nature.containsAll(new HashSet<String>(Arrays.asList("nature", "plastic")))); // false

    }
}

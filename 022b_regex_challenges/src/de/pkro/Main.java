package de.pkro;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Main {

  public static void main(String[] args) {
    String challenge1 = "I want a bike.";
    System.out.println(challenge1.matches(("I want a bike\\.")));

    String challenge2 = "I want a ball.";
    String challenge2b = "I want a ball";
    challenge(challenge1, "^I want a [a-zA-ZÀ-ž].+?\\.");
    challenge(challenge2, "^I want a [a-zA-ZÀ-ž].+?\\.");
    challenge(challenge2b, "^I want a [a-zA-ZÀ-ž].+?\\.");
    challenge("I want a Hündchen.", "^I want a [a-zA-ZÀ-ž].+?\\.");
    challenge("I want a Hündchen.", "^I want a \\w.+?\\."); // \w seems to include unicode

    Pattern wantsAThingPattern = Pattern.compile("^\\w+ wants*? a [a-zA-ZÀ-ž].+?\\.");
    Matcher wantsMatcher = wantsAThingPattern.matcher("I want a dog.");
    System.out.println("------------------");
    System.out.println(wantsMatcher.matches());
    wantsMatcher.reset();
    System.out.println(wantsMatcher.find());
    System.out.println(wantsMatcher.start());

    wantsMatcher = wantsAThingPattern.matcher("He wants a dog."); // true
    System.out.println(wantsMatcher.matches());
    wantsMatcher = wantsAThingPattern.matcher("He wanted a dog."); // false
    System.out.println(wantsMatcher.matches());

    sep();

    String challenge4 = "Replace all blanks with underscores.";
    System.out.println(challenge4.replaceAll("\\s", "_"));

    sep();
    String challenge5 = "aaabccccccdddeffg";
    challenge(challenge5, "^aaabccccccdddeffg$");
    challenge(challenge5, "^a{3}bc{6}d{3}ef{2}g$");

    String challenge7 = "abcd.135";
    challenge(challenge7, "^[a-zA-Z]+?\\.\\d+$");

    String challenge8 = "abcd.135uvqz.7tzik.999";
    Pattern pattern = Pattern.compile("[a-zA-Z]+?\\.(\\d+)");
    Matcher matcher8 = pattern.matcher(challenge8);
    while(matcher8.find()) {
      System.out.println(matcher8.group(1));
    }

    String challenge10 = "abcd.135\tuvqz.7\ttzik.999\n";
    pattern = Pattern.compile("[a-zA-Z]+?\\.(\\d+)\\s+?");
    Matcher matcher10 = pattern.matcher(challenge10);
    while(matcher10.find()) {
      System.out.println(matcher10.group(1) + "("+matcher10.start(1)+"-"+(matcher10.end(1)-1)+")");
    }
    sep();
    String challenge11 = "{0, 2}, {0,5}, {1,3}, {2, 4}";
    pattern = Pattern.compile("\\{(\\d+? *?, *?\\d+?)}");
    Matcher matcher11 = pattern.matcher(challenge11);
    while(matcher11.find()) {
      System.out.println(matcher11.group(1));
    }

    challenge("11111", "^\\d{5}$");
    challenge("111111", "^\\d{5}$");

    challenge("11111-1111", "^\\d{5}\\-\\d{4}$");

    challenge("11111-1111", "^\\d{5}(\\-\\d{4})*?$"); // true
    challenge("11111", "^\\d{5}(\\-\\d{4})*?$"); // true
    challenge("11111-1111-33", "^\\d{5}(\\-\\d{4})*?$"); // false


  }

  private static void challenge(String str, String regex) {
    System.out.println("testing \""+regex+"\" on \""+str+"\"");
    System.out.println(str.matches(regex));
  }

  private static void sep() {
    System.out.println("------------------");
  }
}

package de.pkro;

import jdk.swing.interop.SwingInterOpUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

  public static void main(String[] args) {
    String string = "I am a String. Yes, I am.";
    System.out.println(string);
    String youString = string.replaceAll("I am", "you are");
    System.out.println(youString);

    String alphanumeric = "abcDeeeFGc94c3cD";
    System.out.println(alphanumeric.replaceAll(".", "Y"));
    System.out.println(alphanumeric.replaceAll("\\d", "Y"));
    System.out.println(alphanumeric.matches("^a")); // false, WHOLE String has to match
    System.out.println(alphanumeric.replaceAll("[c][D]", "REPLACED"));

    System.out.println("harry".replaceAll("[Hh]arry", "Harry"));

    // replace all letters except a+c
    System.out.println(alphanumeric.replaceAll("[^ac]", "REPLACED"));

    // case insensitive
    System.out.println(alphanumeric.replaceAll("(?iu)[a-c]", "X"));

    System.out.println("asfds dfsdfsdf\t fdfsd\ndfsdf".replaceAll("\\s", "-"));

    System.out.println(alphanumeric.replaceAll("^abcDe+", "X"));

    StringBuilder htmlText = new StringBuilder("<h1>My Heading</h1>");
    htmlText.append("<h2>Sub heading</h2>");
    htmlText.append("<p>lorem ipsum fdfsdfsd</p>");
    htmlText.append("<p>Morelorem ipsum fdfsdfsd</p>");
    htmlText.append("<h2>Summary</h2>");
    htmlText.append("<p>Here is the summary</p>");

    String h2Pattern = "<h2>";
    Pattern pattern = Pattern.compile(h2Pattern);
    Matcher matcher = pattern.matcher(htmlText);
    System.out.println(matcher.matches()); // String as whole string must match for matches()
    matcher.reset();
    System.out.println("-------------------");
    int count = 0;
    while(matcher.find()) {
      count++;
      System.out.println("Occurence " + count + " : " + matcher.start() + " to " + matcher.end());
    }

    String h2GroupPattern = "(<h2>.*?</h2>)";
    Pattern groupPattern = Pattern.compile(h2GroupPattern);
    Matcher groupMatcher = groupPattern.matcher(htmlText);
    System.out.println(groupMatcher.matches());
    groupMatcher.reset();
    while(groupMatcher.find()) {
      System.out.println(groupMatcher.group(1));
    }

    String h2TextGroups = "(<h2>)(.+?)(</h2>)";
    Pattern h2TextPattern = Pattern.compile(h2TextGroups);
    groupMatcher = h2TextPattern.matcher(htmlText);
    while(groupMatcher.find()) {
      System.out.println(groupMatcher.group(2));
    }

    System.out.println("harry".replaceAll("[H|h]arry", "Larry"));

    String tvTest = "tstvtkt";
    //String tNotV = "t[^v]";
    String tNotV = "t(?!v)";
    Pattern tNotVPattern = Pattern.compile(tNotV);
    Matcher tNotVMatcher = tNotVPattern.matcher(tvTest);
    System.out.println("------------");
    while(tNotVMatcher.find()) {
      System.out.println(tNotVMatcher.start()); // 0, 4, 6
    }

  }
}

package de.pkro;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Main {

  public static void main(String[] args) {
    try {
      URL url = new URL("http://www.example.org/doesntexist.html");
      HttpURLConnection connection = (HttpURLConnection) url.openConnection();
      connection.setRequestMethod("GET"); // is default anyway
      connection.setRequestProperty("User-Agent", "Chrome");
      connection.setReadTimeout(1000);
      int responseCode = connection.getResponseCode();
      System.out.println(connection.getResponseMessage());

      if(responseCode == 200) {
        BufferedReader reader =
            new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
          System.out.println(line);
        }
        reader.close();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}

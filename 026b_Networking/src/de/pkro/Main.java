package de.pkro;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.List;
import java.util.Map;

public class Main {

  public static void main(String[] args) {
    try {
      URL url = new URL("http://example.org");
      URLConnection urlConnection = url.openConnection(); // doesn't connect,  just returns connection instance
      //urlConnection.setDoOutput(true);
      urlConnection.connect(); // connection values must be set BEFORE connect()

      BufferedReader inputStream = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

      Map<String, List<String>> headerFields = urlConnection.getHeaderFields();
      for(Map.Entry<String, List<String>> entry: headerFields.entrySet()) {
        String key = entry.getKey();
        List<String> value = entry.getValue();
        System.out.println("----key = " + key);
        for(String val: value) {
          System.out.println(val);
        }
      }
      /*while (inputStream.ready()) {
        String line = inputStream.readLine();
        System.out.println(line);
      }*/

      /*try (BufferedReader inputStream =
          new BufferedReader(new InputStreamReader(url.openStream())) ) {
        String line;
      while ((line = reader.readLine())!= null) {
        System.out.println(line);
      }
      } catch (IOException e) {
        e.printStackTrace();
      }*/

      /*String uriStr =
      "http://username:password@myserver.com:5000/catalogue/phones?os=android#article";*/

      /* String baseUriStr = "http://username:password@myserver.com:5000";
      URI baseUri = new URI(baseUriStr);

      String uri1 = "/catalogue/phones?os=android#samsung";
      String uri2 = "/catalogue/tvs?manufacturer=samsung";
      String uri3 = "/stores/locations?zip=12345";

      URI uri = new URI(uri1);

      URI resolvedUri1 = baseUri.resolve(uri1);
      URI resolvedUri2 = baseUri.resolve(uri2);
      URI resolvedUri3 = baseUri.resolve(uri3);

      URL url1 = resolvedUri1.toURL();
      URL url2 = resolvedUri2.toURL();
      URL url3 = resolvedUri3.toURL();

      System.out.println("URL 1 = " + url1);
      System.out.println("URL 2 = " + url2);
      System.out.println("URL 3 = " + url3);

      URI relativizedURI = baseUri.relativize(resolvedUri2);
      System.out.println("realtivized URI: "+relativizedURI);*/

      /*System.out.println("Full URI: " + uriStr);
      System.out.println("Scheme = " + uri.getScheme());
      System.out.println("Scheme-specific part = " + uri.getSchemeSpecificPart());
      System.out.println("Authority = " + uri.getAuthority());
      System.out.println("User info = " + uri.getUserInfo());
      System.out.println("Host = " + uri.getHost());
      System.out.println("Port = " + uri.getPort());
      System.out.println("Path = " + uri.getPath());
      System.out.println("Query = " + uri.getQuery());
      System.out.println("Fragment = " + uri.getFragment());*/
    } catch (IOException e ) {
      e.printStackTrace();
    }
  }
}

package de.pkro;

import de.pkro.model.Artist;
import de.pkro.model.DataSource;
import de.pkro.model.SongArtist;

import java.util.List;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    DataSource dataSource = new DataSource();
    dataSource.open();
    dataSource.createViewForSongArtists();

    List<Artist> artists = dataSource.queryArtists(DataSource.ORDER_BY_NONE);
    if (artists == null) {
      System.out.println("No artists");
      return;
    }
    for (Artist artist : artists) {
      System.out.println(artist.getId() + ": " + artist.getName());
    }

    List<String> zztopAlbums =
        dataSource.queryAlbumsForArtist("Pink Floyd", DataSource.ORDER_BY_ASC);
    for (String albumName : zztopAlbums) {
      System.out.println(albumName);
    }

    System.out.println("--------");
    List<SongArtist> evilWoman = dataSource.queryArtistForSong("Evil Woman", DataSource.ORDER_BY_ASC);
    for (SongArtist sa : evilWoman) {
      System.out.println(sa);
    }

    dataSource.querySongsMetadata();
    System.out.println("-----");
    System.out.println(dataSource.getCount("songs"));

    System.out.println("-------------------");
    evilWoman = dataSource.queryViewForSongArtist("Evil Woman", DataSource.ORDER_BY_ASC);
    for (SongArtist sa : evilWoman) {
      System.out.println(sa);
    }

    /*Scanner scanner = new Scanner(System.in);
    System.out.println("Please enter a song to search");
    String song = scanner.nextLine();
    List<SongArtist> songData = dataSource.queryViewForSongArtist(song, DataSource.ORDER_BY_ASC);
    for(SongArtist data : songData) {
      System.out.println(data);
    }*/

    System.out.println("------prepared version-------------");
    evilWoman = dataSource.querySongInfoView("Evil Woman");
    for (SongArtist sa : evilWoman) {
      System.out.println(sa);
    }

    System.out.println("testing song insert--------------------");
    dataSource.insertSong("see and praise", "Kawack", "the early years", 1);

    List<SongArtist> seeAndPraise = dataSource.querySongInfoView("see and praise");
    for (SongArtist sa : seeAndPraise) {
      System.out.println(sa);
    }

    dataSource.close();
  }
}

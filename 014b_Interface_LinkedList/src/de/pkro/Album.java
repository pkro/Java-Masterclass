package de.pkro;

import java.util.ArrayList;
import java.util.List;

public class Album {
    private String name;
    private String artist;
    private ArrayList<Song> songs;

    public Album(String name, String artist) {
        this.name = name;
        this.artist = artist;
        songs = new ArrayList<>();
    }

    public boolean addSong(String title, double duration) {
        if (findSong(title) != null) {
            return false;
        }
        return songs.add(new Song(title, duration));
    }

    public boolean addToPlayList(int trackNumber, List<Song> playList) {
        if (trackNumber <= 0 || trackNumber > songs.size()) {
            return false;
        }
        Song song = songs.get(trackNumber-1);
        if(song != null) {
            return playList.add(song);
        }
        return false;
    }

    public boolean addToPlayList(String title, List<Song> playList) {
        Song song = findSong(title);
        if (song != null) {
            playList.add(song);
            return true;
        }
        return false;
    }

    private Song findSong(String title) {
        for (int i = 0; i < songs.size(); i++) {
            if (songs.get(i).getTitle().equals(title)) {
                return songs.get(i);
            }
        }
        return null;
    }
}

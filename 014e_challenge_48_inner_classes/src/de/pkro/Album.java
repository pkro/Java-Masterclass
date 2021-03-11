package de.pkro;

import java.util.ArrayList;
import java.util.LinkedList;

public class Album {
    private String name;
    private String artist;
    private SongList songs = new SongList();

    public static class SongList {
        private ArrayList<Song> songs;

        private SongList() {
            songs = new ArrayList<>();
        }

        private boolean add(Song song) {
            if (findSong(song.getTitle()) != null) {
                return false;
            }
            return songs.add(song);
        }

        private Song findSong(String title) {
            for (int i = 0; i < songs.size(); i++) {
                if (songs.get(i).getTitle().equals(title)) {
                    return songs.get(i);
                }
            }
            return null;
        }

        private Song findSong(int trackNumber) {
            if(trackNumber>0 && trackNumber <=songs.size()) {
                return songs.get(trackNumber-1);
            }

            return null;
        }
    }

    public Album(String name, String artist) {
        this.name = name;
        this.artist = artist;
        songs = new SongList();
    }

    public boolean addToPlayList(int trackNumber, LinkedList<Song> playList) {
        Song song = songs.findSong(trackNumber-1);
        if(song != null) {
            return playList.add(song);
        }
        return false;
    }

    public boolean addToPlayList(String title, LinkedList<Song> playList) {
        Song song = songs.findSong(title);
        if (song != null) {
            playList.add(song);
            return true;
        }
        return false;
    }

    public boolean addSong(String title, double duration) {
        return songs.add(new Song(title, duration));
    }
}

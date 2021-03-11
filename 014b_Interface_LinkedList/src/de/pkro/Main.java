// the only change here is the change of list in declaration to allow for exchangable list types that implement the same interfase
package de.pkro;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        //ArrayList<Album> albums = new ArrayList<>();
        //LinkedList<Song> playList = new LinkedList<>();

        List<Album> albums = new ArrayList<>();

        Album album = new Album("Stormbringer", "Deep Purple");
        album.addSong("Stormbringer", 4.6);
        album.addSong("Love don't mean a thing", 4.22);
        album.addSong("Holy man", 4.3);
        album.addSong("Hold on", 5.6);
        album.addSong("Lady double dealer", 3.21);
        album.addSong("You can't do it right", 6.23);
        album.addSong("High ball shooter", 4.27);
        album.addSong("The gypsy", 4.2);
        album.addSong("Soldier of fortune", 3.13);
        albums.add(album);

        album = new Album("For those about to rock", "AC/DC");
        album.addSong("For those about to rock", 5.44);
        album.addSong("I put the finger on you", 3.25);
        album.addSong("Lets go", 3.45);
        album.addSong("Inject the venom", 3.33);
        album.addSong("Snowballed", 4.51);
        album.addSong("Evil walks", 3.45);
        album.addSong("C.O.D.", 5.25);
        album.addSong("Breaking the rules", 5.32);
        album.addSong("Night of the long knives", 5.12);
        albums.add(album);

        List<Song> playList = new LinkedList<Song>();
        albums.get(0).addToPlayList("You can't do it right", playList);
        albums.get(0).addToPlayList("Holy man", playList);
        albums.get(0).addToPlayList("Speed king", playList);  // Does not exist
        albums.get(0).addToPlayList(9, playList);
        albums.get(1).addToPlayList(3, playList);
        albums.get(1).addToPlayList(2, playList);
        albums.get(1).addToPlayList(24, playList);  // There is no track 24

        Scanner scanner = new Scanner(System.in);

        boolean quit = false;
        boolean goingForward = true;
        ListIterator<Song> iter = playList.listIterator();
        Song song = iter.next();
        while (!quit) {
            System.out.println("Playing " + song);
            showMenu();
            int input;
            if (scanner.hasNextInt()) {
                input = scanner.nextInt();
                scanner.nextLine();

                switch (input) {
                    case 0:
                        quit = true;
                        break;
                    case 1:
                        if (!goingForward) {
                            iter.next();
                        }
                        goingForward = true;
                        if (iter.hasNext()) {
                            song = iter.next();
                        }
                        break;
                    case 2:
                        if (goingForward) {
                            iter.previous();
                        }
                        goingForward = false;
                        if(iter.hasPrevious()) {
                            song = iter.previous();
                        }
                        break;
                    case 3:
                        break;
                    case 4:
                        iter.remove();
                        if(goingForward && iter.hasNext()) {
                            song = iter.next();
                        } else if(iter.hasPrevious()){
                            song = iter.previous();
                        } else {
                            System.out.println("List empty, quitting");
                            quit = true;
                        }
                        break;
                }
            }
        }


    }

    public static void showMenu() {
        System.out.println("0 - quit\n"
                + "1 - next\n"
                + "2 - back\n"
                + "3 - replay current\n"
                + "4 - remove current");
    }

}

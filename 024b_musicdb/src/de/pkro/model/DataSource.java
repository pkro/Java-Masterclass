package de.pkro.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataSource {
  public static final String DATABASE = "music.db";
  public static final String CONNECTION_STRING = "jdbc:sqlite:" + DATABASE;

  public static final String TABLE_ALBUMS = "albums";
  public static final String COLUMN_ALBUM_ID = "_id";
  public static final String COLUMN_ALBUM_NAME = "name";
  public static final String COLUMN_ALBUM_ARTIST = "artist";
  public static final int INDEX_ALBUM_ID = 1;
  public static final int INDEX_ALBUM_NAME = 2;
  public static final int INDEX_ALBUM_ARTIST = 3;

  public static final String TABLE_ARTISTS = "artists";
  public static final String COLUMN_ARTIST_ID = "_id";
  public static final String COLUMN_ARTIST_NAME = "name";
  public static final int INDEX_ARTIST_ID = 1;
  public static final int INDEX_ARTIST_NAME = 2;

  public static final String TABLE_SONGS = "songs";
  public static final String COLUMN_SONG_ID = "_id";
  public static final String COLUMN_SONG_TRACK = "track";
  public static final String COLUMN_SONG_TITLE = "title";
  public static final String COLUMN_SONG_ALBUM = "album";
  public static final int INDEX_SONG_ID = 1;
  public static final int INDEX_SONG_TRACK = 2;
  public static final int INDEX_SONG_TITLE = 3;
  public static final int INDEX_SONG_ALBUM = 4;

  public static final String VIEW_ARTIST_LIST = "artist_list";
  public static final String VIEW_COLUMN_NAME = "name";
  public static final String VIEW_COLUMN_ALBUM = "album";
  public static final String VIEW_COLUMN_TRACK = "track";
  public static final String VIEW_COLUMN_TITLE = "title";

  public static final int ORDER_BY_NONE = 1;
  public static final int ORDER_BY_ASC = 2;
  public static final int ORDER_BY_DESC = 3;

  public static final String QUERY_ALBUMS_BY_ARTIST_START =
      "SELECT "
          + TABLE_ALBUMS
          + "."
          + COLUMN_ALBUM_NAME
          + " from "
          + TABLE_ALBUMS
          + " INNER JOIN "
          + TABLE_ARTISTS
          + " ON "
          + TABLE_ALBUMS
          + "."
          + COLUMN_ALBUM_ARTIST
          + "="
          + TABLE_ARTISTS
          + "."
          + COLUMN_ARTIST_ID
          + " AND "
          + TABLE_ARTISTS
          + "."
          + COLUMN_ARTIST_NAME
          + "='";
  public static final String QUERY_ALBUMS_BY_ARTIST_SORT =
      " ORDER BY " + TABLE_ALBUMS + "." + COLUMN_ALBUM_NAME + " COLLATE NOCASE ";

  public static final String QUERY_ARTIST_FOR_SONG_START =
      "SELECT "
          + TABLE_ARTISTS
          + "."
          + COLUMN_ARTIST_NAME
          + ", "
          + TABLE_ALBUMS
          + "."
          + COLUMN_ALBUM_NAME
          + ", "
          + TABLE_SONGS
          + "."
          + COLUMN_SONG_TRACK
          + " FROM "
          + TABLE_ARTISTS
          + " INNER JOIN "
          + TABLE_ALBUMS
          + " ON "
          + TABLE_ARTISTS
          + "."
          + COLUMN_ARTIST_ID
          + "= "
          + TABLE_ALBUMS
          + "."
          + COLUMN_ALBUM_ARTIST
          + " INNER JOIN "
          + TABLE_SONGS
          + " ON "
          + TABLE_SONGS
          + "."
          + COLUMN_SONG_ALBUM
          + " = "
          + TABLE_ALBUMS
          + "."
          + COLUMN_ALBUM_ID
          + " WHERE "
          + TABLE_SONGS
          + "."
          + COLUMN_SONG_TITLE
          + " = '";

  public static final String QUERY_ARTIST_FOR_SONG_SORT =
      "ORDER BY "
          + TABLE_ARTISTS
          + "."
          + COLUMN_ARTIST_NAME
          + ", "
          + TABLE_ALBUMS
          + "."
          + COLUMN_ALBUM_NAME
          + " \n"
          + "COLLATE NOCASE\n";

  public static final String QUERY_ARTIST_FOR_SONG_VIEW_START =
      "SELECT "
          + VIEW_COLUMN_NAME
          + ", "
          + VIEW_COLUMN_ALBUM
          + ", "
          + VIEW_COLUMN_TRACK
          + " FROM "
          + VIEW_ARTIST_LIST
          + " WHERE "
          + VIEW_COLUMN_TITLE
          + " = '";

  public static final String QUERY_ARTIST_FOR_SONG_VIEW_SORT =
      "ORDER BY " + VIEW_COLUMN_NAME + ", " + VIEW_COLUMN_ALBUM + " \n" + "COLLATE NOCASE\n";

  public static final String TABLE_ARTIST_SONG_VIEW = "artist_list";
  // I will not do the concat hell here
  public static final String CREATE_ARTIST_FOR_SONG_VIEW =
      "CREATE VIEW IF NOT EXISTS artist_list AS SELECT artists.name AS name, albums.name AS album, songs.track, songs.title FROM songs INNER JOIN albums ON songs.album=albums._id INNER JOIN artists ON albums.artist=artists._id order by artists.name, albums.name, songs.track collate nocase";

  public static final String QUERY_VIEW_SONG_INFO_PREP =
      "SELECT "
          + VIEW_COLUMN_NAME
          + ", "
          + VIEW_COLUMN_ALBUM
          + ", "
          + VIEW_COLUMN_TRACK
          + " FROM "
          + TABLE_ARTIST_SONG_VIEW
          + " WHERE "
          + VIEW_COLUMN_TITLE
          + " = ?";

  public static final String INSERT_ARTIST =
      "insert into " + TABLE_ARTISTS + "(" + COLUMN_ARTIST_NAME + ") VALUES(?)";
  public static final String INSERT_ALBUMS =
      "insert into "
          + TABLE_ALBUMS
          + "("
          + COLUMN_ALBUM_NAME
          + ", "
          + COLUMN_ALBUM_ARTIST
          + ") VALUES(?, ?)";
  public static final String INSERT_SONG =
      "insert into "
          + TABLE_SONGS
          + "("
          + COLUMN_SONG_TRACK
          + ", "
          + COLUMN_SONG_TITLE
          + ", "
          + COLUMN_SONG_ALBUM
          + ") VALUES(?, ?, ?)";

  public static final String QUERY_ARTIST =
      "SELECT "
          + COLUMN_ARTIST_ID
          + " FROM "
          + TABLE_ARTISTS
          + " WHERE "
          + COLUMN_ARTIST_NAME
          + " = ?";

  public static final String QUERY_ALBUM =
      "SELECT "
          + COLUMN_ALBUM_ID
          + " FROM "
          + TABLE_ALBUMS
          + " WHERE "
          + COLUMN_ALBUM_NAME
          + " = ?"
          + " AND "
          + COLUMN_ALBUM_ARTIST
          + "= ?";

  private Connection conn;

  private PreparedStatement querySongInfoViewsStmt;
  private PreparedStatement insertIntoArtists;
  private PreparedStatement insertIntoAlbums;
  private PreparedStatement insertIntoSongs;
  private PreparedStatement queryArtist;
  private PreparedStatement queryAlbum;

  private static void closeStatements(List<PreparedStatement> statements) throws SQLException {
    for (PreparedStatement statement : statements) {
      if (statement != null) {
        statement.close();
      }
    }
  }

  public boolean open() {
    try {
      conn = DriverManager.getConnection(CONNECTION_STRING);

      querySongInfoViewsStmt = conn.prepareStatement(QUERY_VIEW_SONG_INFO_PREP);
      insertIntoArtists = conn.prepareStatement(INSERT_ARTIST, Statement.RETURN_GENERATED_KEYS);
      insertIntoAlbums = conn.prepareStatement(INSERT_ALBUMS, Statement.RETURN_GENERATED_KEYS);
      insertIntoSongs = conn.prepareStatement(INSERT_SONG);
      queryArtist = conn.prepareStatement(QUERY_ARTIST);
      queryAlbum = conn.prepareStatement(QUERY_ALBUM);

      return true;
    } catch (SQLException e) {
      System.out.println(e.getMessage());
      return false;
    }
  }

  public void close() {
    try {
      closeStatements(
          Arrays.asList(
              querySongInfoViewsStmt,
              insertIntoAlbums,
              insertIntoArtists,
              insertIntoSongs,
              queryAlbum,
              queryArtist));
      if (conn != null) {
        conn.close();
      }
    } catch (SQLException e) {
      System.out.println("couldn't close db connection");
    }
  }

  public List<Artist> queryArtists(int sortOrder) {
    StringBuilder sb = new StringBuilder("SELECT * FROM ");
    sb.append(TABLE_ARTISTS);
    if (sortOrder != ORDER_BY_NONE) {
      sb.append(" ORDER BY ");
      sb.append(COLUMN_ARTIST_NAME);
      sb.append(" COLLATE NOCASE");
      switch (sortOrder) {
        case ORDER_BY_DESC:
          sb.append(" DESC");
          break;
        case ORDER_BY_ASC:
        default:
          sb.append(" ASC");
          break;
      }
    }

    String query = sb.toString();
    try (Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(query)) {
      List<Artist> artists = new ArrayList<>();
      while (resultSet.next()) {
        artists.add(
            new Artist(
                // resultSet.getInt(COLUMN_ARTIST_ID), resultSet.getString(COLUMN_ARTIST_NAME)));
                resultSet.getInt(INDEX_ARTIST_ID), resultSet.getString(INDEX_ARTIST_NAME)));
      }
      return artists;
    } catch (SQLException e) {
      e.printStackTrace();
      return null;
    }
  }

  // select * from albums inner join artists on albums.artist=artists._id where artists.name="ZZ
  // Top"
  public List<String> queryAlbumsForArtist(String artistName, int sortOrder) {

    StringBuilder sb = new StringBuilder(QUERY_ALBUMS_BY_ARTIST_START);
    sb.append(artistName);
    sb.append("'");

    if (sortOrder != ORDER_BY_NONE) {
      sb.append(QUERY_ALBUMS_BY_ARTIST_SORT);
      switch (sortOrder) {
        case ORDER_BY_DESC:
          sb.append(" DESC");
          break;
        case ORDER_BY_ASC:
        default:
          sb.append(" ASC");
          break;
      }
    }

    String query = sb.toString();
    try (Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(query)) {
      List<String> albums = new ArrayList<>();
      while (resultSet.next()) {
        albums.add(resultSet.getString(1)); // only one column is returned
      }
      return albums;
    } catch (SQLException e) {
      e.printStackTrace();
      return null;
    }
  }

  public List<SongArtist> queryArtistForSong(String songTitle, int sortOrder) {
    StringBuilder sb = new StringBuilder(QUERY_ARTIST_FOR_SONG_START);
    sb.append(songTitle);
    sb.append("' ");
    if (sortOrder != ORDER_BY_NONE) {
      sb.append(QUERY_ARTIST_FOR_SONG_SORT);
      switch (sortOrder) {
        case ORDER_BY_DESC:
          sb.append(" DESC");
          break;
        case ORDER_BY_ASC:
        default:
          sb.append(" ASC");
          break;
      }
    }
    String sql = sb.toString();
    try (Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(sql)) {

      List<SongArtist> result = new ArrayList<>();
      while (resultSet.next()) {
        result.add(
            new SongArtist(resultSet.getString(1), resultSet.getString(2), resultSet.getInt(3)));
      }
      return result;
    } catch (SQLException e) {
      e.printStackTrace();
      return null;
    }
  }

  public List<SongArtist> queryViewForSongArtist(String songTitle, int sortOrder) {
    StringBuilder sb = new StringBuilder(QUERY_ARTIST_FOR_SONG_VIEW_START);
    sb.append(songTitle);
    sb.append("' ");
    if (sortOrder != ORDER_BY_NONE) {
      sb.append(QUERY_ARTIST_FOR_SONG_VIEW_SORT);
      switch (sortOrder) {
        case ORDER_BY_DESC:
          sb.append(" DESC");
          break;
        case ORDER_BY_ASC:
        default:
          sb.append(" ASC");
          break;
      }
    }
    String sql = sb.toString();
    try (Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(sql)) {

      List<SongArtist> result = new ArrayList<>();
      while (resultSet.next()) {
        result.add(
            new SongArtist(resultSet.getString(1), resultSet.getString(2), resultSet.getInt(3)));
      }
      return result;
    } catch (SQLException e) {
      e.printStackTrace();
      return null;
    }
  }

  public List<SongArtist> querySongInfoView(String title) {
    try {
      querySongInfoViewsStmt.setString(1, title);
      ResultSet resultSet = querySongInfoViewsStmt.executeQuery();
      List<SongArtist> result = new ArrayList<>();
      while (resultSet.next()) {
        result.add(
            new SongArtist(resultSet.getString(1), resultSet.getString(2), resultSet.getInt(3)));
      }
      return result;
    } catch (SQLException e) {
      e.printStackTrace();
      return null;
    }
  }

  public void querySongsMetadata() {
    String sql = "SELECT * FROM " + TABLE_SONGS;

    try (Statement stmt = conn.createStatement();
        ResultSet results = stmt.executeQuery(sql)) {
      ResultSetMetaData meta = results.getMetaData();
      int numColumns = meta.getColumnCount();
      for (int i = 1; i <= numColumns; i++) {
        System.out.format("Column %d in the sontas table is named %s\n", i, meta.getColumnName(i));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public int getCount(String table) {
    String sql = "SELECT COUNT(*) as count FROM " + table;
    try (Statement statement = conn.createStatement();
        ResultSet result = statement.executeQuery(sql)) {
      ResultSetMetaData meta = result.getMetaData();
      // System.out.println(meta.getColumnName(1)); // "COUNT(*)
      return result.getInt("count");
    } catch (SQLException e) {
      e.printStackTrace();
      return -1;
    }
  }

  public boolean createViewForSongArtists() {
    try (Statement stmt = conn.createStatement();
        ResultSet result = stmt.executeQuery(CREATE_ARTIST_FOR_SONG_VIEW)) {
      return true;
    } catch (SQLException e) {
      e.printStackTrace();
      return false;
    }
  }

  private int insertArtist(String name) throws SQLException {
    queryArtist.setString(1, name);
    ResultSet result = queryArtist.executeQuery();
    if (result.next()) {
      return result.getInt(1);
    } else {
      insertIntoArtists.setString(1, name);
      int affectedRows = insertIntoArtists.executeUpdate();
      if (affectedRows != 1) {
        throw new SQLException("Couldn't insert artist");
      }
      ResultSet generatedKeys = insertIntoArtists.getGeneratedKeys();
      if (generatedKeys.next()) {
        return generatedKeys.getInt(1);
      }
      throw new SQLException("Couldn't get ID for artist");
    }
  }

  // album is null, DEBUG ME
  private int insertAlbum(String name, int artistId) throws SQLException {
    queryAlbum.setString(1, name);
    queryAlbum.setInt(2, artistId);
    ResultSet result = queryAlbum.executeQuery();
    if (result.next()) {
      return result.getInt(1);
    } else {
      insertIntoAlbums.setString(1, name);
      insertIntoAlbums.setInt(2, artistId);
      int affectedRows = insertIntoAlbums.executeUpdate();
      if (affectedRows != 1) {
        throw new SQLException("Couldn't insert album");
      }
      ResultSet generatedKeys = insertIntoAlbums.getGeneratedKeys();
      if (generatedKeys.next()) {
        return generatedKeys.getInt(1);
      }
      throw new SQLException("Couldn't get ID for album");
    }
  }

  public void insertSong(String title, String artist, String album, int track) {
    try {
      conn.setAutoCommit(false);
      int artistId = insertArtist(artist);
      int albumId = insertAlbum(album, artistId);
      insertIntoSongs.setInt(1, track);
      insertIntoSongs.setString(2, title);
      insertIntoSongs.setInt(3, albumId);

      int affectedRows = insertIntoSongs.executeUpdate();
      if (affectedRows == 1) {
        conn.commit();
      } else {
        throw new SQLException("couldn't insert song");
      }

    } catch (Exception e) {
      // NOT just SQLException, otherwise finally (with autocommit=true)
      // gets executed and artist / album could be
      // added permanently even if the insert song fails for another reason,
      // e.g. index out of bounds when adding a wrong parameter index
      System.out.println("Insert song exception; " + e.getMessage());
      try {
        System.out.println("performint rollback");
        conn.rollback();
      } catch (SQLException e2) {
        System.out.println("Couldn't roll back: " + e.getMessage());
      }
    } finally {
      try {
        System.out.println("Resetting default commit behaviour");
        conn.setAutoCommit(true);
      } catch (SQLException e) {
        System.out.println("couldn't set autocommit to true: " + e.getMessage());
      }
    }
  }
}

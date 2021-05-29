package de.pkro;

import org.sqlite.SQLite;

import java.sql.*;

public class Main {
  public static final String DB_NAME = "testjava.db";
  public static final String CONNECTION_STRING = "jdbc:sqlite:" + DB_NAME;
  public static final String TABLE_CONTACTS = "contacts";
  public static final String COLUMN_NAME = "name";
  public static final String COLUMN_PHONE = "phone";
  public static final String COLUMN_EMAIL = "email";

  public static void main(String[] args) {
    try (Connection conn = DriverManager.getConnection(CONNECTION_STRING)) {
      // conn.setAutoCommit(false); // default is autocommit
      Statement statement = conn.createStatement();
      statement.execute("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
      extracted(statement);

      insertContact(statement, "Joe", 4321, "b@b.cd");
      insertContact(statement, "Jane", 1234, "c@b.cd");
      insertContact(statement, "Bill", 43434, "d@b.cd");

      statement.execute(
          "UPDATE contacts SET " + COLUMN_PHONE + "=44444 where " + COLUMN_NAME + "='Joe'");
      // statement.execute("DELETE FROM contacts WHERE name='Joe'");
      // statement.execute("select * from contacts");
      // ResultSet results = statement.getResultSet();
      ResultSet results = statement.executeQuery("SELECT * FROM " + TABLE_CONTACTS);
      while (results.next()) {
        System.out.println(
            results.getString(COLUMN_NAME)
                + " "
                + results.getInt(COLUMN_PHONE)
                + " "
                + results.getString(COLUMN_EMAIL)
                + " ");
      }
      results.close();
      // conn.commit();
      statement.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  private static void extracted(Statement statement) throws SQLException {
    statement.execute(
        "CREATE TABLE IF NOT EXISTS "
            + TABLE_CONTACTS
            + " ("
            + COLUMN_NAME
            + " TEXT, "
            + COLUMN_PHONE
            + " INTEGER, "
            + COLUMN_EMAIL
            + " TEXT)");
  }

  public static void insertContact(Statement statement, String name, int phone, String email) throws SQLException {
    statement.execute(
            "INSERT INTO contacts ("
                    + COLUMN_NAME
                    + ", "
                    + COLUMN_PHONE
                    + ", "
                    + COLUMN_EMAIL
                    + ") "
                    + "VALUES ('"+name+"', "+phone+", '"+email+"')");
  }
}

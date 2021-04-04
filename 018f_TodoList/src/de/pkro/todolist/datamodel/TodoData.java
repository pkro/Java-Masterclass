package de.pkro.todolist.datamodel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;

public class TodoData {
    private static TodoData instance = new TodoData();
    private static String filename = "TodoListItems.txt";
    private ObservableList<TodoItem> todoItems;
    private DateTimeFormatter formatter;

    private TodoData() {
        formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    }

    public static TodoData getInstance() {
        return instance;
    }

    public ObservableList<TodoItem> getTodoItems() {
        return todoItems;
    }

    public void setTodoItems(List<TodoItem> todoItems) {
        this.todoItems = (ObservableList<TodoItem>) todoItems;
    }

    public ObservableList<TodoItem> addTodoItem(TodoItem item) {
        todoItems.add(item);
        return todoItems;
    }

    public void deleteTodoItem(TodoItem item) {
        todoItems.removeAll(item);
    }

    public void loadTodoItems() throws IOException {
        // observableArrayList type needed for "setAll", raises an event on change (?)
        // FXCollections types reduce the number of notifications for performance reasons
        todoItems = FXCollections.observableArrayList();
        Path path = Paths.get(filename);
        BufferedReader br = Files.newBufferedReader(path);

        String input;

        try {
            while ((input = br.readLine()) != null) {
                String[] itemPieces = input.split("\t");
                String shortDescription = itemPieces[0];
                String details = itemPieces[1];
                String dateString = itemPieces[2];
                LocalDate date = LocalDate.parse(dateString, formatter);
                todoItems.add(new TodoItem(shortDescription, details, date));
            }
        } finally {
            br.close();
        }
    }

    public void storeTodoItems() throws IOException {
        Path path = Paths.get(filename);
        BufferedWriter bw = Files.newBufferedWriter(path);
        try {
            Iterator<TodoItem> iter = todoItems.iterator();
            while (iter.hasNext()) {
                TodoItem item = iter.next();
                bw.write(String.format("%s\t%s\t%s",
                        item.getShortDescription(),
                        item.getDetails(), item.getDeadline().format(formatter)));
                bw.newLine();
            }
        } finally {
            bw.close();
        }
    }
}

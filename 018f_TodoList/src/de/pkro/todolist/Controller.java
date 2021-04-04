package de.pkro.todolist;

import de.pkro.todolist.datamodel.TodoData;
import de.pkro.todolist.datamodel.TodoItem;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.util.Callback;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class Controller {
    private List<TodoItem> todoItems;
    @FXML
    private ListView<TodoItem> todoListView;
    @FXML
    private TextArea itemDetailsTextArea;
    @FXML
    private Label deadlineLabel;
    @FXML
    private BorderPane mainBorderPane;
    @FXML
    private ContextMenu listContextMenu;
    @FXML
    private ToggleButton filterToggleButton;

    private FilteredList<TodoItem> filteredList;

    private Predicate<TodoItem> wantAllItems;
    private Predicate<TodoItem> wantTodaysItems;

    public void initialize() {

        /*
        // just for creation of the initial file to work with
        TodoItem item1 = new TodoItem("Mail bd card", "buy card and send it", LocalDate.of(2021, Month.APRIL, 25));
        TodoItem item2 = new TodoItem("Mail another bd card", "fdfdswfew d and send it", LocalDate.of(2021, Month.APRIL, 25));
        TodoItem item3 = new TodoItem("Mail fsdfasd card", "buy cdsfsdt34 rte it", LocalDate.of(2021, Month.MAY, 25));
        TodoItem item4 = new TodoItem("Go compra", "gret 3tfsd f d", LocalDate.of(2021, Month.JUNE, 25));
        TodoItem item5 = new TodoItem("Buy beer", "buysdwet wert ", LocalDate.of(2021, Month.JULY, 25));

        todoItems = new ArrayList<>();
        todoItems.add(item1);
        todoItems.add(item2);
        todoItems.add(item3);
        todoItems.add(item4);
        todoItems.add(item5);

        TodoData.getInstance().setTodoItems(todoItems);*/

        // add context menu for (non-empty) cells; add context menu in cellfactory below
        // TODO: 4/3/21 add copy to clipboard menu item

        // delete
        listContextMenu = new ContextMenu();
        MenuItem deleteMenuItem = new MenuItem();
        deleteMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                TodoItem item = todoListView.getSelectionModel().getSelectedItem();
                deleteItem(item);
            }
        });
        deleteMenuItem.setText("delete");
        listContextMenu.getItems().addAll(deleteMenuItem);

        // new
        MenuItem newMenuItem = new MenuItem();
        newMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                showNewItemDialog();
            }
        });

        newMenuItem.setText("new");
        listContextMenu.getItems().addAll(newMenuItem);
        // see adding the context menu to the cells in the cell factory part in this file

        todoListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TodoItem>() {
            @Override
            public void changed(ObservableValue<? extends TodoItem> observable, TodoItem oldValue, TodoItem newValue) {
                if (newValue != null) {
                    TodoItem selected = todoListView.getSelectionModel().getSelectedItem();
                    itemDetailsTextArea.setText(selected.getDetails());
                    DateTimeFormatter df = DateTimeFormatter.ofPattern("MMMM d, yyyy");
                    deadlineLabel.setText(df.format(selected.getDeadline()));
                }
            }
        });

        //todoListView.getItems().setAll(TodoData.getInstance().getTodoItems());
        // now auto-populating with an ObservableList

        // initialize predicates for filtering (saves us writing them over and over where needed)
        wantAllItems = new Predicate<TodoItem>() {
            @Override
            public boolean test(TodoItem todoItem) {
                return true;
            }
        };

        wantTodaysItems = new Predicate<TodoItem>() {
            @Override
            public boolean test(TodoItem todoItem) {
                return todoItem.getDeadline().equals(LocalDate.now());
            }
        };

        // wrap todoItems in a FilteredList to use later for "show today only" togglebuton
        filteredList = new FilteredList<TodoItem>(TodoData.getInstance().getTodoItems(), wantAllItems);

        // wrap FilteredList in SortedList
        SortedList<TodoItem> sortedList = new SortedList<TodoItem>(filteredList, new Comparator<TodoItem>() {
            @Override
            public int compare(TodoItem o1, TodoItem o2) {
                return o1.getDeadline().compareTo(o2.getDeadline());
            }
        });

        //todoListView.setItems(TodoData.getInstance().getTodoItems()); // before wrapping it in a sorted list
        todoListView.setItems(sortedList);
        todoListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        todoListView.getSelectionModel().selectFirst();

        // pass an anonymous class that implements the Callback interface
        todoListView.setCellFactory(new Callback<ListView<TodoItem>, ListCell<TodoItem>>() {
            @Override
            public ListCell<TodoItem> call(ListView<TodoItem> todoItemListView) {
                ListCell<TodoItem> cell = new ListCell<TodoItem>() {
                    @Override
                    protected void updateItem(TodoItem item, boolean empty) {
                        // keep most of default appearance
                        super.updateItem(item, empty);
                        //setContextMenu(listContextMenu);
                        if (empty) {
                            setText(null);
                        } else {
                            setText(item.getShortDescription());
                            if (item.getDeadline().isBefore(LocalDate.now().plusDays(1))) {
                                setTextFill(Color.RED);
                            } else if (item.getDeadline().equals(LocalDate.now().plusDays(1))) {
                                setTextFill(Color.ORANGE);
                            } else {
                                // set explicitely, otherwise random cells will be red as well
                                // https://stackoverflow.com/questions/50348508/java-fx-listview-cell-background-affect-uncolored-cells-while-scrolling
                                setTextFill(Color.BLACK);
                            }
                        }
                    }
                };
                // or just setContextMenu(listContextMenu); ?
                // maybe because otherwise deleted cells would have the context menu?
                cell.emptyProperty().addListener(
                        (obs, wasEmpty, isNowEmpty) -> {
                            if (isNowEmpty) {
                                // TODO: 4/3/21 add  context menu without "delete", just "new" for empty cells
                                cell.setContextMenu(null);
                            } else {
                                cell.setContextMenu(listContextMenu);
                            }
                        }
                );

                return cell;
            }
        });
    }

    @FXML
    public void showNewItemDialog() {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Add a new ToDo Item");
        dialog.setHeaderText("Create a new todo item");
        dialog.initOwner(mainBorderPane.getScene().getWindow());
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("todoItemDialog.fxml"));
        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            DialogController controller = fxmlLoader.getController();
            TodoItem newItem = controller.processResults();
            // this is now handled by binding the observable list, so no manual update needed anymore (except auto select)
            // todoListView.getItems().setAll(TodoData.getInstance().getTodoItems());
            // autoselect newly added Item
            todoListView.getSelectionModel().select(newItem);
        } else if (result.isPresent() && result.get() == ButtonType.CANCEL) {
            // nothing to be done, just exits without changing anything
            System.out.println("cancel pressed");
        }
    }

    @FXML
    public void exitApplication() {
        Platform.exit();
    }

    public void deleteItem(TodoItem item) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete ToDo");
        alert.setHeaderText("Delete Item " + item.getShortDescription());
        alert.setContentText("Are you sure? Press OK to confirm or cancel to... well, cancel.");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            TodoData.getInstance().deleteTodoItem(item);
        }
    }

    @FXML
    public void handleKeyPressed(KeyEvent keyEvent) {
        TodoItem selectedItem = todoListView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            if (keyEvent.getCode() == KeyCode.DELETE) {
                deleteItem(selectedItem);
            }
        }
    }

    @FXML
    public void handleFilterButton() {
        TodoItem selectedItem = todoListView.getSelectionModel().getSelectedItem();
        if (filterToggleButton.isSelected()) {
            filteredList.setPredicate(wantTodaysItems);
            if(filteredList.isEmpty()) {
                itemDetailsTextArea.clear();
                deadlineLabel.setText("");
            } else if(filteredList.contains(selectedItem)) {
                todoListView.getSelectionModel().select(selectedItem);
            } else {
                todoListView.getSelectionModel().selectFirst();
            }
        } else {
            filteredList.setPredicate(wantAllItems);
            todoListView.getSelectionModel().select(selectedItem);
        }

    }
    // this was replaced by the changeHandler in initialize later
    /*@FXML
    public void handleClickListView() {
        TodoItem selected = todoListView.getSelectionModel().getSelectedItem();
        itemDetailsTextArea.setText(selected.getDetails());
        deadlineLabel.setText(selected.getDeadline().toString());
    }*/
}

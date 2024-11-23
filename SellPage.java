package application;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

public class SellPage {
    private Button add, sell, remove, returnButton;
    private ObservableList<Book> observableBooks;
    private ListView<Book> listbooks;

    public SellPage() {
        add = new Button("Add to Sell");
        remove = new Button("Remove");
        sell = new Button("Sell");
        returnButton = new Button("Return");
        listbooks = new ListView<>();
        this.observableBooks = DataManager.getSharedBooks(); // Use shared list
        listbooks.setItems(observableBooks);
    }

    public void showScene() {
        Stage sellStage = new Stage();
        sellStage.setTitle("User View");

        listbooks.setCellFactory(param -> new listViewCell());

        remove.setStyle("-fx-background-color: maroon; -fx-text-fill: white;");
        remove.setPrefSize(150, 50);
        sell.setStyle("-fx-background-color: maroon; -fx-text-fill: white;");
        sell.setPrefSize(150, 50);
        add.setStyle("-fx-background-color: maroon; -fx-text-fill: white;");
        add.setPrefSize(150, 50);
        add.setOnAction(e -> {
            sellForm();
        });

        returnButton.setStyle("-fx-background-color: maroon; -fx-text-fill: white; -fx-font-size: 16px;");
        returnButton.setPrefSize(200, 75);
        returnButton.setOnAction(e -> {
            sellStage.close();
        });

        remove.setOnAction(e -> {
            Book selectedBook = listbooks.getSelectionModel().getSelectedItem();
            if (selectedBook != null) {
                observableBooks.remove(selectedBook);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Confirmation");
                alert.setHeaderText(null);
                alert.setContentText("Book removed successfully!");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("No book selected to remove.");
                alert.showAndWait();
            }
        });

        VBox buttonBox = new VBox(20);
        buttonBox.getChildren().addAll(remove, sell, add);
        buttonBox.setAlignment(Pos.CENTER);

        BorderPane root = new BorderPane();
        root.setCenter(buttonBox);

        VBox listBox = new VBox(20);
        listBox.setPadding(new Insets(10));
        listBox.setPrefWidth(700);
        listBox.setAlignment(Pos.CENTER_LEFT);

        HBox header = new HBox();
        header.setStyle("-fx-background-color: #E1AD01;");
        header.setPadding(new Insets(15));
        header.setAlignment(Pos.CENTER);

        Label headerLabel = new Label("Sell");
        headerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        headerLabel.setTextFill(Color.DARKRED);

        header.getChildren().add(headerLabel);
        root.setTop(header);

        HBox returnBox = new HBox(30);
        returnBox.getChildren().addAll(returnButton);
        returnBox.setAlignment(Pos.TOP_LEFT);

        listBox.getChildren().addAll(returnBox, listbooks);
        root.setLeft(listBox);

        Scene scene = new Scene(root, 1300, 600);
        sellStage.setScene(scene);
        sellStage.show();
    }

    private void sellForm() {
        Stage addSellStage = new Stage();
        addSellStage.setTitle("Add to Sell Order");

        GridPane formGrid = new GridPane();
        formGrid.setAlignment(Pos.CENTER);
        formGrid.setHgap(10);
        formGrid.setVgap(10);
        formGrid.setPadding(new Insets(20, 20, 20, 20));

        Label TitleLabel = new Label("Book Title:");
        formGrid.add(TitleLabel, 0, 0);

        TextField TitleField = new TextField();
        formGrid.add(TitleField, 1, 0);

        Label AuthorLabel = new Label("Author:");
        formGrid.add(AuthorLabel, 0, 1);

        TextField AuthorField = new TextField();
        formGrid.add(AuthorField, 1, 1);

        Label CategoryLabel = new Label("Category:");
        formGrid.add(CategoryLabel, 0, 2);

        TextField CategoryField = new TextField();
        formGrid.add(CategoryField, 1, 2);

        ComboBox<String> Condition = new ComboBox<>();
        Condition.setPromptText("Condition");
        Condition.getItems().addAll("New", "Like New", "Fair", "Poor");
        formGrid.add(Condition, 1, 3);

        Label PriceLabel = new Label("Price:");
        formGrid.add(PriceLabel, 0, 4);

        Pattern validDoubleText = Pattern.compile("-?\\d*(\\.\\d{0,2})?");
        UnaryOperator<TextFormatter.Change> filter = change -> {
            String newText = change.getControlNewText();
            if (validDoubleText.matcher(newText).matches()) {
                return change;
            }
            return null;
        };
        TextFormatter<String> textFormatter = new TextFormatter<>(filter);
        TextField PriceField = new TextField();
        PriceField.setTextFormatter(textFormatter);
        formGrid.add(PriceField, 1, 4);

        Label inputLabel = new Label("");
        formGrid.add(inputLabel, 0, 5);

        Button submitButton = new Button("Submit");
        submitButton.setStyle("-fx-background-color: maroon; -fx-text-fill: white;");
        formGrid.add(submitButton, 1, 5);

        submitButton.setOnAction(event -> {
            if (!TitleField.getText().isEmpty() && Condition.getValue() != null && !AuthorField.getText().isEmpty() && !PriceField.getText().isEmpty()) {
                Book temp = new Book();
                temp.setTitle(TitleField.getText());
                temp.setAuthor(AuthorField.getText());
                temp.setCategory(CategoryField.getText());
                temp.setCondition(Condition.getValue());
                temp.setPrice(Double.parseDouble(PriceField.getText()));

                observableBooks.add(temp);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Confirmation");
                alert.setHeaderText(null);
                alert.setContentText("Book added successfully!");
                alert.showAndWait();

                addSellStage.close();
            } else {
                inputLabel.setTextFill(Color.RED);
                inputLabel.setText("Please make sure you have filled in all details.");
            }
        });

        Scene formScene = new Scene(formGrid, 500, 500);
        addSellStage.setScene(formScene);
        addSellStage.showAndWait();
    }
}

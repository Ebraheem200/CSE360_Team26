package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BuyCart {

    private Button refresh, returnButton, order, remove;
    private ObservableList<Book> observableBooks;
    private ListView<Book> listbooks;
    private Book selected;

    public BuyCart() {
        remove = new Button("Remove");
        order = new Button("Place Order");
        refresh = new Button("Refresh");
        returnButton = new Button("Return");
        listbooks = new ListView<>();
        this.observableBooks = FXCollections.observableArrayList();
        listbooks.setItems(observableBooks);
    }

    public void showScene() {
        Stage cartStage = new Stage();
        cartStage.setTitle("Cart");

        listbooks.setOnMousePressed(c -> {
            // Get the selected item
            this.selected = listbooks.getSelectionModel().getSelectedItem();
        });

        // Add styling to buttons
        remove.setStyle("-fx-background-color: maroon; -fx-text-fill: white;");
        remove.setPrefSize(150, 50);
        order.setStyle("-fx-background-color: maroon; -fx-text-fill: white;");
        order.setPrefSize(150, 50);
        returnButton.setStyle("-fx-background-color: maroon; -fx-text-fill: white; -fx-font-size: 16px;");
        returnButton.setPrefSize(200, 75);

        // Return button action
        returnButton.setOnAction(e -> cartStage.close());

        // Remove button action
        remove.setOnAction(e -> {
            if (selected != null) {
                observableBooks.remove(selected);

                // Show confirmation dialog
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Confirmation");
                alert.setHeaderText(null); // Removes the default icon
                alert.setContentText("Book removed from cart successfully!");
                alert.showAndWait();
            } else {
                // Show error dialog
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null); // Removes the default icon
                alert.setContentText("No book selected to remove.");
                alert.showAndWait();
            }
        });

        // Order button action
        order.setOnAction(e -> {
            if (!observableBooks.isEmpty()) {
                double totalCost = calculateTotalCost();

                // Show total cost and confirm order placement
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Order Confirmation");
                alert.setHeaderText(null); // Removes the default icon
                alert.setContentText(String.format("Total cost: $%.2f\nThank you for your purchase!", totalCost));
                alert.showAndWait();

                observableBooks.clear(); // Clear the cart
            } else {
                // Show error dialog
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null); // Removes the default icon
                alert.setContentText("Your cart is empty.");
                alert.showAndWait();
            }
        });

        VBox buttonBox = new VBox(20);
        buttonBox.getChildren().addAll(remove, order);
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

        Label headerLabel = new Label("Cart");
        headerLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
        header.getChildren().add(headerLabel);
        root.setTop(header);

        HBox returnBox = new HBox(30);
        returnBox.getChildren().addAll(returnButton);
        returnBox.setAlignment(Pos.TOP_LEFT);

        listBox.getChildren().addAll(returnBox, listbooks);
        root.setLeft(listBox);

        Scene scene = new Scene(root, 1300, 600);
        cartStage.setScene(scene);
        cartStage.show();
    }

    public void updatelist(Book book) {
        observableBooks.add(book);
    }

    private double calculateTotalCost() {
        return observableBooks.stream().mapToDouble(Book::getPrice).sum();
    }
}

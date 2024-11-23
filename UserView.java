package application;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class UserView {

    private Button refresh, cart, add, sell;
    private TextField sortcategory, sortcondition, sortstring;
    private ListView<Book> listbooks;

    public UserView() {
        // Buttons
        sell = new Button("Sell");
        add = new Button("Add to Cart");
        refresh = new Button("Refresh");
        cart = new Button("\uD83D\uDED2");

        // Input fields
        sortcategory = new TextField();
        sortcondition = new TextField();
        sortstring = new TextField();

        // ListView
        listbooks = new ListView<>();
        listbooks.setItems(DataManager.getSharedBooks()); // Use shared list for dynamic updates

        // Set button actions
        SellPage sellPage = new SellPage();
        sell.setOnAction(e -> sellPage.showScene());

        BuyCart buyCart = new BuyCart();
        cart.setOnAction(e -> buyCart.showScene());

        add.setOnAction(e -> {
            Book selected = listbooks.getSelectionModel().getSelectedItem();
            if (selected != null) {
                buyCart.updatelist(selected); // Add book to cart
                DataManager.getSharedBooks().remove(selected); // Remove book from shared list

                // Show confirmation dialog
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Confirmation");
                alert.setHeaderText(null);
                alert.setContentText("Book added to cart and removed from the list!");
                alert.showAndWait();
            } else {
                // Show error dialog
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("No book selected to add to cart.");
                alert.showAndWait();
            }
        });

        refresh.setOnAction(e -> loadBooksFromFile());
    }

    public void showScene() {
        Stage userStage = new Stage();
        userStage.setTitle("User View");

        // Read books into the ListView
        loadBooksFromFile();

        // Root layout
        BorderPane root = new BorderPane();

        // Styling for buttons
        sell.setStyle("-fx-background-color: maroon; -fx-text-fill: white;");
        sell.setPrefSize(120, 50);
        add.setStyle("-fx-background-color: maroon; -fx-text-fill: white;");
        add.setPrefSize(120, 50);
        cart.setStyle("-fx-background-color: maroon; -fx-text-fill: white; -fx-font-size: 28px;");
        cart.setPrefSize(90, 90);

        // Button box
        VBox buttonBox = new VBox(20);
        buttonBox.getChildren().addAll(sell, add);
        buttonBox.setAlignment(Pos.CENTER_LEFT);

        root.setCenter(buttonBox);

        // ListView box
        VBox listBox = new VBox(10);
        listBox.setPadding(new Insets(10));
        listBox.setPrefWidth(400);
        listBox.setAlignment(Pos.CENTER_LEFT);

        HBox header = new HBox();
        header.setStyle("-fx-background-color: #E1AD01;");
        header.setPadding(new Insets(15));
        header.setAlignment(Pos.CENTER);

        Label headerLabel = new Label("Used Book Store");
        headerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        headerLabel.setTextFill(Color.DARKRED);

        header.getChildren().add(headerLabel);
        root.setTop(header); // Add header to the top of the BorderPane

        HBox sortBox = new HBox(15);
        ComboBox<String> sortBy = new ComboBox<>();
        sortBy.setPromptText("Sort By");
        sortBy.getItems().addAll("Author (A-Z)", "Price $->$$$", "Condition");
        ComboBox<String> category = new ComboBox<>();
        category.setPromptText("Category");
        category.getItems().addAll("");
        sortBox.getChildren().addAll(sortBy, category);
        sortBox.setAlignment(Pos.TOP_LEFT);

        listBox.getChildren().addAll(sortBox, listbooks);
        root.setLeft(listBox);

        HBox topRightBox = new HBox();
        topRightBox.setAlignment(Pos.TOP_RIGHT);
        topRightBox.setPadding(new Insets(10, 15, 0, 0));
        topRightBox.getChildren().add(cart);

        root.setRight(topRightBox);

        // Create the scene
        Scene scene = new Scene(root, 1300, 600);
        userStage.setScene(scene);

        // Show the stage
        userStage.show();
    }

    private void loadBooksFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("BooksForSale.txt"))) {
            DataManager.getSharedBooks().clear(); // Clear existing list to prevent duplicates
            while (true) {
                try {
                    Book temp = (Book) ois.readObject();
                    DataManager.getSharedBooks().add(temp); // Add books to shared list
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            if (!(e instanceof java.io.EOFException)) {
                e.printStackTrace();
            }
        }
    }
}

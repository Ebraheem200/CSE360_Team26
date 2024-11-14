package application;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import javax.swing.text.View;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
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
private ArrayList <Book> arrayList_Book; 
private ArrayList <listViewCell> arrayList_LVC;
private ListView <Book> listbooks;
private Book selected;


	public UserView() {
		sell = new Button("sell");
		add = new Button("add");
		refresh = new Button("refresh");
		cart = new Button("\uD83D\uDED2");
		sortcategory = new TextField();
        sortcondition = new TextField();
        sortstring = new TextField();
        listbooks = new ListView<>();
        this.arrayList_Book = new ArrayList<>(); 
	}

	public void showScene() {
		Stage userStage = new Stage();
        userStage.setTitle("User View");
        //Read books into the list view
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("BooksForSale.txt"))) {
            while (true) {
                try {
                    Book temp = (Book) ois.readObject(); 
                    arrayList_Book.add(temp);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            if (!(e instanceof java.io.EOFException)) {
                e.printStackTrace();
            }
        }
        for (Book temp : arrayList_Book) {
            listbooks.getItems().add(temp);
        }
    
        // Create the root layout
        BorderPane root = new BorderPane();
        
        //Add styling to buttons
        sell.setStyle("-fx-background-color: maroon; -fx-text-fill: white;");
        sell.setPrefSize(120, 50);
        add.setStyle("-fx-background-color: maroon; -fx-text-fill: white;");
        add.setPrefSize(120, 50);
        cart.setStyle("-fx-background-color: maroon; -fx-text-fill: white; -fx-font-size: 28px;");
        cart.setPrefSize(90, 90);
        
      
        //event handler for list view
        listbooks.setOnMousePressed(e -> {
            // Get the selected item
           this.selected = listbooks.getSelectionModel().getSelectedItem();
        });
        
        BuyCart buyCart = new BuyCart();//create the cart
        SellPage sellPage  = new SellPage();// create the sellpage
        sell.setOnAction(e -> {sellPage.showScene();});
        cart.setOnAction(e -> {buyCart.showScene();}); 
        add.setOnAction(e -> {
		buyCart.updatelist(selected);
        	  });
        // Create an HBox to hold the buttons and add them
        VBox buttonBox = new VBox(20);  
        buttonBox.getChildren().addAll(sell, add);
        buttonBox.setAlignment(Pos.CENTER_LEFT);

        // Set the buttonBox in a position on the layout, like at the top
        root.setCenter(buttonBox);

        // Optionally add other elements (like `listbooks`) to different regions of the BorderPane
        VBox listBox = new VBox(1);
        listBox.setPadding(new Insets(10));
        listBox.setPrefWidth(400);
        listBox.setAlignment(Pos.CENTER_LEFT);
       

        HBox header = new HBox();
        header.setStyle("-fx-background-color: #E1AD01;");
        header.setPadding(new Insets(15));
        header.setAlignment(Pos.CENTER);

        Label headerLabel = new Label("Used Book Store");
        headerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        headerLabel.setTextFill(Color.DARKRED); // Set text color to dark red

        header.getChildren().add(headerLabel);
        root.setTop(header); // Add header to the top of the BorderPane	
        
        //Create combo boxes for the sort options
        HBox sortBox = new HBox(15);
        ComboBox<String> sortBy = new ComboBox<>();
        sortBy.setPromptText("Sort By");
        sortBy.getItems().addAll("Author (A-Z)", "Price $->$$$", "Condition");
        ComboBox<String> category = new ComboBox<>();
        category.setPromptText("Category");
        category.getItems().addAll("");
        sortBox.getChildren().addAll(sortBy, category);
        sortBox.setAlignment(Pos.TOP_LEFT);
        
        
        
        //add the filters to the left box
        listBox.getChildren().addAll(sortBox, listbooks);
        root.setLeft(listBox);  
        HBox topRightBox = new HBox();
        topRightBox.setAlignment(Pos.TOP_RIGHT);
        topRightBox.setPadding(new Insets(10, 15, 0, 0)); // Add some padding to position it

        //Add cart button to the top right corner
        topRightBox.getChildren().add(cart);
        
        // Add the "Create Account" button HBox to the top of the BorderPane
        root.setRight(topRightBox);
       
        
        // Create the scene with the root layout
        Scene scene = new Scene(root, 1300, 600);
        userStage.setScene(scene);
        
        // Show the stage
        userStage.show();
	}
	
	private void updateListFromDataBase() {
		
	}
	
	private void add() {
		
	}

}

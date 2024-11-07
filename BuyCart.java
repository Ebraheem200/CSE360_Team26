package application;

import java.util.ArrayList;

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

public class BuyCart {
	
	private Button refresh, returnButton, order, remove;
	private TextField sortcategory, sortcondition, sortstring;
	private ArrayList <Book> arrayList_Book; 
	private ArrayList <listViewCell> arrayList_LVC;
	private ListView <listViewCell> listbooks;
	
	public BuyCart() {
		remove = new Button("Remove");
		order = new Button("Place Order");
		refresh = new Button("refresh");
		returnButton = new Button("Return");
		sortcategory = new TextField();
        sortcondition = new TextField();
        sortstring = new TextField();
        listbooks = new ListView<>();
	}

	public void showScene() {
		Stage cartStage = new Stage();
        cartStage.setTitle("User View");

        // Create the root layout
        BorderPane root = new BorderPane();
        
        //Add styling to buttons
        remove.setStyle("-fx-background-color: maroon; -fx-text-fill: white;");
        remove.setPrefSize(150, 50);
        order.setStyle("-fx-background-color: maroon; -fx-text-fill: white;");
        order.setPrefSize(150, 50);
        returnButton.setStyle("-fx-background-color: maroon; -fx-text-fill: white; -fx-font-size: 16px;");
        returnButton.setPrefSize(200, 75);
        
        //Set button actions
        returnButton.setOnAction(e -> {cartStage.close();});
        // Create an HBox to hold the buttons and add them
        VBox buttonBox = new VBox(20);  
        buttonBox.getChildren().addAll(remove, order);
        buttonBox.setAlignment(Pos.CENTER);

        // Set the buttonBox in a position on the layout, like at the top
        root.setCenter(buttonBox);

        // Optionally add other elements (like `listbooks`) to different regions of the BorderPane
        VBox listBox = new VBox(20);
        listBox.setPadding(new Insets(10));
        listBox.setPrefWidth(700);
        listBox.setAlignment(Pos.CENTER_LEFT);
       

        HBox header = new HBox();
        header.setStyle("-fx-background-color: #E1AD01;");
        header.setPadding(new Insets(15));
        header.setAlignment(Pos.CENTER);

        Label headerLabel = new Label("Cart");
        headerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        headerLabel.setTextFill(Color.DARKRED); // Set text color to dark red

        header.getChildren().add(headerLabel);
        root.setTop(header); // Add header to the top of the BorderPane	
        
        //Create combo boxes for the sort options
        HBox returnBox = new HBox(30);
        returnBox.getChildren().addAll(returnButton);
        returnBox.setAlignment(Pos.TOP_LEFT);
        
        
        listBox.getChildren().addAll(returnBox, listbooks);
        root.setLeft(listBox);   
        // Create the scene with the root layout
        Scene scene = new Scene(root, 1300, 600);
        cartStage.setScene(scene);
        
        // Show the stage
        cartStage.show();
	}
	
	private void updateListFromDataBase() {
		
	}
}

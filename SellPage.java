package application;

import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class SellPage {
	private Button refresh, cart, add, sell, remove, returnButton;
	private TextField sortcategory, sortcondition, sortstring;
	private ArrayList <Book> arrayList_Book; 
	private ArrayList <listViewCell> arrayList_LVC;
	private ListView <listViewCell> listbooks;


		public SellPage() {
			add = new Button("Add to Sell");
			remove = new Button("Remove");
			sell = new Button("Sell");
			refresh = new Button("refresh");
			returnButton = new Button("Return");
			sortcategory = new TextField();
	        sortcondition = new TextField();
	        sortstring = new TextField();
	        listbooks = new ListView<>();
		}

		public void showScene() {
			Stage sellStage = new Stage();
	        sellStage.setTitle("User View");

	        // Create the root layout
	        BorderPane root = new BorderPane();
	        
	        //Add styling to buttons
	        remove.setStyle("-fx-background-color: maroon; -fx-text-fill: white;");
	        remove.setPrefSize(150, 50);
	        sell.setStyle("-fx-background-color: maroon; -fx-text-fill: white;");
	        sell.setPrefSize(150, 50);
	        add.setStyle("-fx-background-color: maroon; -fx-text-fill: white;");
	        add.setPrefSize(150, 50);
	        
	        returnButton.setStyle("-fx-background-color: maroon; -fx-text-fill: white; -fx-font-size: 16px;");
	        returnButton.setPrefSize(200, 75);
	        
	        //Set button actions
	        returnButton.setOnAction(e -> {sellStage.close();});
	        add.setOnAction(e ->{sellForm();});
	        
	        // Create an HBox to hold the buttons and add them
	        VBox buttonBox = new VBox(20);  
	        buttonBox.getChildren().addAll(remove, sell, add);
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

	        Label headerLabel = new Label("Sell");
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
	        sellStage.setScene(scene);
	        
	        // Show the stage
	        sellStage.show();
		}
		
		private void sellForm() {
			Stage addSellStage = new Stage();
	        addSellStage.setTitle("Add to Sell Order");
	        
	        // Create a GridPane layout for the create account form
	        GridPane formGrid = new GridPane();
	        formGrid.setAlignment(Pos.CENTER);
	        formGrid.setHgap(10);
	        formGrid.setVgap(10);
	        formGrid.setPadding(new Insets(20, 20, 20, 20));

	        // Add Username and Password fields
	        Label TitleLabel = new Label("Book Title:");
	        formGrid.add(TitleLabel, 0, 0);

	        TextField TitleField = new TextField();
	        formGrid.add(TitleField, 1, 0);

	        Label AuthorLabel = new Label("Author");
	        formGrid.add(AuthorLabel, 0, 1);

	        TextField AuthorField = new TextField();
	        formGrid.add(AuthorField, 1, 1);
	        
	        Label CategoryLabel = new Label("Category");
	        formGrid.add(CategoryLabel, 0,2);
	        
	        TextField CategoryField = new TextField();
	        formGrid.add(CategoryField, 1, 2);
	        
	        ComboBox<String> Condition = new ComboBox<>();
	        Condition.setPromptText("Condition");
	        Condition.getItems().addAll("New", "Like New", "Fair", "Poor");
	        formGrid.add(Condition, 1, 3);
	        
	        Label PriceLabel = new Label("Price");
	        formGrid.add(PriceLabel, 0, 4);
	        
	        TextField PriceField = new TextField();
	        formGrid.add(PriceField, 1, 4);
	        
	        Label inputLabel = new Label("");
	        formGrid.add(inputLabel, 0, 5);
	        
	        // Add Submit button
	        Button submitButton = new Button("Submit");
	        submitButton.setStyle("-fx-background-color: maroon; -fx-text-fill: white;");
	        formGrid.add(submitButton, 1, 5);        
	        // Set action for Submit button
	        submitButton.setOnAction(event -> {
	        	 String newTitle = TitleField.getText();
	             String newAuthor = AuthorField.getText();
	             
	             //Check valid user info
	             if(newTitle.isEmpty()||newAuthor.length()<10||newAuthor.isEmpty()) {
	            	 inputLabel.setTextFill(Color.RED);
	            	 inputLabel.setText("Please make sure you have filled in all details");
	             } else{addSellStage.close();}});
	        Scene formScene = new Scene(formGrid, 600, 700);
	        addSellStage.setScene(formScene);
	        addSellStage.showAndWait(); 
		}
	        
}

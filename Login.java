package application;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OptionalDataException;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;



public class Login extends Application{
Stage loginStage;
	@Override
	public void start(Stage loginStage) throws Exception {
		// TODO Auto-generated method stub
		loginStage.setTitle("Login Page");
        BorderPane root = new BorderPane();
        // Create the header with a yellow background
        HBox header = new HBox();
        header.setStyle("-fx-background-color: #E1AD01;");
        header.setPadding(new Insets(15));
        header.setAlignment(Pos.CENTER);

        Label headerLabel = new Label("Used Book Store");
        headerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        headerLabel.setTextFill(Color.DARKRED); // Set text color to dark red

        header.getChildren().add(headerLabel);
        root.setTop(header); // Add header to the top of the BorderPane
        
        //Create Create account button
        HBox topRightBox = new HBox();
        topRightBox.setAlignment(Pos.TOP_RIGHT);
        topRightBox.setPadding(new Insets(10, 15, 0, 0)); // Add some padding to position it

        Button createAccountButton = new Button("Create Account");
        createAccountButton.setStyle("-fx-background-color: maroon; -fx-text-fill: white;"); // Optional styling
        topRightBox.getChildren().add(createAccountButton);
        
        // Add the "Create Account" button HBox to the top of the BorderPane
        root.setRight(topRightBox);
        // Create a GridPane layout
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        // Add Username label and text field
        Label userNameLabel = new Label("Username:");
        grid.add(userNameLabel, 0, 1);

        TextField userTextField = new TextField();
        grid.add(userTextField, 1, 1);

        // Add Password label and password field
        Label pwLabel = new Label("Password:");
        grid.add(pwLabel, 0, 2);

        PasswordField pwField = new PasswordField();
        grid.add(pwField, 1, 2);
        

        // Add Login button
        Button loginButton = new Button("Login");
        grid.add(loginButton, 1, 3);
        loginButton.setStyle("-fx-background-color: maroon; -fx-text-fill: white;");
       
        Label inputLabel = new Label("");
        grid.add(inputLabel, 1, 4);
        // Set action for Login button
        loginButton.setOnAction(e -> {
            String username = userTextField.getText();
            String password = pwField.getText();
            databaseLogin(username, password, inputLabel, loginStage);
        });
        
        createAccountButton.setOnAction(e -> {createUserAccount();});
        
        root.setCenter(grid);
        
        // Create and set the Scene
        Scene scene = new Scene(root, 1300, 600);
        loginStage.setScene(scene);
        loginStage.show();
    
    }
    
    private void databaseLogin(String username, String password, Label inputLabel, Stage loginStage) {
    	boolean foundMatch = false;
    	File file = new File("Accounts.txt");
    	if (!file.exists() || file.length() == 0) {
            inputLabel.setText("Account not found. Please create an account first.");
            inputLabel.setTextFill(Color.RED);
            return;
        }

    	
    	if(username.isBlank()|| password.isBlank()) {
    		inputLabel.setText("Invalid Login");
    		inputLabel.setTextFill(Color.RED);	
    	}
    	else { 
    		try (ObjectInputStream read = new ObjectInputStream(new FileInputStream("Accounts.txt"))) {
                while (true) {
                    try {
                        User compare = (User) read.readObject();
                        // Check if the credentials match
                        if (compare.getUserName().equals(username) && compare.getPassword().equals(password)){
                        	foundMatch = true;
                        	inputLabel.setText("Logging in...");
                    		inputLabel.setTextFill(Color.BLACK);
                    		
                    		UserView userView = new UserView();//Create a UserView 
                    		
                    		 //Only here to simulate loading can be removed
                    		 PauseTransition pause = new PauseTransition(Duration.seconds(0));
                             pause.setOnFinished(e -> userView.showScene()); //replace action with Userview.launch
                             pause.play();
                             loginStage.close();
                    		 
                             
                             
                    		break; //Successful login
                        }
                       
                    } catch (EOFException eof) {
                        break; // End of file reached
                    }
                 catch (OptionalDataException ode) {
                    // Handle optional data by skipping it
                    read.skipBytes(ode.length); // Skip over any non-object data
                }
                }
                if (foundMatch == false) {
                    // If no matching credentials were found after the loop
                    inputLabel.setText("Invalid Credentials, please try again");
                    inputLabel.setTextFill(Color.RED);
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
    	}
    	
    }
   
    private void createUserAccount() {
        Stage createAccountStage = new Stage();
        createAccountStage.setTitle("Create New Account");
        
        // Create a GridPane layout for the create account form
        GridPane formGrid = new GridPane();
        formGrid.setAlignment(Pos.CENTER);
        formGrid.setHgap(10);
        formGrid.setVgap(10);
        formGrid.setPadding(new Insets(20, 20, 20, 20));

        // Add Username and Password fields
        Label UsernameLabel = new Label("New Username:");
        formGrid.add(UsernameLabel, 0, 0);

        TextField UsernameField = new TextField();
        formGrid.add(UsernameField, 1, 0);

        Label PasswordLabel = new Label("New Password:");
        formGrid.add(PasswordLabel, 0, 1);

        PasswordField PasswordField = new PasswordField();
        formGrid.add(PasswordField, 1, 1);
        
        Label inputLabel = new Label("Password must be at least 10 characters long");
        formGrid.add(inputLabel, 0, 2);
        
        // Add Submit button
        Button submitButton = new Button("Submit");
        submitButton.setStyle("-fx-background-color: maroon; -fx-text-fill: white;");
        formGrid.add(submitButton, 1, 2);        
        // Set action for Submit button
        submitButton.setOnAction(event -> {
        	 String newUsername = UsernameField.getText();
             String newPassword = PasswordField.getText();
             
             //Check valid user info
             if(newUsername.isEmpty()||newPassword.length()<10||newPassword.isEmpty()) {
            	 inputLabel.setTextFill(Color.RED);
             }
            else {
        	User user = new User(0, newUsername, newPassword);
            //Write the User data to Accounts.txt with a new line after
        	try {
                File file = new File("Accounts.txt");
                boolean append = file.exists() && file.length() > 0; // Check if we need to append

                try (ObjectOutputStream write = append ?
                        new ObjectOutputStream(new FileOutputStream(file, true)) {
                            @Override
                            protected void writeStreamHeader() throws IOException {
                                reset(); // Prevent header from being written again
                            }
                        } :
                        new ObjectOutputStream(new FileOutputStream(file))) {
                    write.writeObject(user);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            
            createAccountStage.close(); 
            }
        });
        
        
        Scene formScene = new Scene(formGrid, 300, 200);
        createAccountStage.setScene(formScene);
        createAccountStage.showAndWait(); 
	}

}

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;


public class ManageUserAccounts extends Application {
    private Button refresh, transactionView, manageUserAccounts, systemData, add, delete, modify;
    private TextField idSort, passwordSort;
    private ComboBox<String> permissionSort;
    private ListView <String> listUsers;

    public ManageUserAccounts(){
        transactionView = new Button("Transaction View");
        manageUserAccounts = new Button("Manage User Accounts");
        systemData = new Button("System Data");
        add = new Button("Add");
        delete = new Button("Delete");
        modify = new Button("Modify");
        idSort = new TextField();
        passwordSort = new TextField();
        permissionSort = new ComboBox<>();
        listUsers = new ListView<>();
    }

    public void start(Stage userStage){
        userStage.setTitle("Manage User Accounts");

        // root layout
        BorderPane root = new BorderPane();

        // styles for buttons
        transactionView.setStyle("-fx-background-color: maroon; -fx-text-fill: white;");
        transactionView.setPrefSize(120,40);

        manageUserAccounts.setStyle("-fx-background-color: maroon; -fx-text-fill: white;");
        manageUserAccounts.setPrefSize(150,40);

        systemData.setStyle("-fx-background-color: maroon; -fx-text-fill: white;");
        systemData.setPrefSize(120,40);

        add.setStyle("-fx-background-color: maroon; -fx-text-fill: white;");
        add.setPrefSize(100,40);

        delete.setStyle("-fx-background-color: maroon; -fx-text-fill: white;");
        delete.setPrefSize(100,40);

        modify.setStyle("-fx-background-color: maroon; -fx-text-fill: white;");
        modify.setPrefSize(100,40);

        // button actions
        add.setOnAction(e -> {modifyUsers();});
        modify.setOnAction(e -> {modifyUsers();});

        VBox buttonBox1 = new VBox(20);
        buttonBox1.getChildren().addAll(add, delete, modify);
        buttonBox1.setAlignment(Pos.CENTER);
        root.setCenter(buttonBox1);

        HBox buttonBox2 = new HBox(40);
        buttonBox2.getChildren().addAll(transactionView, manageUserAccounts,systemData);
        buttonBox2.setAlignment(Pos.TOP_LEFT);

        VBox listBox = new VBox(20);
        listBox.setPadding(new Insets(10));
        listBox.setPrefWidth(800);
        listBox.setAlignment(Pos.CENTER_LEFT);

        GridPane formGrid = new GridPane();
        formGrid.setAlignment(Pos.BOTTOM_LEFT);
        formGrid.setHgap(10);
        formGrid.setVgap(10);
        formGrid.setPadding(new Insets(10,10,10,10));

        Label idSortLabel = new Label("ID Sort:");
        formGrid.add(idSortLabel, 0, 0);
        idSort = new TextField();
        formGrid.add(idSort, 1, 0);

        Label passwordSortLabel = new Label("Password Sort:");
        formGrid.add(passwordSortLabel, 0, 1);
        passwordSort = new TextField();
        formGrid.add(passwordSort, 1, 1);

        Label permissionSortLabel = new Label("Permission Sort:");
        formGrid.add(permissionSortLabel, 0, 2);
        permissionSort = new ComboBox<>();
        permissionSort.getItems().addAll("Customer","Admin");
        formGrid.add(permissionSort, 1, 2);

        listBox.getChildren().addAll(buttonBox2,listUsers, formGrid);
        root.setLeft(listBox);


        HBox header = new HBox();
        header.setStyle("-fx-background-color: #E1AD01;");
        header.setPadding(new Insets(15));
        header.setAlignment(Pos.CENTER);

        Label headerLabel = new Label("Manage User Accounts");
        headerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        headerLabel.setTextFill(Color.DARKRED); // Set text color to dark red

        header.getChildren().add(headerLabel);
        root.setTop(header);


        Scene scene = new Scene(root, 1300, 600);
        userStage.setScene(scene);
        userStage.show();
    }
    private void modifyUsers()
    {
        Stage modifyStage = new Stage();
        modifyStage.setTitle("Modify User Accounts");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.TOP_CENTER);
        grid.setHgap(20);
        grid.setVgap(20);
        grid.setPadding(new Insets(10,10,10,10));

        Button returnButton = new Button("Return");
        returnButton.setStyle("-fx-background-color: maroon; -fx-text-fill: white;");
        returnButton.setPrefSize(120,40);

        Button resetButton = new Button("Reset");
        resetButton.setStyle("-fx-background-color: maroon; -fx-text-fill: white;");
        resetButton.setPrefSize(100,40);

        Button confirmButton = new Button("Confirm");
        confirmButton.setStyle("-fx-background-color: maroon; -fx-text-fill: white;");
        confirmButton.setPrefSize(100,40);

        grid.add(returnButton, 0, 0);

        Label label1 = new Label("ASU ID:");
        grid.add(label1, 0, 2);

        TextField asuID = new TextField();
        grid.add(asuID, 1,2);

        grid.add(resetButton, 5, 2);

        Label label2 = new Label("Password:");
        grid.add(label2, 0, 3);

        TextField password = new TextField();
        grid.add(password, 1, 3);

        grid.add(confirmButton, 5, 3);

        Label label3 = new Label("Permission:");
        grid.add(label3, 0, 4);

        ComboBox<String> permission = new ComboBox<>();
        permission.getItems().addAll("Customer","Admin");
        grid.add(permission, 1, 4);

        returnButton.setOnAction(e -> {modifyStage.close();});

        Scene formScene = new Scene(grid, 500, 500);
        modifyStage.setScene(formScene);
        modifyStage.showAndWait();
    }

}

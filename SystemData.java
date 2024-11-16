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


public class SystemData{
    private Button transactionView, manageUserAccounts, systemData, add, removeListing, modify;
    private ListView <String> listUsers;

    public SystemData(){
        transactionView = new Button("Transaction View");
        manageUserAccounts = new Button("Manage User Accounts");
        systemData = new Button("System Data");
        add = new Button("Add Listing");
        removeListing = new Button("Remove Listing");
        modify = new Button("Modify Listing");
        listUsers = new ListView<>();
    }

    public void showScene(){
        Stage userStage = new Stage();
        userStage.setTitle("System Data");

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
        add.setPrefSize(120,40);

        removeListing.setStyle("-fx-background-color: maroon; -fx-text-fill: white;");
        removeListing.setPrefSize(120,40);

        modify.setStyle("-fx-background-color: maroon; -fx-text-fill: white;");
        modify.setPrefSize(120,40);

        // button actions
        add.setOnAction(e -> {
            modifyListing();});
        modify.setOnAction(e -> {
            modifyListing();});

        /*
        TransactionView transactionPage = new TransactionView();
        transactionPage.setOnAction(e -> {
            userStage.close();
            transactionPage.showScene();
        });
         */

        ManageUserAccounts manageUserPage = new ManageUserAccounts();
        manageUserAccounts.setOnAction(e -> {
            userStage.close();
            manageUserPage.showScene();
        });

        VBox buttonBox1 = new VBox(20);
        buttonBox1.getChildren().addAll(add, removeListing, modify);
        buttonBox1.setAlignment(Pos.CENTER_LEFT);
        root.setCenter(buttonBox1);

        HBox buttonBox2 = new HBox(40);
        buttonBox2.getChildren().addAll(transactionView, manageUserAccounts,systemData);
        buttonBox2.setAlignment(Pos.TOP_LEFT);

        VBox listBox = new VBox(20);
        listBox.setPadding(new Insets(10));
        listBox.setPrefWidth(800);
        listBox.setAlignment(Pos.CENTER_LEFT);

        listBox.getChildren().addAll(buttonBox2,listUsers);
        root.setLeft(listBox);

        HBox header = new HBox();
        header.setStyle("-fx-background-color: #E1AD01;");
        header.setPadding(new Insets(15));
        header.setAlignment(Pos.CENTER);

        Label headerLabel = new Label("System Data");
        headerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        headerLabel.setTextFill(Color.DARKRED);

        header.getChildren().add(headerLabel);
        root.setTop(header);


        Scene scene = new Scene(root, 1300, 600);
        userStage.setScene(scene);
        userStage.show();
    }
    private void modifyListing()
    {
        Stage modifyStage = new Stage();
        modifyStage.setTitle("Modify Listing");

        Button returnButton = new Button("Return");
        returnButton.setStyle("-fx-background-color: maroon; -fx-text-fill: white;");
        returnButton.setPrefSize(120,40);

        Button resetButton = new Button("Reset");
        resetButton.setStyle("-fx-background-color: maroon; -fx-text-fill: white;");
        resetButton.setPrefSize(100,40);

        Button addListing = new Button("Add Listing");
        addListing.setStyle("-fx-background-color: maroon; -fx-text-fill: white;");
        addListing.setPrefSize(100,40);

        BorderPane root1 = new BorderPane();

        Label label1 = new Label("Category:");

        ComboBox<String> category = new ComboBox<>();
        category.setMaxSize(220,20);
        category.getItems().addAll("Comics", "History", "Horror", "Sports", "Romance", "Mysteries");

        Label label2 = new Label("Condition:");

        ComboBox<String> condition = new ComboBox<>();
        condition.setMaxSize(220,20);
        condition.getItems().addAll("Fine","Near Fine", "Very Good", "Good", "Fair", "Poor");

        Label label3 = new Label("Name:");

        TextField name = new TextField();
        name.setPrefWidth(300);

        Label label4 = new Label("Set Price:");

        TextField price = new TextField();
        price.setPrefWidth(220);

        VBox vBox = new VBox(10);
        vBox.setPadding(new Insets(10,10,10,10));
        vBox.getChildren().addAll(returnButton, label1, category, label2, condition, label3, name, label4, price);
        vBox.setAlignment(Pos.TOP_LEFT);

        root1.setLeft(vBox);

        GridPane buttonLayout = new GridPane();
        buttonLayout.setPadding(new Insets(70,10,10,50));
        buttonLayout.setHgap(10);
        buttonLayout.setVgap(20);

        buttonLayout.add(resetButton,0,0);
        buttonLayout.add(addListing,0,1);
        buttonLayout.setAlignment(Pos.TOP_LEFT);

        root1.setCenter(buttonLayout);

        returnButton.setOnAction(e -> {modifyStage.close();});

        Scene formScene = new Scene(root1, 500, 500);
        modifyStage.setScene(formScene);
        modifyStage.showAndWait();
    }

}

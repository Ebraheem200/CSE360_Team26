import javafx.animation.PauseTransition;
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
import javafx.util.Duration;


public class TransactionView extends Application {
    private Button transactionView, manageUserAccounts, systemData;
    private TextField buysText, sellText;
    private ListView <String> listView;

    public TransactionView(){
        transactionView = new Button("Transaction View");
        manageUserAccounts = new Button("Manage User Accounts");
        systemData = new Button("System Data");
        buysText = new TextField();
        sellText = new TextField();
        listView = new ListView<>();
    }

    public void start(Stage transactionStage){
        transactionStage.setTitle("Transaction View");

        // root2 layout
        BorderPane root2 = new BorderPane();

        // styles for buttons
        transactionView.setStyle("-fx-background-color: maroon; -fx-text-fill: white;");
        transactionView.setPrefSize(120,40);

        manageUserAccounts.setStyle("-fx-background-color: maroon; -fx-text-fill: white;");
        manageUserAccounts.setPrefSize(150,40);

        systemData.setStyle("-fx-background-color: maroon; -fx-text-fill: white;");
        systemData.setPrefSize(120,40);

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

        Label buys = new Label("Buys:");
        formGrid.add(buys, 0, 0);
        buysText = new TextField();
        formGrid.add(buysText, 1, 0);

        Label passwordSortLabel = new Label("Sells:");
        formGrid.add(passwordSortLabel, 0, 1);
        sellText = new TextField();
        formGrid.add(sellText, 1, 1);

        listBox.getChildren().addAll(buttonBox2,listView, formGrid);
        root2.setLeft(listBox);

        ManageUserAccounts manageUserAccounts = new ManageUserAccounts();
        PauseTransition pause = new PauseTransition(Duration.seconds(0));
       // pause.setOnFinished(event -> manageUserAccounts.start());
        pause.play();
        transactionStage.close();

        HBox header = new HBox();
        header.setStyle("-fx-background-color: #E1AD01;");
        header.setPadding(new Insets(15));
        header.setAlignment(Pos.CENTER);

        Label headerLabel = new Label("Transaction View");
        headerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        headerLabel.setTextFill(Color.DARKRED); // Set text color to dark red

        header.getChildren().add(headerLabel);
        root2.setTop(header);

        Scene scene = new Scene(root2, 1300, 600);
        transactionStage.setScene(scene);
        transactionStage.show();
    }
}

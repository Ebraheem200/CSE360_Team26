package application;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class listViewCell extends ListCell<Book> {
    private HBox content;
    private Label titleLabel;
    private Label detailsLabel;

    public listViewCell() {
        titleLabel = new Label();
        titleLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");
        titleLabel.setTextFill(Color.DARKBLUE);

        detailsLabel = new Label();
        detailsLabel.setStyle("-fx-font-size: 12px;");
        detailsLabel.setTextFill(Color.GRAY);

        content = new HBox(10, titleLabel, detailsLabel);
        content.setAlignment(Pos.CENTER_LEFT);
    }

    @Override
    protected void updateItem(Book book, boolean empty) {
        super.updateItem(book, empty);
        if (empty || book == null) {
            setText(null);
            setGraphic(null);
        } else {
            titleLabel.setText(book.getTitle());
            detailsLabel.setText(String.format("by %s | $%.2f | %s", 
                book.getAuthor(), book.getPrice(), book.getCondition()));
            setGraphic(content);
        }
    }
}

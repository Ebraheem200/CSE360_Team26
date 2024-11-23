package application;

import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.scene.image.ImageView;

public class ListViewCell extends ListCell<Book> {
    private HBox content;
    private Text name;
    private Text author;
    private Text category;
    private ImageView thumbnail;

    public ListViewCell() {
        super();
        name = new Text();
        author = new Text();
        category = new Text();
        thumbnail = new ImageView();
        content = new HBox(thumbnail, name, author, category);
        content.setSpacing(10); // Adjust spacing between elements
    }

    @Override
    protected void updateItem(Book book, boolean empty) {
        super.updateItem(book, empty);
        if (empty || book == null) {
            setText(null);
            setGraphic(null);
        } else {
            name.setText("Name: " + book.getName());
            author.setText("Author: " + book.getAuthor());
            category.setText("Category: " + book.getCategory());
            thumbnail.setImage(book.getThumbnail());
            setGraphic(content);
        }
    }
}

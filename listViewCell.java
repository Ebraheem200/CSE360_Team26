package application;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class listViewCell extends ListCell<Book> {
protected void updateItem(Book book,  boolean empty) {
    super.updateItem(book, empty);
  if (empty || book == null){
         setText(null);
         setGraphic(null);
  } else { 
    HBox hbox = new HBox();
    Text titleText = new Text(book.getTitle());
    Text authorText = new Text(" by " + book.getAuthor());   

    ImageView coverImageView = null;
    if (book.getCoverImage() != null  && !book.getCoverImage().isEmpty(){
      Image CoverImage = new Image("file:" + book.getCoverImage()); 
      coverImageView.setFitHeight(50);
      coverImageView.setFitWidth(50);
    }
    if (coverImageView != null) {
      hbox.getChildren().add(coverImageView);
      hbox.getChlidren().add(authorText);

      setGraphic(hbox);
    }
  }
}
    
    

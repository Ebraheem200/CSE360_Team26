package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.util.ArrayList;

public class DataManager {
    private static final ObservableList<Book> sharedBooks = FXCollections.observableArrayList();
    private static final String DATA_FILE = "BooksData.ser";

    // Prevent instantiation
    private DataManager() {}

    /**
     * Returns the shared ObservableList of books.
     *
     * @return the shared ObservableList of books
     */
    public static ObservableList<Book> getSharedBooks() {
        return sharedBooks;
    }

    /**
     * Saves the current list of books to a file.
     */
    public static void saveBooksToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
            // Convert ObservableList to ArrayList for serialization
            oos.writeObject(new ArrayList<>(sharedBooks));
        } catch (IOException e) {
            System.err.println("Error saving books to file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Loads the list of books from a file into the sharedBooks list.
     * If the file does not exist or an error occurs, it initializes an empty list.
     */
    @SuppressWarnings("unchecked")
    public static void loadBooksFromFile() {
        File file = new File(DATA_FILE);
        if (!file.exists()) {
            System.out.println("Data file not found. Initializing an empty book list.");
            sharedBooks.clear();
            return;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(DATA_FILE))) {
            // Read ArrayList from file and populate ObservableList
            ArrayList<Book> loadedBooks = (ArrayList<Book>) ois.readObject();
            sharedBooks.clear();
            sharedBooks.addAll(loadedBooks);
            System.out.println("Books loaded successfully from file.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading books from file: " + e.getMessage());
            e.printStackTrace();
            sharedBooks.clear(); // Initialize an empty list if an error occurs
        }
    }
}

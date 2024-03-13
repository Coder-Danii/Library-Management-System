import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Class for handling file operations related to library data.
 */
public class LibraryFilehandling {

    /**
     * Load books from a file.
     * 
     * @param fileName The name of the file to load books from.
     * @return A list of books loaded from the file.
     */
    public static List<Book> loadBooksFromFile(String fileName) {
        List<Book> books = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    int bookID = Integer.parseInt(parts[0]);
                    String title = parts[1];
                    String author = parts[2];
                    String genre = parts[3];
                    boolean available = Boolean.parseBoolean(parts[4]);
                    Book book = new Book(bookID, title, author, genre);
                    book.setAvailable(available);
                    books.add(book);
                }
            }
            System.out.println("Books data loaded from file: " + fileName);
        } catch (IOException e) {
            System.out.println("Error while loading books data from file: " + e.getMessage());
        }
        return books;
    }

    /**
     * Load users from a file.
     * 
     * @param fileName The name of the file to load users from.
     * @return A list of users loaded from the file.
     */
    public static List<User> loadUsersFromFile(String fileName) {
        List<User> users = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    int userID = Integer.parseInt(parts[0]);
                    String name = parts[1];
                    String contactInfo = parts[2];
                    User user = new User(name, contactInfo);
                    user.setUserID(userID); // Ensure correct user ID
                    users.add(user);
                }
            }
            System.out.println("Users data loaded from file: " + fileName);
        } catch (IOException e) {
            System.out.println("Error while loading users data from file: " + e.getMessage());
        }
        return users;
    }

    /**
     * Save books to a file.
     * 
     * @param books    The list of books to save.
     * @param fileName The name of the file to save books to.
     */
    public static void saveBooksToFile(List<Book> books, String fileName) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            for (Book book : books) {
                writer.println(book.getBookID() + "," + book.getTitle() + "," + book.getAuthor() + "," + book.getGenre() + "," + book.isAvailable());
            }
            System.out.println("Books data saved to file: " + fileName);
        } catch (IOException e) {
            System.out.println("Error while saving books data to file: " + e.getMessage());
        }
    }

    /**
     * Save users to a file.
     * 
     * @param users    The list of users to save.
     * @param fileName The name of the file to save users to.
     */
    public static void saveUsersToFile(List<User> users, String fileName) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            for (User user : users) {
                writer.println(user.getUserID() + "," + user.getName() + "," + user.getContactInfo());
            }
            System.out.println("Users data saved to file: " + fileName);
        } catch (IOException e) {
            System.out.println("Error while saving users data to file: " + e.getMessage());
        }
    }
}

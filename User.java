import java.util.ArrayList;
import java.util.List;

public class User {
    // Static variable to keep track of the next available user ID
    private static int nextID = 1;
    private int userID;                 // Unique identifier for the user
    private String name;                // Name of the user
    private String contactInfo;         // Contact information of the user
    private List<Book> borrowedBooks;   // List of books borrowed by the user

    // Constructor to initialize a new User object
    public User(String name, String contactInfo) {
        this.userID = nextID++;         // Assigning the next available user ID
        this.name = name;
        this.contactInfo = contactInfo;
        this.borrowedBooks = new ArrayList<>();  // Initializing the list of borrowed books
    }

    // Getter method for retrieving the user ID
    public int getUserID() {
        return userID;
    }

    // Getter method for retrieving the user's name
    public String getName() {
        return name;
    }

    // Getter method for retrieving the user's contact information
    public String getContactInfo() {
        return contactInfo;
    }

    // Getter method for retrieving the list of books borrowed by the user
    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    // Setter method for updating the user ID
    public void setUserID(int userID) {
        this.userID = userID;
    }
}

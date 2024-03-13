import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Library {
    private List<Book> books;   // List to store books in the library
    private List<User> users;   // List to store users of the library

    // Constructor to initialize the lists of books and users
    public Library() {
        this.books = new ArrayList<>();
        this.users = new ArrayList<>();
    }

    // Method to add a book to the library
    public void addBook(Book book) {
        books.add(book);
    }

    // Method to add a user to the library
    public void addUser(User user) {
        users.add(user);
    }

    // Getter method to retrieve the list of books in the library
    public List<Book> getBooks() {
        return books;
    }

    // Getter method to retrieve the list of users in the library
    public List<User> getUsers() {
        return users;
    }

    // Method to allow a user to borrow a book from the library
    public void checkoutBook(String bookQuery, User borrower) {
        // Iterate through the list of books to find the requested book
        for (Book book : books) {
            // Check if the book matches the query and is available
            if ((book.getTitle().equalsIgnoreCase(bookQuery) || book.getAuthor().equalsIgnoreCase(bookQuery)) && book.isAvailable()) {
                // Check if the borrower has already borrowed the book
                if (borrower.getBorrowedBooks().contains(book)) {
                    System.out.println("This book has already been borrowed by " + borrower.getName() + ".");
                } else {
                    // Add the book to the borrower's list of borrowed books
                    borrower.getBorrowedBooks().add(book);
                    // Mark the book as unavailable
                    book.setAvailable(false);
                    // Display success message
                    System.out.println("Book \"" + book.getTitle() + "\" has been successfully borrowed by " + borrower.getName() + ".");
                }
                return;
            }
        }
        // If the book is not found or is not available, display appropriate message
        System.out.println("Book \"" + bookQuery + "\" is not available for borrowing.");
    }

    // Method to allow a user to return a book to the library
    public void returnBook(String bookQuery, User returnUser) {
        // Iterate through the list of books borrowed by the user
        for (Book book : returnUser.getBorrowedBooks()) {
            // Check if the book matches the query
            if (book.getTitle().equalsIgnoreCase(bookQuery)) {
                // Mark the book as available
                book.setAvailable(true);
                // Remove the book from the user's list of borrowed books
                returnUser.getBorrowedBooks().remove(book);
                // Display success message
                System.out.println("Book \"" + book.getTitle() + "\" has been successfully returned by " + returnUser.getName() + ".");
                return;
            }
        }
        // If the book is not found in the user's list of borrowed books, display appropriate message
        System.out.println("Book \"" + bookQuery + "\" was not borrowed by " + returnUser.getName() + ".");
    }

    // Method to search for books by title or author
    public List<Book> searchByTitleOrAuthor(String searchQuery) {
        List<Book> searchResults = new ArrayList<>();
        // Iterate through the list of books to find matching titles or authors
        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(searchQuery.toLowerCase()) || book.getAuthor().toLowerCase().contains(searchQuery.toLowerCase())) {
                searchResults.add(book);
            }
        }
        return searchResults;
    }

    // Method to save the list of books to a file
    public void saveBooksToFile(String fileName) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            // Write each book's information to the file
            for (Book book : books) {
                writer.println(book.getBookID() + "," + book.getTitle() + "," + book.getAuthor() + "," + book.getGenre() + "," + book.isAvailable());
            }
            // Display success message
            System.out.println("Books data saved to file: " + fileName);
        } catch (IOException e) {
            // If an error occurs while saving, display error message
            System.out.println("Error while saving books data to file: " + e.getMessage());
        }
    }

    // Method to save the list of users to a file
    public void saveUsersToFile(String fileName) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            // Write each user's information to the file
            for (User user : users) {
                writer.println(user.getUserID() + "," + user.getName() + "," + user.getContactInfo());
            }
            // Display success message
            System.out.println("Users data saved to file: " + fileName);
        } catch (IOException e) {
            // If an error occurs while saving, display error message
            System.out.println("Error while saving users data to file: " + e.getMessage());
        }
    }

    // Method to load data from files into the library
    public void loadLibraryDataFromFile(String booksFileName, String usersFileName) {
        try (BufferedReader booksReader = new BufferedReader(new FileReader(booksFileName));
            BufferedReader usersReader = new BufferedReader(new FileReader(usersFileName))) {

            String line;
            // Load books
            while ((line = booksReader.readLine()) != null) {
                // Split the line into parts and create a new Book object
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
            // Display success message
            System.out.println("Books data loaded from file: " + booksFileName);

            // Load users
            while ((line = usersReader.readLine()) != null) {
                // Split the line into parts and create a new User object
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    int userID = Integer.parseInt(parts[0]);
                    String name = parts[1];
                    String contactInfo = parts[2];
                    User user = new User(name, contactInfo);
                    user.setUserID(userID);
                    users.add(user);
                }
            }
            // Display success message
            System.out.println("Users data loaded from file: " + usersFileName);

        } catch (IOException e) {
            // If an error occurs while loading, display error message
            System.out.println("Error while loading data from file: " + e.getMessage());
        }
    }
}

public class Book {
    private int bookID;         // Unique identifier for the book
    private String title;       // Title of the book
    private String author;      // Author of the book
    private String genre;       // Genre of the book
    private boolean available;  // Availability status of the book

    // Constructor to initialize a new Book object
    public Book(int bookID, String title, String author, String genre) {
        this.bookID = bookID;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.available = true;  // Initially, the book is available
    }

    // Getter method for retrieving the book ID
    public int getBookID() {
        return bookID;
    }

    // Getter method for retrieving the book title
    public String getTitle() {
        return title;
    }

    // Getter method for retrieving the book author
    public String getAuthor() {
        return author;
    }

    // Getter method for retrieving the book genre
    public String getGenre() {
        return genre;
    }

    // Getter method for retrieving the availability status of the book
    public boolean isAvailable() {
        return available;
    }

    // Setter method for updating the availability status of the book
    public void setAvailable(boolean available) {
        this.available = available;
    }
}

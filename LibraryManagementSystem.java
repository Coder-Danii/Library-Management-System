import java.util.List;
import java.util.Scanner;

public class LibraryManagementSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Library library = new Library();

        boolean exit = false;

        // Display the main menu and handle user input
        System.out.println("----------------------------------------------------------------------------------");
        System.out.println("                 Welcome to the Library Management System!");
        System.out.println("----------------------------------------------------------------------------------");
        while (!exit) {
            System.out.println("1. Add a Book");
            System.out.println("2. Add a User");
            System.out.println("3. Display Books");
            System.out.println("4. Borrow a Book");
            System.out.println("5. Return a Book");
            System.out.println("6. Search for Books by Title or Author");
            System.out.println("7. Search for User by ID");
            System.out.println("8. Save Library Data to File");
            System.out.println("9. Load Library Data from File");
            System.out.println("10. Exit");

            // Handle user input with error handling
            int choice;
            while (true) {
                System.out.print("Enter your choice: ");
                try {
                    choice = Integer.parseInt(scanner.nextLine());
                    if (choice < 1 || choice > 10) {
                        System.out.println("Please enter a number between 1 and 10.");
                        continue;
                    }
                    break; // Valid input, break the loop
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a valid number.");
                }
            }

            // Process user choice based on menu selection
            switch (choice) {
                case 1:
                    addBook(library, scanner);
                    break;
                case 2:
                    addUser(library, scanner);
                    break;
                case 3:
                    displayBooks(library);
                    break;
                case 4:
                    borrowBook(library, scanner);
                    break;
                case 5:
                    returnBook(library, scanner);
                    break;
                case 6:
                    searchBooks(library, scanner);
                    break;
                case 7:
                    searchUserByID(library, scanner);
                    break;
                case 8:
                    library.saveBooksToFile("books.txt");
                    library.saveUsersToFile("users.txt");
                    break;
                case 9:
                    library.loadLibraryDataFromFile("books.txt", "users.txt");
                    break;
                case 10:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice! Please enter a valid option.");
            }
        }
        scanner.close();
    }

    // Method to add a book to the library



    private static void addBook(Library library, Scanner scanner) {
        int bookID=0; // Declare bookID as an int variable
        boolean validInput = false;
    
        do {
            System.out.println("Enter Book ID:");
            String bookIDString = scanner.nextLine();
    
            // Check if the input contains only numeric characters
            if (bookIDString.matches("\\d+")) {
                // Convert bookIDString to an int
                bookID = Integer.parseInt(bookIDString);
                validInput = true;
            } else {
                System.out.println("Invalid input. Please enter the book ID in numbers only.");
            }
        } while (!validInput);
    
        System.out.println("Enter Title:");
        String title = scanner.nextLine();
        System.out.println("Enter Author:");
        String author = scanner.nextLine();
        System.out.println("Enter Genre:");
        String genre = scanner.nextLine();
    
        Book newBook = new Book(bookID, title, author, genre);
        library.addBook(newBook);
        System.out.println("Book added successfully!");
    }
    
    
    
    // Method to add a user to the library


    private static boolean isValidUserName(String userName) {
        // Check if userName contains only letters
        return userName.matches("[a-zA-Z]+");
    }
    
    private static void addUser(Library library, Scanner scanner) {
        String userName;
        boolean validInput = false;
    
        do {
            System.out.println("Enter User Name:");
            userName = scanner.nextLine();
    
            // Check if the input contains only letters
            if (isValidUserName(userName)) {
                validInput = true;
            } else {
                System.out.println("Invalid input. User name can only contain letters.");
            }
        } while (!validInput);
    
        System.out.println("Enter Contact Information:");
        String contactInfo = scanner.nextLine();
    
        User newUser = new User(userName, contactInfo);
        library.addUser(newUser);
        System.out.println("User added successfully! User ID: " + newUser.getUserID());
    }
    

    // Method to display all books in the library
    private static void displayBooks(Library library) {
        System.out.println("List of Books:");
        for (Book book : library.getBooks()) {
            System.out.println("Book ID: " + book.getBookID() + ", Title: " + book.getTitle() + ", Author: " + book.getAuthor() + ", Genre: " + book.getGenre() + ", Availability: " + (book.isAvailable() ? "Available" : "Not Available"));
        }
    }

    // Method to allow a user to borrow a book from the library
    private static void borrowBook(Library library, Scanner scanner) {
        System.out.println("Enter User ID:");
        int userID = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.println("Enter Book Title or Author:");
        String bookQuery = scanner.nextLine();
        User borrower = findUserByID(userID, library);
        if (borrower != null) {
            library.checkoutBook(bookQuery, borrower);
        } else {
            System.out.println("User not found!");
        }
    }

    // Method to allow a user to return a book to the library
    private static void returnBook(Library library, Scanner scanner) {
        System.out.println("Enter User ID:");
        int returnUserID = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.println("Enter Book Title or Author:");
        String returnBookQuery = scanner.nextLine();
        User returnUser = findUserByID(returnUserID, library);
        if (returnUser != null) {
            library.returnBook(returnBookQuery, returnUser);
        } else {
            System.out.println("User not found!");
        }
    }

    // Method to search for books by title or author
    private static void searchBooks(Library library, Scanner scanner) {
        System.out.println("Enter search query (Title or Author):");
        String searchQuery = scanner.nextLine();
        System.out.println("Search results:");
        List<Book> searchResults = library.searchByTitleOrAuthor(searchQuery);
        if (searchResults.isEmpty()) {
            System.out.println("No books found matching the search query.");
        } else {
            for (Book book : searchResults) {
                System.out.println("Book ID: " + book.getBookID() + ", Title: " + book.getTitle() + ", Author: " + book.getAuthor() + ", Genre: " + book.getGenre() + ", Availability: " + (book.isAvailable() ? "Available" : "Not Available"));
            }
        }
    }

    // Method to search for a user by ID
    private static void searchUserByID(Library library, Scanner scanner) {
        System.out.println("Enter User ID:");
        int userID = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        User user = findUserByID(userID, library);
        if (user != null) {
            List<Book> borrowedBooks = user.getBorrowedBooks();
            if (!borrowedBooks.isEmpty()) {
                System.out.println("User has borrowed the following books:");
                for (Book book : borrowedBooks) {
                    System.out.println("Book ID: " + book.getBookID() + ", Title: " + book.getTitle() + ", Author: " + book.getAuthor());
                }
            } else {
                System.out.println("User has not borrowed any books.");
            }
        } else {
            System.out.println("User not found!");
        }
    }

    // Method to find a user by ID
    private static User findUserByID(int userID, Library library) {
        for (User user : library.getUsers()) {
            if (user.getUserID() == userID) {
                return user;
            }
        }
        return null;
    }
}

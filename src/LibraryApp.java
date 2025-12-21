import java.util.ArrayList;
import java.util.Scanner;

public class LibraryApp {

    private ArrayList<Book> books = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public void run() {
        boolean running = true;

        while (running) {
            printMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> printAllBooks();
                case 2 -> addBook();
                case 3 -> searchByTitle();
                case 4 -> borrowBook();
                case 5 -> returnBook();
                case 6 -> deleteBook();
                case 7 -> running = false;
                default -> System.out.println("Invalid option");
            }
        }
    }

    private void printMenu() {
        System.out.println("\nWelcome to Library App!");
        System.out.println("1. Print all books");
        System.out.println("2. Add new book");
        System.out.println("3. Search books by title");
        System.out.println("4. Borrow a book");
        System.out.println("5. Return a book");
        System.out.println("6. Delete a book by id");
        System.out.println("7. Quit");
        System.out.print("Choose option: ");
    }

    private void printAllBooks() {
        if (books.isEmpty()) {
            System.out.println("No books in the library");
            return;
        }
        for (Book book : books) {
            System.out.println(book);
        }
    }

    private void addBook() {
        System.out.print("Title: ");
        String title = scanner.nextLine();

        System.out.print("Author: ");
        String author = scanner.nextLine();

        System.out.print("Year: ");
        int year = scanner.nextInt();
        scanner.nextLine();

        books.add(new Book(title, author, year));
        System.out.println("Book added");
    }

    private void searchByTitle() {
        System.out.print("Enter title part: ");
        String part = scanner.nextLine().toLowerCase();

        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(part)) {
                System.out.println(book);
            }
        }
    }

    private void borrowBook() {
        System.out.print("Enter book id: ");
        int id = scanner.nextInt();

        for (Book book : books) {
            if (book.getId() == id) {
                if (book.isAvailable()) {
                    book.markAsBorrowed();
                    System.out.println("Book borrowed");
                } else {
                    System.out.println("Book already borrowed");
                }
                return;
            }
        }
        System.out.println("Book not found");
    }

    private void returnBook() {
        System.out.print("Enter book id: ");
        int id = scanner.nextInt();

        for (Book book : books) {
            if (book.getId() == id) {
                if (!book.isAvailable()) {
                    book.markAsReturned();
                    System.out.println("Book returned");
                } else {
                    System.out.println("Book was not borrowed");
                }
                return;
            }
        }
        System.out.println("Book not found");
    }

    private void deleteBook() {
        System.out.print("Enter book id: ");
        int id = scanner.nextInt();

        for (Book book : books) {
            if (book.getId() == id) {
                books.remove(book);
                System.out.println("Book deleted");
                return;
            }
        }
        System.out.println("Book not found");
    }

    public static void main(String[] args) {
        new LibraryApp().run();
    }
}

package Collection2.copy;

import java.util.Scanner;

public class BookArrayListTest {

    public static void main(String[] args) {
        BookArrayList bookArrayList = new BookArrayList();

        Book book1 = new Book(0001, "파우스트");
        Book book2 = new Book(0002, "황무지");
        Book book3 = new Book(0003, "변신");
        Book book4 = new Book(0004, "픽션들");
        Book book5 = new Book(0005, "톨스토이");

        bookArrayList.addBook(book1);
        bookArrayList.addBook(book2);
        bookArrayList.addBook(book3);
        bookArrayList.addBook(book4);
        bookArrayList.addBook(book5);

        bookArrayList.showAllBook();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the index to insert the new book: ");
        int insertIndex = scanner.nextInt();

        System.out.print("Enter the book ID: ");
        int bookId = scanner.nextInt();

        // Consume the newline character left by the previous input.nextInt()
        scanner.nextLine();

        System.out.print("Enter the book title: ");
        String title = scanner.nextLine();

        Book newBook = new Book(bookId, title);
        bookArrayList.insertBook(insertIndex, newBook);

        bookArrayList.showAllBook();

        scanner.close();
    }
}

package library;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Library <T extends Book> {
    private List<T> books = new ArrayList<>();

    public void addBook(T book) {
        books.add(book);
        System.out.println("Added: " + book.getBookDetails());
    }

    public void removeBook(T book) {
        books.remove(book);
        System.out.println("Removed: " + book.getBookDetails());
    }

    public void removeBook(String bookName) {
        Book bookToRemove = books.stream()
                .filter(b -> b.getTitle().equalsIgnoreCase(bookName))
                .findFirst()
                .orElse(null);
        if (bookToRemove != null) {
            books.remove(bookToRemove);
            System.out.println("Removed: " + bookToRemove.getBookDetails());
        } else {
            System.out.println("Book not found");
        }
    }

    public void displayBooks() {
        System.out.println("Library Books:");
        for (T book : books) {
            System.out.println(book.getBookDetails());
        }
    }

    public void searchByPublicationYear(int publicationYear) {
        List<Book> booksPublishedThatYear = books.stream()
                .filter(b -> b.getPublicationYear() == publicationYear)
                .collect(Collectors.toList());

        if (booksPublishedThatYear.isEmpty()) {
            System.out.println("No library books were published in the year " + publicationYear);
        } else {
            System.out.println("Library books that were published in the year " + publicationYear + ":");
            booksPublishedThatYear.forEach(book -> System.out.println(book.getBookDetails()));
        }
    }

    public void searchByAuthor(String author) {
        List<Book> booksByAuthor = books.stream()
                .filter(b -> b.getAuthor().equalsIgnoreCase(author))
                .collect(Collectors.toList());

        if (booksByAuthor.isEmpty()) {
            System.out.println("No library books were written by " + author);
        } else {
            System.out.println("Library books that were written by " + author + ":");
            booksByAuthor.forEach(book -> System.out.println(book.getBookDetails()));
        }
    }

    public void findMostPages() {
        Book mostPages = books.stream()
                .max(Comparator.comparingInt(Book::getPages))
                .orElse(null);

        if (mostPages != null) {
            System.out.println("Most Pages: " + mostPages.getBookDetails());
        } else {
            System.out.println("No books available.");
        }
    }

    public void findAtLeastPages(int pages) {
        List<Book> booksWithPages = books.stream()
                .filter(b -> pages >= b.getPages())
                .collect(Collectors.toList());

        if (booksWithPages.isEmpty()) {
            System.out.println("No library books with at least " + pages + " pages:");
        } else {
            System.out.println("Library books with at least  " + pages + " pages:");
            booksWithPages.forEach(book -> System.out.println(book.getBookDetails()));
        }
    }

    public void titlesAlphabetically() {
        List<Book> booksAlphabetically = books.stream()
                .sorted(Comparator.comparing(Book::getTitle))
                .collect(Collectors.toList());

        if (booksAlphabetically.isEmpty()) {
            System.out.println("No books in the library");
        } else {
            System.out.println("Library books alphabetically:");
            booksAlphabetically.forEach(book -> System.out.println(book.getBookDetails()));
        }

    }

    public void searchByCategory(String category) {
        List<Book> booksByCategory = books.stream()
                .filter(b -> b.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());

        if (booksByCategory.isEmpty()) {
            System.out.println("No library books with category " + category);
        } else {
            System.out.println("Library books in category " + category + ":");
            booksByCategory.forEach(book -> System.out.println(book.getBookDetails()));
        }
    }

    public void lendBook(User user, Book book) {
        if (book.isOnLoan()) {
            System.out.println("That book is already checked out");
        } else {
            book.setOnLoan(true);
            user.addBook(book);
            System.out.println("Lent book: " + book.getBookDetails() + " to " + user.getName());
        }
    }

    public void returnBook(User user, Book book) {
        if (user.getBooks().contains(book)) {
            book.setOnLoan(false);
            user.removeBook(book);
            System.out.println(user.getName() + " returned: " + book.getBookDetails());
        } else {
            System.out.println("That is not a valid book to return");
        }
    }

    public void setLate(User user, Book book) {
        if (user.getBooks().contains(book)) {
            book.setLate(true);
            user.setFees(5);
            System.out.println(book.getTitle() + " is late and " + user.getName() + " has incurred the initial fee of $" + user.getFees());
        } else {
            System.out.println("The user has not checked out that book");
        }
    }

    public void increaseFees(User user, Book book) {
        if (user.getBooks().contains(book)) {
            user.setFees(user.getFees() + 5);
            System.out.println(book.getTitle() + " has still not been returned so " + user.getName() + "has accrued total late fees equal to $" + user.getFees());
        }
    }



}
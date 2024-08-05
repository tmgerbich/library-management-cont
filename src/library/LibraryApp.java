package library;

public class LibraryApp {
    public static void main(String[] args) {
        Library<Book> libraryManager= new Library<>();

        Book book1 =  new Book("Green Eggs and Ham", "Dr Seuss", 1962, 23, "Children");
        Book book2 = new Book("Where the Wild Things Are", "Maurice Sendak", 1960, 29, "Children");
        User user1 = new User("Alice", 21300);

        libraryManager.addBook(book1);
        libraryManager.addBook(book2);
        libraryManager.displayBooks();
        libraryManager.searchByPublicationYear(1960);
        libraryManager.searchByPublicationYear(1970);
        libraryManager.searchByAuthor("dr seuss");
        libraryManager.searchByAuthor("Maurice");
        libraryManager.findMostPages();
        libraryManager.findAtLeastPages(24);
        libraryManager.titlesAlphabetically();
        libraryManager.searchByCategory("Children");
        libraryManager.lendBook(user1, book2);
        libraryManager.setLate(user1, book2);
        libraryManager.increaseFees(user1, book2);
        libraryManager.increaseFees(user1, book2);
        libraryManager.returnBook(user1, book2);
        libraryManager.removeBook("Where the Wild Things");
        libraryManager.removeBook("Where the Wild Things Are");
        libraryManager.displayBooks();



    }
}

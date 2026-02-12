import java.util.*;

class BookUnavailableException extends Exception {
    BookUnavailableException(String m) {
        super(m);
    }
}

class Book {
    private int id;
    private String title;
    private String author;
    private boolean available = true;

    Book(int i, String t, String a) {
        id = i;
        title = t;
        author = a;
    }

    int getId() {
        return id;
    }

    String getTitle() {
        return title;
    }

    String getAuthor() {
        return author;
    }

    boolean isAvailable() {
        return available;
    }

    void issue() {
        available = false;
    }

    void giveBack() {
        available = true;
    }
}

class Member {
    int id;
    String name;

    Member(int i, String n) {
        id = i;
        name = n;
    }
}

class Library {
    List<Book> books = new ArrayList<>();
    Map<Integer, Integer> issued = new HashMap<>();
    Map<Integer, Integer> issueDays = new HashMap<>();

    void addBook(Book b) {
        books.add(b);
    }

    void issueBook(int bookId, int memberId, int days) throws BookUnavailableException {
        for (Book b : books) {
            if (b.getId() == bookId) {
                if (!b.isAvailable())
                    throw new BookUnavailableException("Book not available");
                b.issue();
                issued.put(bookId, memberId);
                issueDays.put(bookId, days);
                System.out.println("Book Issued");
                return;
            }
        }
    }

    void returnBook(int bookId, int daysUsed) {
        for (Book b : books) {
            if (b.getId() == bookId) {
                b.giveBack();
                int days = issueDays.getOrDefault(bookId, 0);
                if (daysUsed > days) {
                    int fine = (daysUsed - days) * 10;
                    System.out.println("Late Fine: " + fine);
                }
                issued.remove(bookId);
                issueDays.remove(bookId);
                System.out.println("Book Returned");
                return;
            }
        }
    }

    void sortByTitle() {
        books.sort(Comparator.comparing(Book::getTitle));
    }

    void sortByAuthor() {
        books.sort(Comparator.comparing(Book::getAuthor));
    }

    void showBooks() {
        for (Book b : books)
            System.out.println(b.getId() + " " + b.getTitle() + " " + b.getAuthor() + " Available:" + b.isAvailable());
    }
}

public class Task8 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Library l = new Library();

        l.addBook(new Book(1, "Java", "James"));
        l.addBook(new Book(2, "Python", "Guido"));
        l.addBook(new Book(3, "C", "Dennis"));

        while (true) {
            System.out.println("1.Show 2.Issue 3.Return 4.SortTitle 5.SortAuthor 6.Exit");
            int ch = sc.nextInt();

            if (ch == 1) {
                l.showBooks();
            } else if (ch == 2) {
                int bid = sc.nextInt();
                int mid = sc.nextInt();
                int days = sc.nextInt();
                try {
                    l.issueBook(bid, mid, days);
                } catch (BookUnavailableException e) {
                    System.out.println(e.getMessage());
                }
            } else if (ch == 3) {
                int bid = sc.nextInt();
                int daysUsed = sc.nextInt();
                l.returnBook(bid, daysUsed);
            } else if (ch == 4) {
                l.sortByTitle();
            } else if (ch == 5) {
                l.sortByAuthor();
            } else {
                break;
            }
        }
    }
}

import java.util.*;

class Movie {
    private String name;

    Movie(String n) {
        name = n;
    }

    String getName() {
        return name;
    }
}

class Theatre {
    private int seats;
    private Map<String, Double> price = new HashMap<>();
    private List<String> history = new ArrayList<>();

    Theatre(int s) {
        seats = s;
        price.put("Regular", 200.0);
        price.put("Premium", 350.0);
    }

    void book(String user, String category) {
        synchronized (this) {
            if (seats > 0) {
                seats--;
                double p = price.getOrDefault(category, 200.0);
                history.add(user + " booked " + category + " seat Price:" + p);
                System.out.println(user + " booked ticket Price:" + p);
            } else {
                System.out.println("No seats for " + user);
            }
        }
    }

    void showHistory() {
        for (String s : history)
            System.out.println(s);
    }
}

class UserThread extends Thread {
    Theatre t;
    String name;
    String category;

    UserThread(Theatre t, String n, String c) {
        this.t = t;
        name = n;
        category = c;
    }

    public void run() {
        t.book(name, category);
    }
}

public class Task5 {
    public static void main(String[] args) {
        Movie m = new Movie("Avengers");
        Theatre t = new Theatre(3);

        UserThread u1 = new UserThread(t, "User1", "Regular");
        UserThread u2 = new UserThread(t, "User2", "Premium");
        UserThread u3 = new UserThread(t, "User3", "Regular");
        UserThread u4 = new UserThread(t, "User4", "Premium");

        u1.start();
        u2.start();
        u3.start();
        u4.start();

        try {
            u1.join();
            u2.join();
            u3.join();
            u4.join();
        } catch (Exception e) {}

        System.out.println("Booking History:");
        t.showHistory();
    }
}

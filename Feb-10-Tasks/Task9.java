import java.util.*;
import java.util.stream.*;
import java.io.*;

class Product {
    private int id;
    private String name;
    private int stock;

    Product(int i, String n, int s) {
        id = i;
        name = n;
        if (s >= 0) stock = s;
    }

    int getId() {
        return id;
    }

    String getName() {
        return name;
    }

    int getStock() {
        return stock;
    }

    void setStock(int s) {
        if (s >= 0) stock = s;
    }
}

class Inventory {
    Map<Integer, Product> map = new HashMap<>();

    void add(Product p) {
        map.put(p.getId(), p);
    }

    void update(int id, int stock) {
        if (map.containsKey(id))
            map.get(id).setStock(stock);
    }

    void delete(int id) {
        map.remove(id);
    }

    Product search(int id) {
        return map.get(id);
    }

    List<Product> search(String name) {
        return map.values()
                .stream()
                .filter(p -> p.getName().equalsIgnoreCase(name))
                .collect(Collectors.toList());
    }

    void report() {
        map.values()
                .forEach(p -> System.out.println(p.getId() + " " + p.getName() + " Stock:" + p.getStock()));
    }

    void exportFile() {
        try {
            PrintWriter pw = new PrintWriter("stock.txt");
            for (Product p : map.values())
                pw.println(p.getId() + " " + p.getName() + " Stock:" + p.getStock());
            pw.close();
        } catch (Exception e) {}
    }
}

public class Task9 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Inventory i = new Inventory();

        i.add(new Product(1, "Pen", 50));
        i.add(new Product(2, "Book", 30));
        i.add(new Product(3, "Bag", 20));

        while (true) {
            System.out.println("1.Report 2.Update 3.Delete 4.SearchID 5.SearchName 6.Export 7.Exit");
            int ch = sc.nextInt();

            if (ch == 1) {
                i.report();
            } else if (ch == 2) {
                int id = sc.nextInt();
                int s = sc.nextInt();
                i.update(id, s);
            } else if (ch == 3) {
                int id = sc.nextInt();
                i.delete(id);
            } else if (ch == 4) {
                int id = sc.nextInt();
                Product p = i.search(id);
                if (p != null)
                    System.out.println(p.getId() + " " + p.getName() + " " + p.getStock());
            } else if (ch == 5) {
                String n = sc.next();
                i.search(n)
                        .forEach(p -> System.out.println(p.getId() + " " + p.getName() + " " + p.getStock()));
            } else if (ch == 6) {
                i.exportFile();
                System.out.println("Exported");
            } else {
                break;
            }
        }
    }
}
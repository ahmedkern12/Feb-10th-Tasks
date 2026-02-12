import java.util.*;
import java.util.stream.*;

interface Product {
    String getName();
    double getPrice();
}

class Electronics implements Product {
    String name;
    double price;

    Electronics(String n, double p) {
        name = n;
        price = p;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}

class Clothing implements Product {
    String name;
    double price;

    Clothing(String n, double p) {
        name = n;
        price = p;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}

class Groceries implements Product {
    String name;
    double price;

    Groceries(String n, double p) {
        name = n;
        price = p;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}

interface DiscountStrategy {
    double applyDiscount(double total);
}

class NoDiscount implements DiscountStrategy {
    public double applyDiscount(double total) {
        return total;
    }
}

class TenPercentDiscount implements DiscountStrategy {
    public double applyDiscount(double total) {
        return total * 0.9;
    }
}

class TwentyPercentDiscount implements DiscountStrategy {
    public double applyDiscount(double total) {
        return total * 0.8;
    }
}

class Cart {
    List<Product> products = new ArrayList<>();

    void addProduct(Product p) {
        products.add(p);
    }

    double totalPrice() {
        return products.stream()
                .mapToDouble(Product::getPrice)
                .sum();
    }

    double finalPrice(DiscountStrategy d) {
        return d.applyDiscount(totalPrice());
    }

    void sortByPrice() {
        products.sort(Comparator.comparing(Product::getPrice));
    }

    void showProducts() {
        for (Product p : products)
            System.out.println(p.getName() + " " + p.getPrice());
    }
}

public class Task2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Cart cart = new Cart();

        cart.addProduct(new Electronics("Laptop", 50000));
        cart.addProduct(new Clothing("Shirt", 1500));
        cart.addProduct(new Groceries("Rice", 800));

        System.out.println("Products:");
        cart.showProducts();

        System.out.println("Total: " + cart.totalPrice());

        System.out.println("1.No Discount 2.10% 3.20%");
        int ch = sc.nextInt();

        DiscountStrategy d;
        if (ch == 2)
            d = new TenPercentDiscount();
        else if (ch == 3)
            d = new TwentyPercentDiscount();
        else
            d = new NoDiscount();

        System.out.println("Final Price: " + cart.finalPrice(d));

        cart.sortByPrice();
        System.out.println("Sorted Products:");
        cart.showProducts();
    }
}
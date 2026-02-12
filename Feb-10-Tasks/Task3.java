import java.util.*;

abstract class Vehicle {
    int id;
    String name;
    double rate;
    boolean rented = false;

    Vehicle(int i, String n, double r) {
        id = i;
        name = n;
        rate = r;
    }

    abstract double rentPrice(int days);

    void rent() {
        rented = true;
    }

    boolean isRented() {
        return rented;
    }
}

class Car extends Vehicle {
    Car(int i, String n, double r) {
        super(i, n, r);
    }

    double rentPrice(int days) {
        return rate * days;
    }
}

class Bike extends Vehicle {
    Bike(int i, String n, double r) {
        super(i, n, r);
    }

    double rentPrice(int days) {
        return rate * days * 0.8;
    }
}

class Truck extends Vehicle {
    Truck(int i, String n, double r) {
        super(i, n, r);
    }

    double rentPrice(int days) {
        return rate * days * 1.5;
    }
}

class RentalSystem {
    ArrayList<Vehicle> list = new ArrayList<>();

    void addVehicle(Vehicle v) {
        list.add(v);
    }

    void showVehicles() {
        for (Vehicle v : list)
            System.out.println(v.id + " " + v.name + " Rented:" + v.rented);
    }

    Vehicle find(int id) {
        for (Vehicle v : list)
            if (v.id == id) return v;
        return null;
    }
}

class BookingThread extends Thread {
    RentalSystem system;
    int id;
    int days;

    BookingThread(RentalSystem s, int i, int d) {
        system = s;
        id = i;
        days = d;
    }

    public void run() {
        Vehicle v = system.find(id);
        if (v != null && !v.isRented()) {
            v.rent();
            System.out.println("Booked " + v.name + " Price:" + v.rentPrice(days));
        } else {
            System.out.println("Vehicle not available");
        }
    }
}

public class Task3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        RentalSystem rs = new RentalSystem();

        rs.addVehicle(new Car(1, "Car", 2000));
        rs.addVehicle(new Bike(2, "Bike", 500));
        rs.addVehicle(new Truck(3, "Truck", 4000));

        while (true) {
            System.out.println("1.Show 2.Rent 3.Thread Booking 4.Exit");
            int ch = sc.nextInt();

            if (ch == 1) {
                rs.showVehicles();
            } else if (ch == 2) {
                int id = sc.nextInt();
                int days = sc.nextInt();
                Vehicle v = rs.find(id);
                if (v != null && !v.isRented()) {
                    v.rent();
                    System.out.println("Price:" + v.rentPrice(days));
                } else {
                    System.out.println("Vehicle not available");
                }
            } else if (ch == 3) {
                int id = sc.nextInt();
                int days = sc.nextInt();
                new BookingThread(rs, id, days).start();
            } else {
                break;
            }
        }
    }
}
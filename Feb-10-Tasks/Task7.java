import java.util.*;

interface PaymentMethod {
    void pay(double amount);
}

class UPIPayment implements PaymentMethod {
    public void pay(double amount) {
        System.out.println("UPI Payment Done: " + amount);
    }
}

class CreditCardPayment implements PaymentMethod {
    public void pay(double amount) {
        System.out.println("Credit Card Payment Done: " + amount);
    }
}

class NetBankingPayment implements PaymentMethod {
    public void pay(double amount) {
        System.out.println("Net Banking Payment Done: " + amount);
    }
}

class PaymentFactory {
    static PaymentMethod getPayment(String type) {
        if (type.equalsIgnoreCase("upi"))
            return new UPIPayment();
        else if (type.equalsIgnoreCase("card"))
            return new CreditCardPayment();
        else if (type.equalsIgnoreCase("net"))
            return new NetBankingPayment();
        return null;
    }
}

class PaymentLogger {
    static List<String> log = new ArrayList<>();

    static void add(String msg) {
        log.add(msg);
    }

    static void show() {
        for (String s : log)
            System.out.println(s);
    }
}

public class Task7 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter payment type (upi/card/net):");
        String type = sc.next();

        System.out.println("Enter amount:");
        double amt = sc.nextDouble();

        PaymentMethod p = PaymentFactory.getPayment(type);

        if (p != null) {
            p.pay(amt);
            PaymentLogger.add(type + " payment success: " + amt);
        } else {
            System.out.println("Invalid payment type");
            PaymentLogger.add("Failed transaction");
        }

        System.out.println("Transaction Log:");
        PaymentLogger.show();
    }
}

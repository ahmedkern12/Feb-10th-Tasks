import java.util.*;

class InsufficientBalanceException extends Exception {
    public InsufficientBalanceException(String m) {
        super(m);
    }
}

abstract class BankAccount {
    private String accNo;
    private double balance;
    ArrayList<String> history = new ArrayList<>();

    public BankAccount(String a, double b) {
        accNo = a;
        if (b >= 0) balance = b;
    }

    public String getAccNo() {
        return accNo;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amt) {
        if (amt > 0) {
            balance += amt;
            history.add("Deposit " + amt + " Bal:" + balance);
        }
    }

    protected void setBalance(double b) {
        balance = b;
    }

    public abstract void withdraw(double amt) throws InsufficientBalanceException;

    public void showHistory() {
        for (String s : history) System.out.println(s);
    }
}

class SavingsAccount extends BankAccount {
    public SavingsAccount(String a, double b) {
        super(a, b);
    }

    public void withdraw(double amt) throws InsufficientBalanceException {
        if (amt > getBalance())
            throw new InsufficientBalanceException("No balance");
        setBalance(getBalance() - amt);
        history.add("Withdraw " + amt + " Bal:" + getBalance());
    }
}

class CurrentAccount extends BankAccount {
    double limit = 5000;

    public CurrentAccount(String a, double b) {
        super(a, b);
    }

    public void withdraw(double amt) throws InsufficientBalanceException {
        if (getBalance() + limit < amt)
            throw new InsufficientBalanceException("Limit over");
        setBalance(getBalance() - amt);
        history.add("Withdraw " + amt + " Bal:" + getBalance());
    }
}

public class Task1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        HashMap<String, BankAccount> map = new HashMap<>();

        while (true) {
            System.out.println("\n1.Savings 2.Current 3.Deposit 4.Withdraw 5.Balance 6.History 7.Exit");
            int ch = sc.nextInt();

            if (ch == 1) {
                System.out.print("Account No: ");
                String a = sc.next();
                System.out.print("Balance: ");
                double b = sc.nextDouble();
                map.put(a, new SavingsAccount(a, b));
            } else if (ch == 2) {
                System.out.print("Account No: ");
                String a = sc.next();
                System.out.print("Balance: ");
                double b = sc.nextDouble();
                map.put(a, new CurrentAccount(a, b));
            } else if (ch == 3) {
                System.out.print("Account No: ");
                String a = sc.next();
                System.out.print("Amount: ");
                double amt = sc.nextDouble();
                if (map.containsKey(a)) map.get(a).deposit(amt);
            } else if (ch == 4) {
                System.out.print("Account No: ");
                String a = sc.next();
                System.out.print("Amount: ");
                double amt = sc.nextDouble();
                try {
                    if (map.containsKey(a)) map.get(a).withdraw(amt);
                } catch (InsufficientBalanceException e) {
                    System.out.println(e.getMessage());
                }
            } else if (ch == 5) {
                System.out.print("Account No: ");
                String a = sc.next();
                if (map.containsKey(a))
                    System.out.println("Balance: " + map.get(a).getBalance());
            } else if (ch == 6) {
                System.out.print("Account No: ");
                String a = sc.next();
                if (map.containsKey(a))
                    map.get(a).showHistory();
            } else if (ch == 7) {
                break;
            }
        }
    }
}

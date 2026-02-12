import java.util.*;

interface Employee {
    double calculateSalary();
    String getName();
}

class FullTimeEmployee implements Employee {
    String name;
    double monthlySalary;

    private FullTimeEmployee(Builder b) {
        name = b.name;
        monthlySalary = b.salary;
    }

    public double calculateSalary() {
        return monthlySalary;
    }

    public String getName() {
        return name;
    }

    static class Builder {
        String name;
        double salary;

        Builder setName(String n) {
            name = n;
            return this;
        }

        Builder setSalary(double s) {
            salary = s;
            return this;
        }

        FullTimeEmployee build() {
            return new FullTimeEmployee(this);
        }
    }
}

class PartTimeEmployee implements Employee {
    String name;
    double hours;
    double rate;

    private PartTimeEmployee(Builder b) {
        name = b.name;
        hours = b.hours;
        rate = b.rate;
    }

    public double calculateSalary() {
        return hours * rate;
    }

    public String getName() {
        return name;
    }

    static class Builder {
        String name;
        double hours;
        double rate;

        Builder setName(String n) {
            name = n;
            return this;
        }

        Builder setHours(double h) {
            hours = h;
            return this;
        }

        Builder setRate(double r) {
            rate = r;
            return this;
        }

        PartTimeEmployee build() {
            return new PartTimeEmployee(this);
        }
    }
}

class ContractEmployee implements Employee {
    String name;
    double amount;

    private ContractEmployee(Builder b) {
        name = b.name;
        amount = b.amount;
    }

    public double calculateSalary() {
        return amount;
    }

    public String getName() {
        return name;
    }

    static class Builder {
        String name;
        double amount;

        Builder setName(String n) {
            name = n;
            return this;
        }

        Builder setAmount(double a) {
            amount = a;
            return this;
        }

        ContractEmployee build() {
            return new ContractEmployee(this);
        }
    }
}

public class Task4 {
    public static void main(String[] args) {
        List<Employee> list = new ArrayList<>();

        list.add(new FullTimeEmployee.Builder()
                .setName("Roy")
                .setSalary(40000)
                .build());

        list.add(new PartTimeEmployee.Builder()
                .setName("Ben")
                .setHours(80)
                .setRate(300)
                .build());

        list.add(new ContractEmployee.Builder()
                .setName("Bassi")
                .setAmount(25000)
                .build());

        double total = 0;

        for (Employee e : list) {
            double sal = e.calculateSalary();
            System.out.println(e.getName() + " Salary: " + sal);
            total += sal;
        }

        System.out.println("Total Payroll: " + total);
    }
}
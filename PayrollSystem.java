import java.io.*;
import java.util.*;

// Base Class
class Employee {
    int id;
    String name;
    double baseSalary;

    Employee(int id, String name, double baseSalary) {
        this.id = id;
        this.name = name;
        this.baseSalary = baseSalary;
    }

    double calculateSalary() {
        return baseSalary;
    }
}

// Full Time Employee
class FullTimeEmployee extends Employee {
    double bonus;

    FullTimeEmployee(int id, String name, double baseSalary, double bonus) {
        super(id, name, baseSalary);
        this.bonus = bonus;
    }

    @Override
    double calculateSalary() {
        return baseSalary + bonus;
    }
}

// Part Time Employee
class PartTimeEmployee extends Employee {
    int hours;
    double rate;

    PartTimeEmployee(int id, String name, int hours, double rate) {
        super(id, name, 0);
        this.hours = hours;
        this.rate = rate;
    }

    @Override
    double calculateSalary() {
        return hours * rate;
    }
}

// Main Class
public class PayrollSystem {

    static double calculateTax(double salary) {
        if (salary > 50000)
            return salary * 0.2;
        else
            return salary * 0.1;
    }

    public static void main(String[] args) throws IOException {

        ArrayList<Employee> employees = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Payroll System ---");
            System.out.println("1. Add Full-Time Employee");
            System.out.println("2. Add Part-Time Employee");
            System.out.println("3. Display Payroll");
            System.out.println("4. Save to File");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();

            switch (choice) {

                case 1 -> {
                    System.out.print("ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Name: ");
                    String name = sc.nextLine();

                    System.out.print("Base Salary: ");
                    double salary = sc.nextDouble();

                    System.out.print("Bonus: ");
                    double bonus = sc.nextDouble();

                    employees.add(new FullTimeEmployee(id, name, salary, bonus));
                }

                case 2 -> {
                    System.out.print("ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Name: ");
                    String name = sc.nextLine();
                    System.out.print("Hours Worked: ");
                    int hours = sc.nextInt();
                    System.out.print("Rate per Hour: ");
                    double rate = sc.nextDouble();
                    employees.add(new PartTimeEmployee(id, name, hours, rate));
                }

                case 3 -> {
                    for (Employee e : employees) {
                        double gross = e.calculateSalary();
                        double tax = calculateTax(gross);
                        double net = gross - tax;

                        System.out.println("\nID: " + e.id);
                        System.out.println("Name: " + e.name);
                        System.out.println("Gross Salary: " + gross);
                        System.out.println("Tax: " + tax);
                        System.out.println("Net Salary: " + net);
                    }
                }

                case 4 -> {
                    try (FileWriter writer = new FileWriter("employees.txt")) {
                        for (Employee e : employees) {
                            writer.write(e.id + "," + e.name + "," + e.calculateSalary() + "\n");
                        }
                    }
                    System.out.println("Data saved to file.");
                }


                case 5 -> {
                    System.out.println("Exiting...");
                    return;
                }

                default -> System.out.println("Invalid choice!");
            }
        }
    }
}
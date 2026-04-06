package banking;

import java.util.*;
import java.io.*;

class LowDepositException extends Exception{
    LowDepositException(){
        super("Account must be created with a minimum balance of Rs.1000.");
    }
}

class OverdraftException extends Exception {
    OverdraftException(){
        super("Withdrawal amount exceeds total balance.");
    }
}


class InvalidAmountException extends Exception {
    InvalidAmountException() {
        super("Cash amount must be positive.");
    }
}

class BadCustomerIDException extends Exception {
    BadCustomerIDException() {
        super("CustID must be in range of 1 to 20.");
    }
}

class Customer {
    int cid;
    String cname;
    double amount;

    Customer(int cid, String cname, double amount) {
        this.cid    = cid;
        this.cname  = cname;
        this.amount = amount;
    }
    void deposit(double amt) throws InvalidAmountException {
        if (amt <= 0) throw new InvalidAmountException();
        amount += amt;
        System.out.println("Deposited Rs." + amt + ". New balance: Rs." + amount);
    }

    void withdraw(double amt)
            throws InvalidAmountException, OverdraftException {
        if (amt <= 0)    throw new InvalidAmountException();
        if (amt > amount) throw new OverdraftException();
        amount -= amt;
        System.out.println("Withdrawn Rs." + amt + ". Remaining balance: Rs." + amount);
    }

    void display() {
        System.out.println("ID: " + cid + " | Name: " + cname + " | Balance: Rs." + amount);
    }
}

public class BankApp {
    static List<Customer> customers = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);
    static final String FILE_NAME = "bank_records.txt";

    static void saveToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Customer c : customers) {
                bw.write(c.cid + "," + c.cname + "," + c.amount);
                bw.newLine();
            }
            System.out.println("Records saved to " + FILE_NAME);
        } catch (IOException e) {
            System.out.println("File error: " + e.getMessage());
        }
    }

    static void loadFromFile() {
        File f = new File(FILE_NAME);
        if (!f.exists()) return;
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                customers.add(new Customer(
                        Integer.parseInt(parts[0]),
                        parts[1],
                        Double.parseDouble(parts[2])
                ));
            }
            System.out.println("Records loaded from file.");
        } catch (IOException e) {
            System.out.println("Could not load file: " + e.getMessage());
        }
    }
    static Customer findCustomer(int id) {
        for (Customer c : customers)
            if (c.cid == id) return c;
        return null;
    }

    public static void main(String[] args) {
        loadFromFile();
        int choice;

        do {
            System.out.println("===== BANKING MENU =====");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Display All Accounts");
            System.out.println("5. Save & Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    try {
                        System.out.print("Enter Customer ID (1-20): ");
                        int id = sc.nextInt();
                        if (id < 1 || id > 20) throw new BadCustomerIDException();

                        System.out.print("Enter Name: ");
                        String name = sc.next();

                        System.out.print("Enter Initial Amount: ");
                        double amt = sc.nextDouble();
                        if (amt <= 0)   throw new InvalidAmountException();
                        if (amt < 1000) throw new LowDepositException();

                        customers.add(new Customer(id, name, amt));
                        System.out.println("Account created successfully!");
                    } catch (BadCustomerIDException | LowDepositException
                             | InvalidAmountException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case 2:
                    try {
                        System.out.print("Enter Customer ID: ");
                        int id = sc.nextInt();
                        Customer c = findCustomer(id);
                        if (c == null) { System.out.println("Customer not found."); break; }
                        System.out.print("Enter amount to deposit: ");
                        double amt = sc.nextDouble();
                        c.deposit(amt);
                    } catch (InvalidAmountException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 3:
                    try {
                        System.out.print("Enter Customer ID: ");
                        int id = sc.nextInt();
                        Customer c = findCustomer(id);
                        if (c == null) { System.out.println("Customer not found."); break; }
                        System.out.print("Enter amount to withdraw: ");
                        double amt = sc.nextDouble();
                        c.withdraw(amt);
                    } catch (InvalidAmountException | OverdraftException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 4:
                    if (customers.isEmpty()) {
                        System.out.println("No accounts found.");
                    } else {
                        System.out.println("\total--- All Accounts ---");
                        for (Customer c : customers) c.display();
                    }
                    break;

                case 5:
                    saveToFile();
                    System.out.println("Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
            }

        } while (choice != 5);

        sc.close();
    }
}
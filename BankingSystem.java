import java.util.Scanner;
import java.util.ArrayList;

public class BankingSystem {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Customer customer = new Customer("Goutam123", "1856");
        BankingOperations bankingOperations = new BankingOperations(customer);

        System.out.println("Welcome to the Banking System!");
        System.out.print("Enter your Customer ID: ");
        String customerID = input.nextLine();
        System.out.print("Enter your PIN: ");
        String pinCode = input.nextLine();

        if (customer.authenticate(customerID, pinCode)) {
            int option;
            do {
                System.out.println("\nBanking Menu:");
                System.out.println("1. View Transaction History");
                System.out.println("2. Withdraw Cash");
                System.out.println("3. Deposit Funds");
                System.out.println("4. Transfer Money");
                System.out.println("5. Exit");
                System.out.print("Enter your choice: ");
                while (!input.hasNextInt()) {
                    System.out.println("Invalid input. Please enter a number.");
                    input.next();
                }
                option = input.nextInt();

                switch (option) {
                    case 1:
                        bankingOperations.viewTransactionHistory();
                        break;
                    case 2:
                        bankingOperations.withdrawCash(input);
                        break;
                    case 3:
                        bankingOperations.depositFunds(input);
                        break;
                    case 4:
                        bankingOperations.transferMoney(input);
                        break;
                    case 5:
                        System.out.println("Thank you for using the Banking System. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } while (option != 5);
        } else {
            System.out.println("Invalid Customer ID or PIN.");
        }

        input.close();
    }
}

class Customer {
    private String customerID;
    private String pinCode;

    public Customer(String customerID, String pinCode) {
        this.customerID = customerID;
        this.pinCode = pinCode;
    }

    public boolean authenticate(String customerID, String pinCode) {
        return this.customerID.equals(customerID) && this.pinCode.equals(pinCode);
    }
}

class BankingOperations {
    private Customer customer;
    private double accountBalance;
    private ArrayList<String> transactionRecord;

    public BankingOperations(Customer customer) {
        this.customer = customer;
        this.accountBalance = 1000.0;
        this.transactionRecord = new ArrayList<>();
    }

    public void viewTransactionHistory() {
        if (transactionRecord.isEmpty()) {
            System.out.println("No transactions yet.");
        } else {
            for (String transaction : transactionRecord) {
                System.out.println(transaction);
            }
        }
    }

    public void withdrawCash(Scanner input) {
        System.out.print("Enter amount to withdraw: ");
        while (!input.hasNextDouble()) {
            System.out.println("Invalid input. Please enter a valid amount.");
            input.next();
        }
        double amount = input.nextDouble();
        if (amount > accountBalance) {
            System.out.println("Insufficient balance.");
        } else {
            accountBalance -= amount;
            transactionRecord.add("Withdrawal: ₹" + amount);
            System.out.println("Withdrawn: ₹" + amount);
        }
    }

    public void depositFunds(Scanner input) {
        System.out.print("Enter amount to deposit: ");
        while (!input.hasNextDouble()) {
            System.out.println("Invalid input. Please enter a valid amount.");
            input.next();
        }
        double amount = input.nextDouble();
        accountBalance += amount;
        transactionRecord.add("Deposit: ₹" + amount);
        System.out.println("Deposited: ₹" + amount);
    }

    public void transferMoney(Scanner input) {
        System.out.print("Enter recipient Customer ID: ");
        String recipientID = input.next();
        System.out.print("Enter amount to transfer: ");
        while (!input.hasNextDouble()) {
            System.out.println("Invalid input. Please enter a valid amount.");
            input.next();
        }
        double amount = input.nextDouble();
        if (amount > accountBalance) {
            System.out.println("Insufficient balance.");
        } else {
            accountBalance -= amount;
            transactionRecord.add("Transfer to " + recipientID + ": ₹" + amount);
            System.out.println("Transferred: ₹" + amount + " to " + recipientID);
        }
    }
}
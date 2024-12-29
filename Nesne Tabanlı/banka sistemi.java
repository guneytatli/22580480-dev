import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Account {
    private String accountNumber;
    private String owner;
    private double balance;

    // static list to hold all account instances
    public static List<Account> accounts = new ArrayList<>();

    // Constructor to create a new account
    public Account(String accountNumber, String owner, double balance) {
        this.accountNumber = accountNumber;
        this.owner = owner;
        this.balance = balance;
        accounts.add(this); // Add the account to the list
    }

    // Deposit method to add money to the account
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            Bank.trackTransaction("Deposited $" + amount + " to account " + accountNumber);
            System.out.println("Successfully deposited $" + amount + ". New balance: $" + balance);
        } else {
            System.out.println("Amount must be greater than zero.");
        }
    }

    // Withdraw method to take money from the account
    public void withdraw(double amount) {
        if (amount <= balance && amount > 0) {
            balance -= amount;
            Bank.trackTransaction("Withdrew $" + amount + " from account " + accountNumber);
            System.out.println("Successfully withdrew $" + amount + ". New balance: $" + balance);
        } else {
            System.out.println("Insufficient funds or invalid amount.");
        }
    }

    // View account balance
    public void viewBalance() {
        System.out.println("Account Owner: " + owner);
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Balance: $" + balance);
    }

    // Get account number for searching
    public String getAccountNumber() {
        return accountNumber;
    }
}

class Bank {
    // static list to hold all transaction descriptions
    public static List<String> transactionHistory = new ArrayList<>();

    // Display basic bank information
    public static void displayBankInfo() {
        System.out.println("Welcome to the Bank!");
        System.out.println("We offer a variety of banking services including deposits, withdrawals, and account management.");
    }

    // Track transaction history
    public static void trackTransaction(String description) {
        transactionHistory.add(description);
    }

    // Display transaction history
    public static void displayTransactionHistory() {
        System.out.println("Transaction History:");
        for (String transaction : transactionHistory) {
            System.out.println(transaction);
        }
    }

    // Find an account by account number
    public static Account findAccount(String accountNumber) {
        for (Account account : Account.accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        return null; // Return null if account not found
    }
}

public class BankSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Creating some sample accounts
        new Account("12345", "Alice", 5000);
        new Account("67890", "Bob", 3000);
        new Account("11223", "Charlie", 1000);

        // Display bank information
        Bank.displayBankInfo();

        while (true) {
            System.out.println("\n1. Create New Account");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. View Account Balance");
            System.out.println("5. View Transaction History");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    // Create new account
                    System.out.print("Enter account number: ");
                    String newAccountNumber = scanner.nextLine();
                    System.out.print("Enter account owner name: ");
                    String owner = scanner.nextLine();
                    System.out.print("Enter initial balance: ");
                    double initialBalance = scanner.nextDouble();
                    new Account(newAccountNumber, owner, initialBalance);
                    System.out.println("Account created successfully!");
                    break;
                
                case 2:
                    // Deposit money
                    System.out.print("Enter account number: ");
                    String depositAccountNumber = scanner.nextLine();
                    Account depositAccount = Bank.findAccount(depositAccountNumber);
                    if (depositAccount != null) {
                        System.out.print("Enter amount to deposit: ");
                        double depositAmount = scanner.nextDouble();
                        depositAccount.deposit(depositAmount);
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;

                case 3:
                    // Withdraw money
                    System.out.print("Enter account number: ");
                    String withdrawAccountNumber = scanner.nextLine();
                    Account withdrawAccount = Bank.findAccount(withdrawAccountNumber);
                    if (withdrawAccount != null) {
                        System.out.print("Enter amount to withdraw: ");
                        double withdrawAmount = scanner.nextDouble();
                        withdrawAccount.withdraw(withdrawAmount);
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;

                case 4:
                    // View account balance
                    System.out.print("Enter account number: ");
                    String balanceAccountNumber = scanner.nextLine();
                    Account balanceAccount = Bank.findAccount(balanceAccountNumber);
                    if (balanceAccount != null) {
                        balanceAccount.viewBalance();
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;

                case 5:
                    // Display transaction history
                    Bank.displayTransactionHistory();
                    break;

                case 6:
                    // Exit
                    System.out.println("Exiting the system...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}

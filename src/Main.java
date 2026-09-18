import java.util.Scanner;

public class Main {
    private static final Bank bank = new Bank();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        FileManager.loadAccounts(bank);
        boolean running = true;

        System.out.println("=====================================");
        System.out.println("   WELCOME TO JAVA BANK SIMULATOR");
        System.out.println("=====================================");

        while (running) {
            printMenu();
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> createAccount();
                case "2" -> deposit();
                case "3" -> withdraw();
                case "4" -> transfer();
                case "5" -> checkBalance();
                case "6" -> viewTransactionHistory();
                case "7" -> listAllAccounts();
                case "8" -> {
                    FileManager.saveAccounts(bank);
                    System.out.println("Goodbye!");
                    running = false;
                }
                default -> System.out.println("Invalid choice. Please try again.\n");
            }
        }
        scanner.close();
    }

    private static void printMenu() {
        System.out.println("\n--------- MENU ---------");
        System.out.println("1. Create Account");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Transfer");
        System.out.println("5. Check Balance");
        System.out.println("6. View Transaction History");
        System.out.println("7. List All Accounts");
        System.out.println("8. Save & Exit");
        System.out.print("Enter your choice: ");
    }

    private static void createAccount() {
        System.out.print("Enter account holder name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Enter initial deposit amount: ");
        double amount = readDouble();
        Account account = bank.createAccount(name, amount);
        System.out.println("Account created successfully: " + account);
    }

    private static void deposit() {
        System.out.print("Enter account number: ");
        String accNum = scanner.nextLine().trim();
        System.out.print("Enter amount to deposit: ");
        double amount = readDouble();
        try {
            bank.deposit(accNum, amount);
            System.out.println("Deposit successful. New balance: " + bank.getAccount(accNum).getBalance());
        } catch (AccountNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void withdraw() {
        System.out.print("Enter account number: ");
        String accNum = scanner.nextLine().trim();
        System.out.print("Enter amount to withdraw: ");
        double amount = readDouble();
        try {
            bank.withdraw(accNum, amount);
            System.out.println("Withdrawal successful. New balance: " + bank.getAccount(accNum).getBalance());
        } catch (AccountNotFoundException | InsufficientFundsException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void transfer() {
        System.out.print("Enter sender account number: ");
        String fromAcc = scanner.nextLine().trim();
        System.out.print("Enter receiver account number: ");
        String toAcc = scanner.nextLine().trim();
        System.out.print("Enter amount to transfer: ");
        double amount = readDouble();
        try {
            bank.transfer(fromAcc, toAcc, amount);
            System.out.println("Transfer successful.");
        } catch (AccountNotFoundException | InsufficientFundsException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void checkBalance() {
        System.out.print("Enter account number: ");
        String accNum = scanner.nextLine().trim();
        try {
            Account account = bank.getAccount(accNum);
            System.out.println("Balance for " + accNum + ": " + account.getBalance());
        } catch (AccountNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void viewTransactionHistory() {
        System.out.print("Enter account number: ");
        String accNum = scanner.nextLine().trim();
        try {
            Account account = bank.getAccount(accNum);
            System.out.println("Transaction history for " + accNum + ":");
            for (String entry : account.getTransactionHistory()) {
                System.out.println("  - " + entry);
            }
        } catch (AccountNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void listAllAccounts() {
        if (bank.getAllAccounts().isEmpty()) {
            System.out.println("No accounts found.");
            return;
        }
        System.out.println("All Accounts:");
        for (Account account : bank.getAllAccounts().values()) {
            System.out.println("  " + account);
        }
    }

    private static double readDouble() {
        while (true) {
            try {
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("Invalid number, please enter again: ");
            }
        }
    }
}

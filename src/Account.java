import java.util.ArrayList;
import java.util.List;

public class Account {
    private final String accountNumber;
    private String holderName;
    private double balance;
    private final List<String> transactionHistory;

    public Account(String accountNumber, String holderName, double initialBalance) {
        this.accountNumber = accountNumber;
        this.holderName = holderName;
        this.balance = initialBalance;
        this.transactionHistory = new ArrayList<>();
        transactionHistory.add("Account opened with balance: " + initialBalance);
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getHolderName() {
        return holderName;
    }

    public double getBalance() {
        return balance;
    }

    public List<String> getTransactionHistory() {
        return transactionHistory;
    }

    public void deposit(double amount) {
        balance += amount;
        transactionHistory.add("Deposited: " + amount + " | New Balance: " + balance);
    }

    public void withdraw(double amount) throws InsufficientFundsException {
        if (amount > balance) {
            throw new InsufficientFundsException(
                    "Insufficient funds in account " + accountNumber +
                    ". Available balance: " + balance + ", Requested: " + amount);
        }
        balance -= amount;
        transactionHistory.add("Withdrew: " + amount + " | New Balance: " + balance);
    }

    @Override
    public String toString() {
        return String.format("Account[%s] Holder: %s, Balance: %.2f", accountNumber, holderName, balance);
    }

    // Used for saving to file: accountNumber,holderName,balance
    public String toFileFormat() {
        return accountNumber + "," + holderName + "," + balance;
    }
}

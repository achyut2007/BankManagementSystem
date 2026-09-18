import java.util.HashMap;
import java.util.Map;

public class Bank {
    private final Map<String, Account> accounts;
    private int nextAccountNumber;

    public Bank() {
        accounts = new HashMap<>();
        nextAccountNumber = 1001;
    }

    public Account createAccount(String holderName, double initialDeposit) {
        String accNum = "ACC" + nextAccountNumber++;
        Account account = new Account(accNum, holderName, initialDeposit);
        accounts.put(accNum, account);
        return account;
    }

    public Account getAccount(String accountNumber) throws AccountNotFoundException {
        Account account = accounts.get(accountNumber);
        if (account == null) {
            throw new AccountNotFoundException("No account found with number: " + accountNumber);
        }
        return account;
    }

    public void deposit(String accountNumber, double amount) throws AccountNotFoundException {
        Account account = getAccount(accountNumber);
        account.deposit(amount);
    }

    public void withdraw(String accountNumber, double amount)
            throws AccountNotFoundException, InsufficientFundsException {
        Account account = getAccount(accountNumber);
        account.withdraw(amount);
    }

    public void transfer(String fromAccNum, String toAccNum, double amount)
            throws AccountNotFoundException, InsufficientFundsException {
        Account from = getAccount(fromAccNum);
        Account to = getAccount(toAccNum);
        from.withdraw(amount);
        to.deposit(amount);
        from.getTransactionHistory().add("Transferred " + amount + " to " + toAccNum);
        to.getTransactionHistory().add("Received " + amount + " from " + fromAccNum);
    }

    public Map<String, Account> getAllAccounts() {
        return accounts;
    }

    // Restores accounts loaded from file, keeping numbering consistent
    public void restoreAccount(Account account) {
        accounts.put(account.getAccountNumber(), account);
        String numPart = account.getAccountNumber().replace("ACC", "");
        try {
            int num = Integer.parseInt(numPart);
            if (num >= nextAccountNumber) {
                nextAccountNumber = num + 1;
            }
        } catch (NumberFormatException ignored) {
            // skip if format unexpected
        }
    }
}

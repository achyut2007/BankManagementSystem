import java.io.*;
import java.util.Map;

public class FileManager {
    private static final String FILE_NAME = "accounts.txt";

    public static void saveAccounts(Bank bank) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Map.Entry<String, Account> entry : bank.getAllAccounts().entrySet()) {
                writer.println(entry.getValue().toFileFormat());
            }
            System.out.println("Accounts saved to " + FILE_NAME);
        } catch (IOException e) {
            System.out.println("Error saving accounts: " + e.getMessage());
        }
    }

    public static void loadAccounts(Bank bank) {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            return; // nothing to load on first run
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String accNum = parts[0];
                    String holder = parts[1];
                    double balance = Double.parseDouble(parts[2]);
                    Account account = new Account(accNum, holder, balance);
                    bank.restoreAccount(account);
                }
            }
            System.out.println("Loaded existing accounts from " + FILE_NAME);
        } catch (IOException e) {
            System.out.println("Error loading accounts: " + e.getMessage());
        }
    }
}

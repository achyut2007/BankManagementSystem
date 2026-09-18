# Java Bank Account Simulator

A console-based banking management system built in core Java, demonstrating object-oriented
programming, custom exception handling, collections, and file-based data persistence.

## Overview

This project simulates the core operations of a retail bank: opening accounts, depositing and
withdrawing funds, transferring money between accounts, checking balances, and reviewing
transaction history. All account data is persisted to a local file so it survives between runs.
It was built as a course project to apply OOP concepts (encapsulation, exception handling,
modular class design) in a small but complete, working application.

## Features

- **Create Account** — open a new account with an auto-generated account number, holder name, and initial deposit
- **Deposit** — add funds to any existing account
- **Withdraw** — remove funds, with validation that blocks overdrafts
- **Transfer** — move money between two accounts in a single operation
- **Check Balance** — look up the current balance of any account
- **Transaction History** — view a full log of every deposit, withdrawal, and transfer on an account
- **List All Accounts** — see every account and its current balance at a glance
- **Persistent Storage** — accounts are saved to `accounts.txt` on exit and automatically reloaded on the next run
- **Custom Exception Handling** — `InsufficientFundsException` and `AccountNotFoundException` enforce valid banking operations at compile time

## Technologies / Tools Used

- **Language:** Java (JDK 21), core Java only — no external libraries or frameworks
- **Data Structures:** `HashMap` for O(1) account lookup, `ArrayList` for per-account transaction history
- **Persistence:** `java.io` (`BufferedReader` / `PrintWriter`) — plain-text CSV-style file storage
- **Input Handling:** `java.util.Scanner`

## Project Structure

```
BankSimulator/
└── src/
    ├── Main.java                        # Console menu and program entry point
    ├── Bank.java                        # Manages all accounts, transfers, lookups
    ├── Account.java                     # Represents a single account and its history
    ├── FileManager.java                 # Loads/saves accounts to accounts.txt
    ├── InsufficientFundsException.java  # Custom checked exception
    └── AccountNotFoundException.java    # Custom checked exception
```

## Steps to Install & Run

**Prerequisite:** JDK 8 or later installed (`java -version` to check).

```bash
# 1. Clone the repository
git clone <your-repo-url>
cd BankSimulator/src

# 2. Compile all source files
javac *.java

# 3. Run the application
java Main
```

The app will create `accounts.txt` in the same folder the first time you save, and will
automatically reload it on the next run.

## Instructions for Testing

1. Run the application and choose **1 (Create Account)** to open one or two accounts.
2. Choose **2 (Deposit)** and **3 (Withdraw)** to confirm balances update correctly.
3. Try withdrawing an amount larger than the balance — the app should print a clear
   `InsufficientFundsException` message instead of crashing.
4. Choose **4 (Transfer)** to move funds between two accounts and confirm both balances update.
5. Choose **6 (View Transaction History)** to confirm every operation was logged.
6. Choose **8 (Save & Exit)**, then relaunch the app — your accounts and balances should still be there.

A detailed manual test log (8 scenarios covering both success and failure paths) is included in
the project report (`Bank_Simulator_Project_Report.pdf`).

## Screenshots

See the **Screenshots / Results** section of the project report for full console output,
including account creation, transfers, transaction history, and exception handling in action.

## Author

Built as a course project (VITyarthi — Build Your Own Project).

# Problem Statement

Manual or spreadsheet-based tracking of bank account transactions is error-prone, offers no
built-in validation, and provides no structured way to prevent invalid operations such as
withdrawing more money than is available in an account. There is a need for a simple, reliable,
menu-driven application that can create and manage multiple bank accounts, safely process
deposits, withdrawals, and transfers with proper validation, and persist account data between
sessions — while demonstrating sound software engineering practices such as modular design and
robust exception handling.

# Scope of the Project

The Java Bank Account Simulator is a **console-based**, single-user banking application. It is
scoped as a learning project to demonstrate core Java and object-oriented programming concepts,
not as a production banking system. Specifically, the scope includes:

- Creating and managing multiple bank accounts in memory during a session
- Performing deposits, withdrawals, and transfers with input validation
- Enforcing business rules (e.g. no overdrafts) through custom checked exceptions
- Persisting account data to a local flat file between sessions
- Providing a clear, menu-driven text interface for all operations

**Out of scope:** authentication/login, multi-user concurrent access, a graphical user interface,
a relational database backend, and network/API integration. These are noted as potential future
enhancements in the project report.

# Target Users

- **Students and instructors** evaluating the project as a course submission, looking for a clear
  demonstration of OOP, exception handling, and file I/O in Java.
- **Individual users / hobbyists** who want a simple, local tool to simulate basic banking
  operations without needing a database or internet connection.

# High-Level Features

1. **Account Management** — create new accounts with auto-generated account numbers; list all accounts.
2. **Transaction Processing** — deposit, withdraw, and transfer funds between accounts, each validated
   against current balances.
3. **Reporting & Persistence** — check balances, view full transaction history per account, and
   automatically save/reload all account data across sessions using file-based storage.

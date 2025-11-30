Console-based Core Java Banking Application

A simple, well-structured console banking application demonstrating core Java concepts and clean layering.
Implements account management and basic banking operations with validations and custom exceptions.

🚀 Features

Create / Open account (with validation)

Withdraw

Deposit

Transfer between accounts

List accounts by customer name

Account statement (list transactions for an account)

Transaction history for an account

Input validation (custom ValidationException)

Custom exception handling and meaningful error messages

Uses Java Streams for filtering and reporting

📦 Project structure (packages)
src/
├─ app/                // application entry point (Main)
├─ domain/             // domain models: Account, Customer, Transaction, etc.
├─ repositories/       // in-memory repositories (List/Map backed)
├─ service/            // business logic: BankService, BankServiceImpl
├─ util/               // helper utilities  validators
├─ exception/          // custom exceptions: ValidationException, NotFoundException


🧠 Concepts & Techniques Demonstrated

Object-Oriented Design (encapsulation, single-responsibility)

Collections API (List, Map, etc.)

Java Streams (filtering, sorting, grouping)

Custom exceptions and exception chaining

Lambda expressions and functional interfaces (e.g., Validation<T>)

Separation of concerns (domain / repository / service / app)

Console I/O and menu-driven interaction

⚙️ Requirements

Java 11+ (works with Java 8 as well, but recommended 11+)

No external libraries — plain Java

🧭 Usage (example console flow)
Welcome to Simple Bank
1) Open Account
2) Deposit
3) Withdraw
4) Transfer
5) List Accounts by Customer Name
6) Account Statement
7) Exit

✅ Validation rules (examples)

Name cannot be null or blank

Email must contain @

Account type must be SAVINGS or CURRENT

Withdraw/Transfer amounts must be > 0 and <= available balance

Transfers require both accounts to exist

🛠 Error handling

ValidationException thrown for invalid inputs (message provided)

NotFoundException (or similar) for missing accounts/customers

Service layer catches and rethrows as appropriate; application prints friendly messages


♻️ Extensibility / Future improvements

Persist data to disk (file-based or embedded DB like H2/SQLite)

Add authentication / user login

Add interest calculation for savings accounts

Add concurrency handling (synchronized transfers)

REST API wrapper (Spring Boot) and a web frontend (React)

Add unit/integration tests and CI pipeline

Improve transaction model to support reversals, statuses, and pagination





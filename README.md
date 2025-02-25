# Expense-Tracker-Updated

# Java Expense Tracker

📌 Project Overview
This is a Java-based Expense Tracker that allows users to:
- Register/Login with **File-based Data Persistence
- Add, View, and Filter Expenses
- Calculate Category-wise Expense Summation
- Save Expenses Locally in a File (`expenses.txt`)

🛠️ Technologies Used
- Java (OOP, File Handling)
- Serializable Objects for Data Storage
- CLI-based User Interaction

📂 Project Structure
```
ExpenseTracker/
│── ExpenseTracker.java    # Main Application
│── User.java              # Handles User Registration
│── Expense.java           # Defines Expense Object
│── ExpenseManager.java    # Manages Expense Data
│── expenses.txt           # Stores Expense Data
```

⚡ How to Run the Project
1️⃣ Compile the Java Files
```sh
javac ExpenseTracker.java User.java Expense.java ExpenseManager.java
```

2️⃣ Run the Application
```sh
java ExpenseTracker
```

📖 How to Use
1. Login/Register with username and password.
2. Add Expenses by entering Category, Amount, and Date.
3. View Expenses to check recorded transactions.
4. View Total by Category to see spending summary.
5. Exit the application, data is saved automatically.

📝 File Storage
- Expenses are stored in `expenses.txt`.
- Data is retrieved automatically on the next run.

🚀 Future Improvements
- Add GUI with Java Swing.
- Implement Database (MySQL) Integration.
- Include Graph Reports (JFreeChart).





import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

class Expense implements Serializable {
    private String category;
    private double amount;
    private Date date;

    public Expense(String category, double amount, Date date) {
        this.category = category;
        this.amount = amount;
        this.date = date;
    }

    public String getCategory() { return category; }
    public double getAmount() { return amount; }
    public Date getDate() { return date; }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        return "Date: " + sdf.format(date) + ", Category: " + category + ", Amount: " + amount;
    }
}

class User implements Serializable {
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() { return username; }
    public boolean validatePassword(String password) { return this.password.equals(password); }
}

class ExpenseManager {
    private List<Expense> expenses;
    private static final String FILE_PATH = "expenses.txt";

    public ExpenseManager() {
        expenses = new ArrayList<>();
        loadExpenses();
    }

    public void addExpense(Expense expense) {
        expenses.add(expense);
        saveExpenses();
    }

    public List<Expense> getAllExpenses() { return expenses; }

    public double getTotalByCategory(String category) {
        return expenses.stream()
                .filter(expense -> expense.getCategory().equalsIgnoreCase(category))
                .mapToDouble(Expense::getAmount)
                .sum();
    }

    private void saveExpenses() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(expenses);
        } catch (IOException e) {
            System.out.println("Error saving expenses: " + e.getMessage());
        }
    }

    private void loadExpenses() {
        File file = new File(FILE_PATH);
        if (!file.exists()) return;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            expenses = (List<Expense>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading expenses: " + e.getMessage());
        }
    }
}

public class ExpenseTracker {
    private static Scanner scanner = new Scanner(System.in);
    private static ExpenseManager expenseManager = new ExpenseManager();
    private static User user;

    public static void main(String[] args) {
        System.out.println("Welcome to Expense Tracker!");
        if (!authenticateUser()) return;

        while (true) {
            System.out.println("\n1. Add Expense\n2. View Expenses\n3. View Category Total\n4. Exit");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> addExpense();
                case 2 -> viewExpenses();
                case 3 -> viewCategoryTotal();
                case 4 -> {
                    System.out.println("Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static boolean authenticateUser() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        user = new User(username, password);
        System.out.println("User authenticated successfully!");
        return true;
    }

    private static void addExpense() {
        System.out.print("Enter category: ");
        String category = scanner.nextLine();
        System.out.print("Enter amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();
        expenseManager.addExpense(new Expense(category, amount, new Date()));
        System.out.println("Expense added successfully.");
    }

    private static void viewExpenses() {
        List<Expense> expenses = expenseManager.getAllExpenses();
        if (expenses.isEmpty()) {
            System.out.println("No expenses recorded.");
        } else {
            expenses.forEach(System.out::println);
        }
    }

    private static void viewCategoryTotal() {
        System.out.print("Enter category: ");
        String category = scanner.nextLine();
        System.out.println("Total for " + category + ": " + expenseManager.getTotalByCategory(category));
    }
}

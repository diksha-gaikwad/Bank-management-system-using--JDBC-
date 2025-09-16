package org.example.main;

import org.example.dao.AccountDAO;
import org.example.model.Account;

import java.util.List;
import java.util.Scanner;

public class BankApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        AccountDAO dao = new AccountDAO();

        while (true) {
            System.out.println("\n--- Bank Management ---");
            System.out.println("1. Create Account");
            System.out.println("2. View All Accounts");
            System.out.println("3. Update Balance");
            System.out.println("4. Delete Account");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Email: ");
                    String email = sc.nextLine();
                    System.out.print("Enter Initial Balance: ");
                    double balance = sc.nextDouble();
                    dao.createAccount(new Account(name, email, balance));
                }
                case 2 -> {
                    List<Account> accounts = dao.getAllAccounts();
                    for (Account a : accounts) {
                        System.out.println(a.getId() + " | " + a.getName() + " | " + a.getEmail() + " | " + a.getBalance());
                    }
                }
                case 3 -> {
                    System.out.print("Enter Account ID: ");
                    int id = sc.nextInt();
                    System.out.print("Enter New Balance: ");
                    double balance = sc.nextDouble();
                    dao.updateBalance(id, balance);
                }
                case 4 -> {
                    System.out.print("Enter Account ID: ");
                    int id = sc.nextInt();
                    dao.deleteAccount(id);
                }
                case 5 -> {
                    System.out.println("Exiting...");
                    System.exit(0);
                }
                default -> System.out.println("Invalid Choice!");
            }
        }
    }
}

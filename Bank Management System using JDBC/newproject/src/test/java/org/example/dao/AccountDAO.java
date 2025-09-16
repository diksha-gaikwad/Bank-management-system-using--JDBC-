package org.example.dao;

import org.example.model.Account;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDAO {

    private final String url = "jdbc:mysql://localhost:3306/bankdb";
    private final String user = "root";
    private final String password = "Mrunali@123#";

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    // Create account
    public void createAccount(Account account) {
        String sql = "INSERT INTO accounts(name,email,balance) VALUES(?,?,?)";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, account.getName());
            ps.setString(2, account.getEmail());
            ps.setDouble(3, account.getBalance());
            ps.executeUpdate();
            System.out.println("Account created successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Read all accounts
    public List<Account> getAllAccounts() {
        List<Account> accounts = new ArrayList<>();
        String sql = "SELECT * FROM accounts";
        try (Connection con = getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                accounts.add(new Account(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getDouble("balance")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accounts;
    }

    // Update balance
    public void updateBalance(int id, double newBalance) {
        String sql = "UPDATE accounts SET balance=? WHERE id=?";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setDouble(1, newBalance);
            ps.setInt(2, id);
            ps.executeUpdate();
            System.out.println("Balance updated successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete account
    public void deleteAccount(int id) {
        String sql = "DELETE FROM accounts WHERE id=?";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Account deleted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

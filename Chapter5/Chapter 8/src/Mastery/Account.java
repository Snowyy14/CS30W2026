/**
 * Account.java
 * Author: Ethan
 * Date: April 16, 2026
 * Description: Base class for bank accounts. Manages balance, customer info, and
 *              account ID. Uses composition with the Customer class (has-a) to handle
 *              personal info separately from financial operations. PersonalAcct and
 *              BusinessAcct extend this class to add their own rules (like minimum
 *              balance penalties). The account ID is auto-generated from the customer's
 *              first initial + last name.
 */
package Mastery;

import java.text.NumberFormat;

public class Account {
    // financial data
    private double balance;

    // customer info stored in a separate object (composition / has-a)
    private Customer customer;

    // auto-generated from first initial + last name (e.g., "JDoe")
    private String accountID;

    /**
     * Full constructor - creates an account with balance, customer name, and address.
     * The account ID is generated automatically from the name.
     * pre: none
     * post: Account created with balance, customer, and generated ID
     */
    public Account(double balance, String firstName, String lastName,
                   String street, String city, String province, String postalCode) {
        this.balance = balance;
        this.customer = new Customer(firstName, lastName, street, city, province, postalCode);
        this.accountID = firstName.substring(0, 1) + lastName;
    }

    /**
     * Simple constructor - just name and balance, no address.
     * Good for quick account creation.
     * pre: none
     * post: Account created with balance and name only
     */
    public Account(double balance, String firstName, String lastName) {
        this.balance = balance;
        this.customer = new Customer(firstName, lastName);
        this.accountID = firstName.substring(0, 1) + lastName;
    }

    /**
     * ID-only constructor - creates an empty account with just an ID.
     * Useful when you need a placeholder account.
     * pre: none
     * post: empty Account created with given ID
     */
    public Account(String id) {
        this.balance = 0;
        this.customer = new Customer("", "");
        this.accountID = id;
    }

    // --- Getters ---

    /**
     * Returns the account ID
     * pre: none
     * post: account ID returned
     */
    public String getID() {
        return accountID;
    }

    /**
     * Returns the current balance
     * pre: none
     * post: balance returned
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Returns the customer object (for accessing customer methods)
     * pre: none
     * post: customer returned
     */
    public Customer getCustomer() {
        return customer;
    }

    // --- Financial operations ---

    /**
     * Adds money to the account. Pretty straightforward.
     * pre: amount should be positive
     * post: balance increased by deposit amount
     */
    public void deposit(double amount) {
        balance += amount;
    }

    /**
     * Takes money out of the account, but only if there's enough.
     * Returns the amount withdrawn (or 0 if insufficient funds).
     * pre: none
     * post: balance decreased if enough funds, otherwise unchanged
     */
    public double withdrawal(double amount) {
        if (amount <= balance) {
            balance -= amount;
            return amount;
        } else {
            System.out.println("Insufficient funds - withdrawal denied.");
            return 0;
        }
    }

    /**
     * Triggers the address change process through the customer object.
     * This shows how composition works - Account delegates address stuff to Customer.
     * pre: none
     * post: customer prompted to update all address fields
     */
    public void changeAddress() {
        customer.changeStreet();
        customer.changeCity();
        customer.changeProvince();
        customer.changePostalCode();
    }

    /**
     * Two accounts are equal if they have the same account ID.
     * pre: none
     * post: true if IDs match, false otherwise
     */
    public boolean equals(Object obj) {
        if (obj instanceof Account) {
            Account other = (Account) obj;
            return this.accountID.equals(other.accountID);
        }
        return false;
    }

    /**
     * Returns a formatted string with account ID, customer info, and balance
     * pre: none
     * post: formatted string returned
     */
    public String toString() {
        NumberFormat money = NumberFormat.getCurrencyInstance();
        return "Account: " + accountID + "\n" +
               customer.toString() + "\n" +
               "Balance: " + money.format(balance);
    }
}

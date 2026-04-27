/**
 * MyBank.java
 * Author: Ethan
 * Date: April 16, 2026
 * Description: Aggregation class that manages a collection of bank accounts.
 *              Demonstrates the "has-a" relationship - a bank HAS accounts.
 *              Uses polymorphism since the accounts list holds Account references
 *              that can be PersonalAcct or BusinessAcct objects. Provides methods
 *              to add accounts, find by ID, list all accounts, and calculate
 *              total assets across the whole bank.
 */
package Mastery;

import java.text.NumberFormat;
import java.util.ArrayList;

public class MyBank {
    // the name of the bank
    private String bankName;

    // polymorphic list - holds PersonalAcct and BusinessAcct objects
    private ArrayList<Account> accounts;

    /**
     * Constructor - creates a bank with a name and empty account list
     * pre: none
     * post: MyBank created with no accounts
     */
    public MyBank(String bankName) {
        this.bankName = bankName;
        this.accounts = new ArrayList<Account>();
    }

    /**
     * Returns the bank name
     * pre: none
     * post: bank name returned
     */
    public String getBankName() {
        return bankName;
    }

    /**
     * Opens a new account at the bank. Since it takes an Account reference,
     * Since it takes an Account reference, either PersonalAcct or BusinessAcct works.
     * pre: account is not null
     * post: account added to the bank
     */
    public void openAccount(Account account) {
        accounts.add(account);
        System.out.println("Opened account: " + account.getID() + " at " + bankName);
    }

    /**
     * Finds an account by its ID. Returns null if no matching account exists.
     * pre: none
     * post: matching account returned, or null if not found
     */
    public Account findAccount(String id) {
        for (Account acct : accounts) {
            if (acct.getID().equals(id)) {
                return acct;
            }
        }
        return null;
    }

    /**
     * Closes (removes) an account by ID.
     * pre: none
     * post: account removed if found
     */
    public void closeAccount(String id) {
        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).getID().equals(id)) {
                System.out.println("Closed account: " + accounts.get(i).getID());
                accounts.remove(i);
                return;
            }
        }
        System.out.println("Account '" + id + "' not found.");
    }

    /**
     * Lists all accounts at the bank. Polymorphism means toString() automatically
     * shows the right format for PersonalAcct vs BusinessAcct.
     * pre: none
     * post: all accounts printed to console
     */
    public void listAllAccounts() {
        System.out.println("--- All Accounts at " + bankName + " ---");
        if (accounts.isEmpty()) {
            System.out.println("  No accounts.");
            return;
        }
        for (Account acct : accounts) {
            System.out.println(acct);
            System.out.println("---");
        }
    }

    /**
     * Lists only personal accounts using instanceof filtering
     * pre: none
     * post: all personal accounts printed
     */
    public void listPersonalAccounts() {
        System.out.println("--- Personal Accounts at " + bankName + " ---");
        for (Account acct : accounts) {
            if (acct instanceof PersonalAcct) {
                System.out.println(acct);
                System.out.println("---");
            }
        }
    }

    /**
     * Lists only business accounts using instanceof filtering
     * pre: none
     * post: all business accounts printed
     */
    public void listBusinessAccounts() {
        System.out.println("--- Business Accounts at " + bankName + " ---");
        for (Account acct : accounts) {
            if (acct instanceof BusinessAcct) {
                System.out.println(acct);
                System.out.println("---");
            }
        }
    }

    /**
     * Calculates the total assets across all accounts (sum of all balances).
     * This gives a snapshot of how much money the bank is managing.
     * pre: none
     * post: total assets returned
     */
    public double getTotalAssets() {
        double total = 0;
        for (Account acct : accounts) {
            total += acct.getBalance();
        }
        return total;
    }

    /**
     * Returns how many accounts the bank has
     * pre: none
     * post: count returned
     */
    public int getAccountCount() {
        return accounts.size();
    }

    /**
     * Returns a summary string for the bank
     * pre: none
     * post: formatted string returned
     */
    public String toString() {
        NumberFormat money = NumberFormat.getCurrencyInstance();
        return bankName + " [" + accounts.size() + " accounts, Total Assets: " +
               money.format(getTotalAssets()) + "]";
    }
}

/**
 * TestAccounts.java
 * Author: Ethan
 * Date: April 16, 2026
 * Description: Test application for the Account inheritance hierarchy and the
 *              MyBank aggregation class. Creates PersonalAcct and BusinessAcct
 *              objects, tests deposits, withdrawals, penalty fees, equals, and
 *              the MyBank operations like searching, listing, and total assets.
 *              Demonstrates inheritance, polymorphism, and aggregation working
 *              together in a realistic banking scenario.
 */
package Mastery;

public class TestAccounts {

    public static void main(String[] args) {
        System.out.println("=== Bank Account Testing Application ===\n");

        // --- Create personal accounts ---
        System.out.println("--- Creating Personal Accounts ---");
        PersonalAcct p1 = new PersonalAcct(500.00, "John", "Doe",
                "123 Elm Street", "Edmonton", "AB", "T5A 0A1");
        PersonalAcct p2 = new PersonalAcct(75.00, "Sarah", "Kim",
                "456 Oak Avenue", "Calgary", "AB", "T2P 1B3");

        // --- Create business accounts ---
        System.out.println("\n--- Creating Business Accounts ---");
        BusinessAcct b1 = new BusinessAcct(2000.00, "Jane", "Smith",
                "789 Main Street", "Vancouver", "BC", "V6B 2Y4");
        BusinessAcct b2 = new BusinessAcct(400.00, "Mike", "Johnson",
                "321 Pine Road", "Toronto", "ON", "M5V 3A8");

        // --- Display all accounts ---
        System.out.println("\n--- Account Details ---");
        System.out.println(p1);
        System.out.println();
        System.out.println(p2);
        System.out.println();
        System.out.println(b1);
        System.out.println();
        System.out.println(b2);

        // --- Test deposit ---
        System.out.println("\n--- Deposit Testing ---");
        System.out.println("Depositing $200 to John's account...");
        p1.deposit(200.00);
        System.out.println("John's new balance: $" + String.format("%.2f", p1.getBalance()));

        // --- Test personal account withdrawal with penalty ---
        System.out.println("\n--- Personal Account Withdrawal (Penalty Test) ---");
        System.out.println("John's balance before: $" + String.format("%.2f", p1.getBalance()));
        System.out.println("Withdrawing $620...");
        p1.withdrawal(620.00);
        System.out.println("John's balance after: $" + String.format("%.2f", p1.getBalance()));
        // balance should drop below $100, triggering the $2 penalty

        // --- Test business account withdrawal with penalty ---
        System.out.println("\n--- Business Account Withdrawal (Penalty Test) ---");
        System.out.println("Jane's balance before: $" + String.format("%.2f", b1.getBalance()));
        System.out.println("Withdrawing $1600...");
        b1.withdrawal(1600.00);
        System.out.println("Jane's balance after: $" + String.format("%.2f", b1.getBalance()));
        // balance should drop below $500, triggering the $10 penalty

        // --- Test insufficient funds ---
        System.out.println("\n--- Insufficient Funds Test ---");
        System.out.println("Trying to withdraw $10000 from Jane's account...");
        double result = b1.withdrawal(10000.00);
        System.out.println("Amount withdrawn: $" + String.format("%.2f", result));

        // --- Test equals ---
        System.out.println("\n--- Equals Testing ---");
        Account testAcct = new Account(0, "John", "Doe");
        System.out.println("p1 ID: " + p1.getID());
        System.out.println("testAcct ID: " + testAcct.getID());
        System.out.println("p1 equals testAcct (same ID)? " + p1.equals(testAcct));
        System.out.println("p1 equals b1 (JDoe vs JSmith)? " + p1.equals(b1));

        // --- Test MyBank aggregation class ---
        System.out.println("\n--- MyBank Aggregation Class ---");
        MyBank bank = new MyBank("Westview National Bank");
        System.out.println("Created: " + bank.getBankName() + "\n");

        // open accounts at the bank - polymorphism handles both types
        bank.openAccount(p1);
        bank.openAccount(p2);
        bank.openAccount(b1);
        bank.openAccount(b2);

        // list all accounts
        System.out.println();
        bank.listAllAccounts();

        // filter by type
        System.out.println();
        bank.listPersonalAccounts();
        System.out.println();
        bank.listBusinessAccounts();

        // search for an account
        System.out.println("\n--- Search Testing ---");
        Account found = bank.findAccount("JDoe");
        System.out.println("Search for 'JDoe': " + (found != null ? "Found - " + found.getID() : "Not found"));

        Account notFound = bank.findAccount("ZZZ");
        System.out.println("Search for 'ZZZ': " + (notFound != null ? "Found" : "Not found"));

        // bank summary
        System.out.println("\n--- Bank Summary ---");
        System.out.println(bank);
        System.out.println("Total accounts: " + bank.getAccountCount());

        System.out.println("\n=== All Account Tests Complete ===");
    }
}

/*
 * ======================== SCREEN DUMP ========================
 *
 * === Bank Account Testing Application ===
 *
 * --- Creating Personal Accounts ---
 *
 * --- Creating Business Accounts ---
 * *** Business account penalty: $10.00 fee applied (balance below $500.00) ***
 *
 * --- Account Details ---
 * [Personal Account]
 * Account: JDoe
 * John Doe
 * 123 Elm Street
 * Edmonton, AB T5A 0A1
 * Balance: $500.00
 *
 * [Personal Account]
 * Account: SKim
 * Sarah Kim
 * 456 Oak Avenue
 * Calgary, AB T2P 1B3
 * Balance: $73.00
 *
 * [Business Account]
 * Account: JSmith
 * Jane Smith
 * 789 Main Street
 * Vancouver, BC V6B 2Y4
 * Balance: $2,000.00
 *
 * [Business Account]
 * Account: MJohnson
 * Mike Johnson
 * 321 Pine Road
 * Toronto, ON M5V 3A8
 * Balance: $390.00
 *
 * --- Deposit Testing ---
 * Depositing $200 to John's account...
 * John's new balance: $700.00
 *
 * --- Personal Account Withdrawal (Penalty Test) ---
 * John's balance before: $700.00
 * Withdrawing $620...
 * *** Personal account penalty: $2.00 fee applied (balance below $100.00) ***
 * John's balance after: $78.00
 *
 * --- Business Account Withdrawal (Penalty Test) ---
 * Jane's balance before: $2000.00
 * Withdrawing $1600...
 * *** Business account penalty: $10.00 fee applied (balance below $500.00) ***
 * Jane's balance after: $390.00
 *
 * --- Insufficient Funds Test ---
 * Trying to withdraw $10000 from Jane's account...
 * Insufficient funds - withdrawal denied.
 * Amount withdrawn: $0.00
 *
 * --- Equals Testing ---
 * p1 ID: JDoe
 * testAcct ID: JDoe
 * p1 equals testAcct (same ID)? true
 * p1 equals b1 (JDoe vs JSmith)? false
 *
 * --- MyBank Aggregation Class ---
 * Created: Westview National Bank
 *
 * Opened account: JDoe at Westview National Bank
 * Opened account: SKim at Westview National Bank
 * Opened account: JSmith at Westview National Bank
 * Opened account: MJohnson at Westview National Bank
 *
 * --- All Accounts at Westview National Bank ---
 * [Personal Account]
 * Account: JDoe
 * John Doe
 * 123 Elm Street
 * Edmonton, AB T5A 0A1
 * Balance: $78.00
 * ---
 * [Personal Account]
 * Account: SKim
 * Sarah Kim
 * 456 Oak Avenue
 * Calgary, AB T2P 1B3
 * Balance: $73.00
 * ---
 * [Business Account]
 * Account: JSmith
 * Jane Smith
 * 789 Main Street
 * Vancouver, BC V6B 2Y4
 * Balance: $390.00
 * ---
 * [Business Account]
 * Account: MJohnson
 * Mike Johnson
 * 321 Pine Road
 * Toronto, ON M5V 3A8
 * Balance: $390.00
 * ---
 *
 * --- Personal Accounts at Westview National Bank ---
 * [Personal Account]
 * Account: JDoe
 * John Doe
 * 123 Elm Street
 * Edmonton, AB T5A 0A1
 * Balance: $78.00
 * ---
 * [Personal Account]
 * Account: SKim
 * Sarah Kim
 * 456 Oak Avenue
 * Calgary, AB T2P 1B3
 * Balance: $73.00
 * ---
 *
 * --- Business Accounts at Westview National Bank ---
 * [Business Account]
 * Account: JSmith
 * Jane Smith
 * 789 Main Street
 * Vancouver, BC V6B 2Y4
 * Balance: $390.00
 * ---
 * [Business Account]
 * Account: MJohnson
 * Mike Johnson
 * 321 Pine Road
 * Toronto, ON M5V 3A8
 * Balance: $390.00
 * ---
 *
 * --- Search Testing ---
 * Search for 'JDoe': Found - JDoe
 * Search for 'ZZZ': Not found
 *
 * --- Bank Summary ---
 * Westview National Bank [4 accounts, Total Assets: $931.00]
 * Total accounts: 4
 *
 * === All Account Tests Complete ===
 *
 * ======================== END SCREEN DUMP ========================
 */

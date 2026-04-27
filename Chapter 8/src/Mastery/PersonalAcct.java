/**
 * PersonalAcct.java
 * Author: Ethan
 * Date: April 16, 2026
 * Description: Subclass of Account for personal banking. Has a special rule where
 *              if the balance drops below $100, the customer gets charged a $2.00
 *              service fee. This penalty is checked after every withdrawal and at
 *              account creation. Demonstrates method overriding - I override
 *              withdrawal() to add penalty checking on top of the normal behavior.
 */
package Mastery;

public class PersonalAcct extends Account {
    // penalty threshold and fee amount as constants so they're easy to change
    private static final double MIN_BALANCE = 100.0;
    private static final double PENALTY_FEE = 2.00;

    /**
     * Constructor - creates a personal account with full address info.
     * Checks the balance right away in case they opened with less than $100.
     * pre: none
     * post: PersonalAcct created, penalty applied if balance under $100
     */
    public PersonalAcct(double balance, String firstName, String lastName,
                        String street, String city, String province, String postalCode) {
        super(balance, firstName, lastName, street, city, province, postalCode);
        checkBalance(); // apply penalty right away if needed
    }

    /**
     * Overrides the parent withdrawal to add the penalty check afterwards.
     * First does the normal withdrawal, then checks if the balance dropped
     * below the minimum. If it did, charges the $2 fee automatically.
     * pre: none
     * post: withdrawal made if possible, then penalty checked
     */
    @Override
    public double withdrawal(double amount) {
        double withdrawn = super.withdrawal(amount);
        // only check penalty if the withdrawal actually went through
        if (withdrawn > 0) {
            checkBalance();
        }
        return withdrawn;
    }

    /**
     * Checks if the balance is below $100 and charges the fee if so.
     * This is private because it's an internal rule - outside code shouldn't
     * be calling this directly, it happens automatically.
     * pre: none
     * post: $2 fee deducted if balance is under $100
     */
    private void checkBalance() {
        if (getBalance() < MIN_BALANCE) {
            super.withdrawal(PENALTY_FEE);
            System.out.println("*** Personal account penalty: $" +
                               String.format("%.2f", PENALTY_FEE) +
                               " fee applied (balance below $" +
                               String.format("%.2f", MIN_BALANCE) + ") ***");
        }
    }

    /**
     * Adds the account type to the parent toString
     * pre: none
     * post: formatted string with "Personal Account" label
     */
    public String toString() {
        return "[Personal Account]\n" + super.toString();
    }
}

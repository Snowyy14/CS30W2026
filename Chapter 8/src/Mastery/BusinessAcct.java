/**
 * BusinessAcct.java
 * Author: Ethan
 * Date: April 16, 2026
 * Description: Subclass of Account for business banking. Has a stricter rule than
 *              PersonalAcct - if the balance drops below $500, a $10.00 penalty fee
 *              is charged. The higher threshold and fee makes sense because business
 *              accounts are expected to maintain larger balances. Demonstrates how
 *              different subclasses can override the same method in different ways
 *              (polymorphism).
 */
package Mastery;

public class BusinessAcct extends Account {
    // business accounts have higher minimums and steeper penalties
    private static final double MIN_BALANCE = 500.0;
    private static final double PENALTY_FEE = 10.00;

    /**
     * Constructor - creates a business account with full address info.
     * Checks balance right away since the opening deposit might be under $500.
     * pre: none
     * post: BusinessAcct created, penalty applied if balance under $500
     */
    public BusinessAcct(double balance, String firstName, String lastName,
                        String street, String city, String province, String postalCode) {
        super(balance, firstName, lastName, street, city, province, postalCode);
        checkBalance(); // apply penalty right away if needed
    }

    /**
     * Overrides the parent withdrawal to add business-specific penalty check.
     * Same pattern as PersonalAcct but with $500 threshold and $10 fee.
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
     * Checks if balance dropped below the $500 minimum.
     * The $10 fee is much steeper than personal accounts because businesses
     * are expected to keep more money in their accounts.
     * pre: none
     * post: $10 fee deducted if balance is under $500
     */
    private void checkBalance() {
        if (getBalance() < MIN_BALANCE) {
            super.withdrawal(PENALTY_FEE);
            System.out.println("*** Business account penalty: $" +
                               String.format("%.2f", PENALTY_FEE) +
                               " fee applied (balance below $" +
                               String.format("%.2f", MIN_BALANCE) + ") ***");
        }
    }

    /**
     * Adds the account type to the parent toString
     * pre: none
     * post: formatted string with "Business Account" label
     */
    public String toString() {
        return "[Business Account]\n" + super.toString();
    }
}

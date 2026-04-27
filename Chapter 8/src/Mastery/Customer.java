/**
 * Customer.java
 * Author: Ethan
 * Date: April 16, 2026
 * Description: Represents a bank customer with personal info and address details.
 *              Used by the Account class through composition (has-a relationship) -
 *              every Account HAS a Customer. Includes methods to update individual
 *              address fields. This is a good example of how splitting things into
 *              separate classes keeps the code organized - Account handles money stuff,
 *              Customer handles personal info.
 */
package Mastery;

import java.util.Scanner;

public class Customer {
    // basic identity
    private String firstName;
    private String lastName;

    // address fields - separated so each can be updated individually
    private String street;
    private String city;
    private String province;
    private String postalCode;

    /**
     * Full constructor - sets up customer with all info provided.
     * Used when we know the full address at account creation time.
     * pre: none
     * post: Customer created with all fields set
     */
    public Customer(String firstName, String lastName, String street,
                    String city, String province, String postalCode) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.street = street;
        this.city = city;
        this.province = province;
        this.postalCode = postalCode;
    }

    /**
     * Simple constructor - just name, no address yet.
     * Uses the full constructor with empty strings for address fields.
     * Handy for quick account creation when address isn't available yet.
     * pre: none
     * post: Customer created with name only, address fields empty
     */
    public Customer(String firstName, String lastName) {
        this(firstName, lastName, "", "", "", "");
    }

    // --- Getters ---

    /** Returns the first name */
    public String getFirstName() { return firstName; }

    /** Returns the last name */
    public String getLastName() { return lastName; }

    /** Returns the full name */
    public String getFullName() { return firstName + " " + lastName; }

    // --- Address change methods ---
    // each one prompts the user for the new value via Scanner

    /**
     * Prompts the user to enter a new street address
     * pre: none
     * post: street updated with user input
     */
    public void changeStreet() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter new street: ");
        street = input.nextLine();
    }

    /**
     * Prompts the user to enter a new city
     * pre: none
     * post: city updated with user input
     */
    public void changeCity() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter new city: ");
        city = input.nextLine();
    }

    /**
     * Prompts the user to enter a new province
     * pre: none
     * post: province updated with user input
     */
    public void changeProvince() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter new province: ");
        province = input.nextLine();
    }

    /**
     * Prompts the user to enter a new postal code
     * pre: none
     * post: postal code updated with user input
     */
    public void changePostalCode() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter new postal code: ");
        postalCode = input.nextLine();
    }

    /**
     * Returns a formatted string with all customer info.
     * Only includes address lines that have actual data in them.
     * pre: none
     * post: formatted string returned
     */
    public String toString() {
        String result = firstName + " " + lastName;
        // only include address if it's been filled in
        if (!street.isEmpty()) {
            result += "\n" + street;
        }
        if (!city.isEmpty() || !province.isEmpty() || !postalCode.isEmpty()) {
            result += "\n" + city + ", " + province + " " + postalCode;
        }
        return result;
    }
}

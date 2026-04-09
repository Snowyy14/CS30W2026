package Mastery;
import java.util.Scanner;

public class Customer {
    private String firstName, lastName;

    // address stuff
    private String street, city, province, postalCode;
    
    // constructor sets everything up
    public Customer(String fName, String lName, String s, String c, String p, String zip) {
        firstName = fName; lastName = lName;
        street = s; city = c;
        province = p; postalCode = zip;
    }
    
// old constructor just in case
    public Customer(String fName, String lName) {
        this(fName, lName, "", "", "", "");
    }

    public void changeCity() {
Scanner in = new Scanner(System.in);
        System.out.print("new city: ");
        city = in.nextLine();
    }
    public void changeStreet() {
        Scanner in = new Scanner(System.in);
        System.out.print("new street: ");
street = in.nextLine();
    }

    public void changeProvince() {
                Scanner in = new Scanner(System.in);
        System.out.print("new province: ");
        province = in.nextLine();
    }

    public void changePostalCode() {
        Scanner in = new Scanner(System.in);
        System.out.print("new postal: ");
        postalCode = in.nextLine();
    }

// Prints customer info
    public String toString() {
return firstName + " " + lastName + "\n" + street + "\n" + city + " " + province + " " + postalCode;
    }
}

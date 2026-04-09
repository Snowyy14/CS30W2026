package Mastery;
import java.text.NumberFormat;

public class Account {
    private double bal; // balance
    private Customer cust;
    private String id;
    
    // makes the account
    public Account(double b, String f, String l, String st, String c, String p, String zip) {
bal = b;
cust = new Customer(f, l, st, c, p, zip);
id = f.substring(0,1) + l;
    }
    
    public Account(double b, String f, String l) {
        bal = b;
        cust = new Customer(f, l);
        id = f.substring(0,1) + l;
    }

    public Account(String Id) {
        bal = 0;
        cust = new Customer("", "");
        id = Id;
    }

    public String getID() { return id; }
public double getBalance() { return bal; }

    public void deposit(double amt) {
        bal += amt;
    }
    
// takes money out if u have enough
    public double withdrawal(double amt) {
        if (amt <= bal) {
            bal -= amt;
            return amt;
        } else {
System.out.println("ur broke"); // print error
            return 0;
        }
    }

    // method to change address lines
    public void changeAddress() {
        cust.changeStreet(); cust.changeCity();
        cust.changeProvince(); cust.changePostalCode();
    }
    
    public boolean equals(Object acct) {
        Account test = (Account)acct;
              if (id.equals(test.id)) { return true; } else { return false; }
    }

    public String toString() {
NumberFormat money = NumberFormat.getCurrencyInstance();
        return id + "\n" + cust.toString() + "\nBalance: " + money.format(bal);
    }
}

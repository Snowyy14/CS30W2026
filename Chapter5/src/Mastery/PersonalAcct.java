package Mastery;

// personal account stuff
public class PersonalAcct extends Account {

    public PersonalAcct(double b, String fName, String lName, String st, String c, String p, String postal) {
        super(b, fName, lName, st, c, p, postal);
                checkBal();
    }

    @Override
    public double withdrawal(double amt) {
        double w = super.withdrawal(amt);
        checkBal(); // check if they get charged
        return w;
    }

// charge em 2 bucks if they drop under 100
    private void checkBal() {
        if (getBalance() < 100) {
super.withdrawal(2.0);
            System.out.println("u dropped below 100 so u got charged 2 bucks srry");
        }
    }
}

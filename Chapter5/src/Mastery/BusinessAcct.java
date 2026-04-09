package Mastery;

// business account
public class BusinessAcct extends Account {

    public BusinessAcct(double bal, String f, String l, String st, String c, String p, String postal) {
        super(bal, f, l, st, c, p, postal);
checkBal();
    }

    @Override
    public double withdrawal(double amt) {
        double x = super.withdrawal(amt);
            checkBal();
        return x;
    }

    // 10 dollar penalty if < 500
private void checkBal() {
        if (getBalance() < 500.0) {
            super.withdrawal(10.0);
System.out.println("business acc warning under 500 so fined 10 dollars");
        }
    }
}
